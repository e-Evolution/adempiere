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

package org.eevolution.context.contract.infrastructure.repository

import org.compiere.model.Query
import org.compiere.util.Env
import org.eevolution.context.contract.api.Context.Context
import org.eevolution.context.contract.api.repository.ContractLineRepository.Service
import org.eevolution.context.contract.domain.ubiquitouslanguage.{ContractLine, Id}
import org.eevolution.domain.model.I_S_ContractLine
import zio.ZIO

import scala.collection.immutable.List
import scala.jdk.CollectionConverters._
import scala.util.Try

/**
 * Contact Line Repository Implementation the infrastructure for Contract Line Entity
 */

case class ContractLineRepositoryLive(contextService: Context) extends Service {
  override def getById(id: Id): ZIO[Context, Throwable, ContractLine] = ZIO.fromTry(Try {
    val whereClause = s"${I_S_ContractLine.COLUMNNAME_S_ContractLine_ID}=?"
    new Query(Env.getCtx, I_S_ContractLine.Table_Name, whereClause, null)
      .setClient_ID()
      .setParameters(id)
      .first()
  })

  override def getByContractId(contractId: Id): ZIO[Context, Throwable, List[ContractLine]] = ZIO.fromTry(Try {
    val parameters = List(contractId)
    val whereClause = s"${I_S_ContractLine.COLUMNNAME_S_Contract_ID}=?"
    new Query(Env.getCtx, I_S_ContractLine.Table_Name, whereClause, null)
      .setClient_ID()
      .setParameters(parameters.asJava)
      .list().asScala.toList
  })

  override def query(whereClause: String, parameters: List[Object]): ZIO[Context, Throwable, List[ContractLine]] = ZIO.fromTry(Try {
    new Query(Env.getCtx, I_S_ContractLine.Table_Name, whereClause, null)
      .setClient_ID()
      .setParameters(parameters.asJava)
      .list().asScala.toList
  })
}