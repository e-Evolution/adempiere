package org.eevolution.context.contract.infrastructure.persistence.model

import java.sql.ResultSet
import java.util.{Comparator, Properties}

import org.eevolution.domain.model.X_S_ContractParties

class MSContractParties(ctx: Properties, id: Int, rs: ResultSet, trxName: String)
  extends X_S_ContractParties(ctx: Properties, id: Int, trxName: String) {

  def this(ctx: Properties, id: Int, trxName: String) {
    this(ctx, id, null, trxName)
    load(id, trxName)
  }
  def this(ctx: Properties, rs: ResultSet, trxName: String) {
    this(ctx, 999999999 , rs, trxName)
    load(rs)
  }

  def compare[A](obj1: A, obj2: A)(implicit comparator: Comparator[A]) = {
    comparator.compare(obj1, obj2)
  }
}
