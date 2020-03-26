/**
  * Copyright (C) 2003-2018, e-Evolution Consultants S.A. , http://www.e-evolution.com
  * This program is free software, you can redistribute it and/or modify it
  * under the terms version 2 of the GNU General Public License as published
  * or (at your option) any later version.
  * by the Free Software Foundation. This program is distributed in the hope
  * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied
  * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
  * See the GNU General Public License for more details.
  * You should have received a copy of the GNU General Public License along
  * with this program, if not, write to the Free Software Foundation, Inc.,
  * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
  * For the text or an alternative of this public license, you may reach us
  * or via info@adempiere.net or http://www.adempiere.net/license.html
  * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
  * Created by victor.perez@e-evolution.com , www.e-evolution.com
  */

package org.eevolution.context.contract.infrastructure.persistence.model

import java.sql.ResultSet
import java.util.{Comparator, Properties}

import org.eevolution.domain.model.X_S_ContractLine


/**
  * Contract Line Entity
  * @param ctx
  * @param id
  * @param rs
  * @param trxName
  */
class MSContractLine(ctx: Properties, id: Int, rs: ResultSet, trxName: String)
  extends X_S_ContractLine(ctx: Properties, id: Int, trxName: String) {

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