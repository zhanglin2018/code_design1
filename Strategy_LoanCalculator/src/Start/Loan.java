package Start;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Loan {
	private static final long MILLIS_PER_DAY = 86400000;
	private static final long DAYS_PER_YEAR = 365;

	private Date expiry; 				// ��Ч��
	private Date maturity; 				// ������
	private Date start; 				// ��ʼ��
	private Date today; 				// ����

	private double commitment; 			// ��ŵ���
	private double outstanding; 		// δ����
	private double unusedPercentage; 	// δ�÷ݶ�
	private int riskRating; 			// ��������

	private List<Payment> payments; 	// ֧����¼

	//////////////////// ��������� ////////////////////
	// ���캯��
	private Loan(double commitment, double outstanding, Date start, Date expiry, Date maturity, int riskRating) {
		this.commitment = commitment;
		this.outstanding = outstanding;
		this.start = start;
		this.expiry = expiry;
		this.maturity = maturity;
		this.riskRating = riskRating;
		
		this.unusedPercentage = 1.0;
		this.payments = new LinkedList<Payment>();
	}

	// �������ڴ���
	public static Loan newTermLoan(double commitment, Date start, Date maturity, int riskRating) {
		return new Loan(commitment, commitment, start, null, maturity, riskRating);
	}

	// �������ö�ȴ���
	public static Loan newAdvisedLine(double commitment, Date start, Date expiry, int riskRating) {
		if (riskRating > 3)
			return null;

		Loan advisedLine = new Loan(commitment, 0, start, expiry, null, riskRating);
		advisedLine.setUnusedPercentage(0.1);

		return advisedLine;
	}

	// ����ѭ������
	public static Loan newRevolver(double commitment, Date start, Date expiry, int riskRating) {
		return new Loan(commitment, 0, start, expiry, null, riskRating);
	}
	
	//////////////////// ���������ڼ��� ////////////////////
	// ���������
	public double capital() {
		// ��Ч��Ϊ�գ������ղ�Ϊ�գ�Ϊ���ڴ���
		// �ʽ���㷽������ŵ��� * ���� * ��������
		if (expiry == null && maturity != null)
			return commitment * duration() * riskFactor();

		// ��Ч�ղ�Ϊ�գ�������Ϊ�գ�Ϊѭ������������ö�ȴ���
		// ��δ�÷ݶΪ 100%��Ϊ���ö�ȴ������Ϊѭ������
		if (expiry != null && maturity == null) {
			if (getUnusedPercentage() != 1.0)
				// ���ö�ȴ���
				// �ʽ���㷽������ŵ��� * δ�÷ݶ� * ���� * ��������
				return commitment * getUnusedPercentage() * duration() * riskFactor();
			else
				// ѭ������
				// �ʽ���㷽����δ�����
				return (outstandingRiskAmount() * duration() * riskFactor())
						+ (unusedRiskAmount() * duration() * unusedRiskFactor());
		}
		return 0.0;
	}

	// �������ڼ���
	public double duration() {
		if (expiry == null && maturity != null) 		// ���ڴ���
			return weightedAverageDuration();
		else if (expiry != null && maturity == null) 	// ѭ���������ö�ȴ���
			return yearsTo(expiry);
		return 0.0;
	}

	//////////////////// ���������ڼ��㸨������ ////////////////////
	// δ�÷ݶ�
	private double getUnusedPercentage() {
		return unusedPercentage;
	}

	private void setUnusedPercentage(double unusedPercentage) {
		this.unusedPercentage = unusedPercentage;
	}

	// δ����
	private double outstandingRiskAmount() {
		return outstanding;
	}

	// δ�÷��ս��
	private double unusedRiskAmount() {
		return (commitment - outstanding);
	}

	// ��Ȩƽ������
	private double weightedAverageDuration() {
		double duration = 0.0;
		double weightedAverage = 0.0;
		double sumOfPayments = 0.0;

		Iterator<Payment> loanPayments = payments.iterator();
		while (loanPayments.hasNext()) {
			Payment payment = loanPayments.next();
			sumOfPayments += payment.amount();
			weightedAverage += yearsTo(payment.date()) * payment.amount();
		}

		if (commitment != 0.0)
			duration = weightedAverage / sumOfPayments;
		
		return duration;
	}

	// ������
	private double yearsTo(Date endDate) {
		Date beginDate = (today == null ? start : today);
		return ((endDate.getTime() - beginDate.getTime()) / MILLIS_PER_DAY) / DAYS_PER_YEAR;
	}

	// ��ȡ��������
	private double riskFactor() {
		return RiskFactor.getFactors().forRating(riskRating);
	}

	// ��ȡδʹ�÷�������
	private double unusedRiskFactor() {
		return UnusedRiskFactors.getFactors().forRating(riskRating);
	}

	////////////////////����֧������ ////////////////////
	// ֧��
	public void payment(double amount, Date date) {
		payments.add(new Payment(amount, date));
	}

}
