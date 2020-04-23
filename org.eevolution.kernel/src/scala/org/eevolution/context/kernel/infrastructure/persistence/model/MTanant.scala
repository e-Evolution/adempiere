package org.eevolution.context.kernel.infrastructure.persistence.model

import java.sql.ResultSet
import java.util.{Comparator, Properties}

/**
  * Legacy Persistence Tenant Model
  *
  * @param ctx
  * @param id
  * @param rs
  * @param trxName
  */
class MTenant(ctx: Properties, id: Int, rs: ResultSet, trxName: String)
  extends org.compiere.model.MClient(ctx: Properties, id: Int, trxName: String) {

  def this(ctx: Properties, id: Int, trxName: String) {
    this(ctx, id, null, trxName)
    load(id, trxName)
  }

  def this(ctx: Properties, rs: ResultSet, trxName: String) {
    this(ctx, 999999999, rs, trxName)
    load(rs)
  }

  def compare[A](obj1: A, obj2: A)(implicit comparator: Comparator[A]) = {
    comparator.compare(obj1, obj2)
  }

}