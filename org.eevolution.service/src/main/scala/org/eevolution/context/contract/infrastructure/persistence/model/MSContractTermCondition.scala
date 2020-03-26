package org.eevolution.context.contract.infrastructure.persistence.model

import java.sql.ResultSet
import java.util.{Comparator, Properties}

import org.compiere.model.Query
import org.eevolution.domain.model.{I_S_ContractTermConditionLine, X_S_ContractTermCondition}

class MSContractTermCondition(ctx: Properties, id: Int, rs: ResultSet, trxName: String)
  extends X_S_ContractTermCondition(ctx: Properties, id: Int, trxName: String) {

  def this(ctx: Properties, id: Int, trxName: String) {
    this(ctx, id, null, trxName)
    load(id, trxName)
  }

  def this(ctx: Properties, rs: ResultSet, trxName: String) {
    this(ctx, 999999999, rs, trxName)
    load(rs)
  }


  override def beforeSave (newRecord: Boolean) = {
    val contractTermCondition = new StringBuilder()
    getContractTermConditionLines.forEach(contractTermConditionLine => {
      val agreementClause = Option(contractTermConditionLine.getDescription).getOrElse("")
      contractTermCondition.append(agreementClause).append(" ")
    })
    setTermsAndConditions(contractTermCondition.toString)
    true
  }

  def getContractTermConditionLines = {
    val whereClause = s"${I_S_ContractTermConditionLine.COLUMNNAME_S_ContractTermCondition_ID}=?"
    val contractTermConditionLines = new Query(getCtx, I_S_ContractTermConditionLine.Table_Name, whereClause, get_TableName())
      .setClient_ID()
      .setParameters(get_ID())
      .setOrderBy(I_S_ContractTermConditionLine.COLUMNNAME_SeqNo)
      .list[MSContractTermConditionLine]()
    contractTermConditionLines
  }

  def compare[A](obj1: A, obj2: A)(implicit comparator: Comparator[A]) = {
    comparator.compare(obj1, obj2)
  }


}
