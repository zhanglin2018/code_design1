package Finish;

public class CapitalStrategyRevolver extends CapitalStrategy {
	public double capital(Loan loan) {
		return (loan.outstandingRiskAmount() * loan.duration() * riskFactor(loan))
				+ (loan.unusedRiskAmount() * loan.duration() * unusedRiskFactor(loan));
	}
	
	public double duration(Loan loan) {
		return yearsTo(loan.getExpiry(), loan);
	}
}
