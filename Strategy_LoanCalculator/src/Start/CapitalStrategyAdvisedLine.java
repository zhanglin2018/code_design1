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
		// ���ö�ȴ���
		// �ʽ���㷽������ŵ��� * δ�÷ݶ� * ���� * ��������
		return loan.getCommitment() * loan.getUnusedPercentage() * loan.duration() * riskFactor(loan);
	}

	@Override
	public double duration(Loan loan) {
		return loan.yearsTo(loan.getExpiry());
	}

}