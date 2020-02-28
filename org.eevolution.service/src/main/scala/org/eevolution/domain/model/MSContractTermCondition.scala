package org.eevolution.domain.model

import java.sql.ResultSet
import java.util.{Comparator, Properties}

class MSContractTermCondition (ctx: Properties, id: Int, rs: ResultSet, trxName: String)
  extends X_S_ContractTermCondition(ctx: Properties, id: Int, trxName: String){

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
