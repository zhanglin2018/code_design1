package Start;

import java.util.Date;

/*
 * 1.��װ����
 * 2.���Բ�����ֱ�ӸĿɼ����𣬵��Ǻ�������ֱ�� �ı�ɼ�����
 */

public class CapitalStrategy {

	//////////////////// ���������ڼ��� ////////////////////
	// ���������
	public double capital(Loan loan) {
		// ��Ч��Ϊ�գ������ղ�Ϊ�գ�Ϊ���ڴ���
		// �ʽ���㷽������ŵ��� * ���� * ��������
		if (loan.getExpiry() == null && loan.getMaturity() != null)
			return loan.getCommitment() * loan.duration() * riskFactor(loan);

		// ��Ч�ղ�Ϊ�գ�������Ϊ�գ�Ϊѭ������������ö�ȴ���
		// ��δ�÷ݶΪ 100%��Ϊ���ö�ȴ������Ϊѭ������
		if (loan.getExpiry() != null && loan.getMaturity() == null) {
			if (loan.getUnusedPercentage() != 1.0)
				// ���ö�ȴ���
				// �ʽ���㷽������ŵ��� * δ�÷ݶ� * ���� * ��������
				return loan.getCommitment() * loan.getUnusedPercentage() * loan.duration() * riskFactor(loan);
			else
				// ѭ������
				// �ʽ���㷽����δ�����
				return (loan.outstandingRiskAmount() * loan.duration() * riskFactor(loan))
						+ (loan.unusedRiskAmount() * loan.duration() * unusedRiskFactor(loan));
		}
		return 0.0;
	}

	// ��ȡ��������
	private double riskFactor(Loan loan) {
		return RiskFactor.getFactors().forRating(loan.getRiskRating());
	}

	// ��ȡδʹ�÷�������
	private double unusedRiskFactor(Loan loan) {
		return UnusedRiskFactors.getFactors().forRating(loan.getRiskRating());
	}

	// �������ڼ���
	public double duration(Loan loan) {
		if (loan.getExpiry() == null && loan.getMaturity() != null) // ���ڴ���
			return loan.weightedAverageDuration();
		else if (loan.getExpiry() != null && loan.getMaturity() == null) // ѭ���������ö�ȴ���
			return loan.yearsTo(loan.getExpiry());
		return 0.0;
	}
	
	
}
