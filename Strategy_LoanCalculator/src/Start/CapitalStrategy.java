package Start;

import java.util.Date;

/*
 * 1.封装属性
 * 2.属性不建议直接改可见级别，但是函数可以直接 改变可见级别。
 */

public class CapitalStrategy {

	//////////////////// 贷款金额周期计算 ////////////////////
	// 贷款金额计算
	public double capital(Loan loan) {
		// 有效日为空，到期日不为空，为定期贷款
		// 资金计算方法：承诺金额 * 期限 * 风险因素
		if (loan.getExpiry() == null && loan.getMaturity() != null)
			return loan.getCommitment() * loan.duration() * riskFactor(loan);

		// 有效日不为空，到期日为空，为循环贷款或建议信用额度贷款
		// 若未用份额不为 100%，为信用额度贷款，否则为循环贷款
		if (loan.getExpiry() != null && loan.getMaturity() == null) {
			if (loan.getUnusedPercentage() != 1.0)
				// 信用额度贷款
				// 资金计算方法：承诺金额 * 未用份额 * 期限 * 风险因素
				return loan.getCommitment() * loan.getUnusedPercentage() * loan.duration() * riskFactor(loan);
			else
				// 循环贷款
				// 资金计算方法：未清风险
				return (loan.outstandingRiskAmount() * loan.duration() * riskFactor(loan))
						+ (loan.unusedRiskAmount() * loan.duration() * unusedRiskFactor(loan));
		}
		return 0.0;
	}

	// 获取风险因素
	private double riskFactor(Loan loan) {
		return RiskFactor.getFactors().forRating(loan.getRiskRating());
	}

	// 获取未使用风险因素
	private double unusedRiskFactor(Loan loan) {
		return UnusedRiskFactors.getFactors().forRating(loan.getRiskRating());
	}

	// 贷款周期计算
	public double duration(Loan loan) {
		if (loan.getExpiry() == null && loan.getMaturity() != null) // 定期贷款
			return loan.weightedAverageDuration();
		else if (loan.getExpiry() != null && loan.getMaturity() == null) // 循环或建议信用额度贷款
			return loan.yearsTo(loan.getExpiry());
		return 0.0;
	}
	
	
}
