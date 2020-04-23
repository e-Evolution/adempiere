/**
  * Copyright (C) 2003-2020, e-Evolution Consultants S.A. , http://www.e-evolution.com
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  * You should have received a copy of the GNU General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
  * Created by victor.perez@e-evolution.com , www.e-evolution.com
  **/

package org.eevolution.context.kernel.infrastructure.respository

import org.eevolution.context.kernel.api.repository.OrganizationRepository
import org.eevolution.context.kernel.domain.model.Organization
import org.eevolution.context.kernel.domain.ubiquitouslanguage.Id
import org.eevolution.context.kernel.infrastructure.database.context._
import zio.Task

import scala.util.Try

final case class OrganizationRepositoryLive() extends OrganizationRepository.Service with OrganizationMapping {
  override def getById(id: Id): Task[Option[Organization]] =
    for {
      tenant <- Task.fromTry {
        Try {
          //val result = prepare(quotePayment.filter(_.paymentId == lift(id)))(trx.getConnectioin).executeQuery()
          //val payment = new MPayment(ctx, result, trx.getTrxName)
          val tenant = run(quoteOrganization.filter(_.organizationId == lift(id))).headOption
          tenant
        }
      }
    } yield tenant
}