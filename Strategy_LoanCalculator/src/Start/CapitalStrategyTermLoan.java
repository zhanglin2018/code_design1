package Start;

/*
 * 1. when the visibility can not satisfy some condition,
 *    You need to modify the function visibility.
 */

public class CapitalStrategyTermLoan extends CapitalStrategy {
	@Override
	public double capital(Loan loan) {
		// ��Ч��Ϊ�գ������ղ�Ϊ�գ�Ϊ���ڴ���
		// �ʽ���㷽������ŵ��� * ���� * ��������
		return loan.getCommitment() * loan.duration() * riskFactor(loan);

	}

	@Override
	public double duration(Loan loan) {
		return loan.weightedAverageDuration();
	}

}
