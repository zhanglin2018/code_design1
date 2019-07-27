package Finish;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Loan {
	private Date expiry; 				// ��Ч��
	private Date maturity; 				// ������
	private Date start; 				// ��ʼ��
	private Date today; 				// ����

	private double commitment; 			// ��ŵ���
	private double outstanding; 		// δ����
	private double unusedPercentage; 	// δ�÷ݶ�
	private int riskRating; 			// ��������

	private List<Payment> payments; 	// ֧����¼
	private CapitalStrategy capitalStrategy;

	//////////////////// ��������� ////////////////////
	// ���캯��
	private Loan(double commitment, double outstanding, Date start, Date expiry, Date maturity, int riskRating, CapitalStrategy capitalStrategy) {
		this.setCommitment(commitment);
		this.outstanding = outstanding;
		this.setStart(start);
		this.setExpiry(expiry);
		this.setMaturity(maturity);
		this.setRiskRating(riskRating);
		
		this.unusedPercentage = 1.0;
		this.setPayments(new LinkedList<Payment>());
		this.capitalStrategy = capitalStrategy;
	}

	// �������ڴ���
	public static Loan newTermLoan(double commitment, Date start, Date maturity, int riskRating) {
		return new Loan(commitment, commitment, start, null, maturity, riskRating, new CapitalStrategyTermLoan());
	}

	// �������ö�ȴ���
	public static Loan newAdvisedLine(double commitment, Date start, Date expiry, int riskRating) {
		if (riskRating > 3)
			return null;

		Loan advisedLine = new Loan(commitment, 0, start, expiry, null, riskRating, new CapitalStrategyAdvisedLine());
		advisedLine.setUnusedPercentage(0.1);

		return advisedLine;
	}

	// ����ѭ������
	public static Loan newRevolver(double commitment, Date start, Date expiry, int riskRating) {
		return new Loan(commitment, 0, start, expiry, null, riskRating, new CapitalStrategyRevolver());
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
	double getUnusedPercentage() {
		return unusedPercentage;
	}

	private void setUnusedPercentage(double unusedPercentage) {
		this.unusedPercentage = unusedPercentage;
	}

	// δ����
	double outstandingRiskAmount() {
		return outstanding;
	}

	// δ�÷��ս��
	double unusedRiskAmount() {
		return (getCommitment() - outstanding);
	}		

	////////////////////����֧������ ////////////////////
	// ֧��
	public void payment(double amount, Date date) {
		getPayments().add(new Payment(amount, date));
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

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

}
