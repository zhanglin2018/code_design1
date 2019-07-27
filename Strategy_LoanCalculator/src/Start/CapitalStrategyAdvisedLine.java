/**
 * 
 */
package Start;

/**
 * @author lin.zhang
 *
 */
public class CapitalStrategyAdvisedLine extends CapitalStrategy {
	@Override
	public double capital(Loan loan) {
		// 信用额度贷款
		// 资金计算方法：承诺金额 * 未用份额 * 期限 * 风险因素
		return loan.getCommitment() * loan.getUnusedPercentage() * loan.duration() * riskFactor(loan);
	}

	@Override
	public double duration(Loan loan) {
		return loan.yearsTo(loan.getExpiry());
	}

}