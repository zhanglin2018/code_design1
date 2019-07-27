package Finish;

public class CapitalStrategyAdvisedLine extends CapitalStrategy {
	public double capital(Loan loan) {
		return loan.getCommitment() * loan.getUnusedPercentage() * loan.duration() * riskFactor(loan);
	}
	
	public double duration(Loan loan) {
		return yearsTo(loan.getExpiry(), loan);
	}
}
