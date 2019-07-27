package Start;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
 * private constructor:
 *    1: singleton case
 *    2: multiple field needed to implemented in the constructor.
 *    3: provide multiple static function to users.
 */

public class Loan {
	private static final long MILLIS_PER_DAY = 86400000;
	private static final long DAYS_PER_YEAR = 365;

	private Date expiry; // ��Ч��
	private Date maturity; // ������
	private Date start; // ��ʼ��
	private Date today; // ����

	private double commitment; // ��ŵ���
	private double outstanding; // δ����
	private double unusedPercentage; // δ�÷ݶ�
	private int riskRating; // ��������

	private List<Payment> payments; // ֧����¼
	private CapitalStrategy capitalStrategy;

	//////////////////// ��������� ////////////////////
	// ���캯��
	private Loan(double commitment, double outstanding, Date start, Date expiry, Date maturity, int riskRating, CapitalStrategy capitalStrategy) {
		this.setCommitment(commitment);
		this.outstanding = outstanding;
		this.start = start;
		this.setExpiry(expiry);
		this.setMaturity(maturity);
		this.setRiskRating(riskRating);

		this.unusedPercentage = 1.0;
		this.payments = new LinkedList<Payment>();
		this.capitalStrategy = capitalStrategy; // �����θı���? �����仯��ʱ�򣬿��Է����ı䡣
	}

	// �������ڴ���
	public static Loan newTermLoan(double commitment, Date start, Date maturity, int riskRating) {
		return new Loan(commitment, commitment, start, null, maturity, riskRating, new CapitalStrategy());
	}

	// �������ö�ȴ���
	public static Loan newAdvisedLine(double commitment, Date start, Date expiry, int riskRating) {
		if (riskRating > 3)
			return null;

		Loan advisedLine = new Loan(commitment, 0, start, expiry, null, riskRating, new CapitalStrategy());
		advisedLine.setUnusedPercentage(0.1);

		return advisedLine;
	}

	// ����ѭ������
	public static Loan newRevolver(double commitment, Date start, Date expiry, int riskRating) {
		return new Loan(commitment, 0, start, expiry, null, riskRating, new CapitalStrategy());
	}

	//////////////////// ���������ڼ��� ////////////////////
	// ���������
	public double capital() {
		return capitalStrategy.capital(this);
	}

	// �������ڼ���
	public double duration() {
		return capitalStrategy.duration(this);
	}

	//////////////////// ���������ڼ��㸨������ ////////////////////
	// δ�÷ݶ�
	public double getUnusedPercentage() {
		return unusedPercentage;
	}

	private void setUnusedPercentage(double unusedPercentage) {
		this.unusedPercentage = unusedPercentage;
	}

	// δ����
	public double outstandingRiskAmount() {
		return outstanding;
	}

	// δ�÷��ս��
	double unusedRiskAmount() {
		return (getCommitment() - outstanding);
	}

	// ��Ȩƽ������
	double weightedAverageDuration() {
		double duration = 0.0;
		double weightedAverage = 0.0;
		double sumOfPayments = 0.0;

		Iterator<Payment> loanPayments = payments.iterator();
		while (loanPayments.hasNext()) {
			Payment payment = loanPayments.next();
			sumOfPayments += payment.amount();
			weightedAverage += yearsTo(payment.date()) * payment.amount();
		}

		if (getCommitment() != 0.0)
			duration = weightedAverage / sumOfPayments;

		return duration;
	}

	// ������
	public double yearsTo(Date endDate) {
		Date beginDate = (today == null ? start : today);
		return ((endDate.getTime() - beginDate.getTime()) / MILLIS_PER_DAY) / DAYS_PER_YEAR;
	}

	// ��ȡ��������
	private double riskFactor() {
		return RiskFactor.getFactors().forRating(getRiskRating());
	}

	// ��ȡδʹ�÷�������
	private double unusedRiskFactor() {
		return UnusedRiskFactors.getFactors().forRating(getRiskRating());
	}

	//////////////////// ����֧������ ////////////////////
	// ֧��
	public void payment(double amount, Date date) {
		payments.add(new Payment(amount, date));
	}

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public Date getMaturity() {
		return maturity;
	}

	public void setMaturity(Date maturity) {
		this.maturity = maturity;
	}

	public double getCommitment() {
		return commitment;
	}

	public void setCommitment(double commitment) {
		this.commitment = commitment;
	}

	public int getRiskRating() {
		return riskRating;
	}

	public void setRiskRating(int riskRating) {
		this.riskRating = riskRating;
	}

}
