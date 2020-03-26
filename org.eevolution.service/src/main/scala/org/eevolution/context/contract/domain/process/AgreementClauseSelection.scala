package org.eevolution.context.contract.domain.process

import org.eevolution.context.contract.infrastructure.persistence.model.{MSAgreementClause, MSContractTermCondition, MSContractTermConditionLine}

class AgreementClauseSelection extends AgreementClauseSelectionAbstract {

  override def prepare() = super.prepare()

  override def doIt(): String = {
    if (getRecord_ID > 0) {
      val contractTermCondition = new MSContractTermCondition(getCtx, getRecord_ID, get_TrxName())
      getSelectionKeys.forEach(agreementClauseId => {
        val agreementClause = new MSAgreementClause(getCtx, agreementClauseId, get_TrxName())
        val contractTermConditionLine = new MSContractTermConditionLine(getCtx, 0, get_TrxName())
        contractTermConditionLine.setS_ContractTermCondition_ID(contractTermCondition.get_ID())
        contractTermConditionLine.setS_AgreementClause_ID(agreementClause.getS_AgreementClause_ID)
        contractTermConditionLine.setDescription(agreementClause.getDescription)
        contractTermConditionLine.setSeqNo(agreementClause.getSeqNo)
        contractTermConditionLine.setIsActive(agreementClause.isActive)
        contractTermConditionLine.saveEx()
      })
      contractTermCondition.setTermsAndConditions("")
      contractTermCondition.saveEx()
    }

    "@Ok@"
  }
}
