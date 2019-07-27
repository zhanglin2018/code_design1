package Finish;

public class CapitalStrategyTermLoan extends CapitalStrategy {
	public double capital(Loan loan) {
		return loan.getCommitment() * loan.duration() * riskFactor(loan);
	}
	
	public double duration(Loan loan) {
		return weightedAverageDuration(loan);
	}
}
