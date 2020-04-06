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

import org.compiere.model.{I_M_Product, Query}
import org.compiere.util.Env
import org.eevolution.context.contract.api.Context.Context
import org.eevolution.context.contract.api.repository.ServiceTypeRepository.Service
import org.eevolution.context.contract.domain.ubiquitouslanguage.{Id, Product, ServiceType}
import org.eevolution.context.contract.infrastructure.persistence.model.I_S_ServiceType
import zio.ZIO

import scala.jdk.CollectionConverters._
import scala.util.Try

/**
 * Service Type Repository Implementation the infrastructure for Service Type Entity
 */

case class ServiceTypeRepositoryLive(contextService: Context) extends Service {
  override def getById(id: Id): ZIO[Context, Throwable, ServiceType] = ZIO.fromTry(Try {
    val parameters = List(id)
    val whereClause = s"${I_S_ServiceType.COLUMNNAME_S_ServiceType_ID}=?"
    new Query(Env.getCtx(), I_S_ServiceType.Table_Name, whereClause, null)
      .setClient_ID()
      .setParameters(parameters.asJava)
      .first()
  })


  override def getProduct(serviceTypeId: Id): ZIO[Context, Throwable, Product] = ZIO.fromTry(Try {
    val parameters = List(serviceTypeId)
    val whereClause = s"${I_M_Product.COLUMNNAME_S_ServiceType_ID}=?"
    new Query(Env.getCtx(), I_M_Product.Table_Name, whereClause, null)
      .setClient_ID()
      .setParameters(parameters)
      .first()
  })
}