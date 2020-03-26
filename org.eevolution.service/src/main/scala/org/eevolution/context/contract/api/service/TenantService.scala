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


package org.eevolution.context.contract.api.service

import org.eevolution.context.contract.api.Context.Context
import org.eevolution.context.contract.api.repository.TenantRepository
import org.eevolution.context.contract.api.service.TenantService.Service
import org.eevolution.context.contract.domain.ubiquitouslanguage.{Id, Tenant}
import zio.{Has, ZIO, ZLayer}

/**
  * Standard Implementation for Domain Tenant Service
  */
object TenantService {

   type TenantService = Has[Service]


  trait Service {
    def getById(id: Id): ZIO[Context, Throwable, Tenant]
  }
   def live : ZLayer[Has[TenantRepository.Service], Nothing, Has[Service]]  =   ZLayer.fromService[TenantRepository.Service, Service] {  tenantRepository =>
    TenantServiceLive(tenantRepository)
  }
}

case class TenantServiceLive(tenantRepository : TenantRepository.Service ) extends Service {
  override def getById(id: Id): ZIO[Context, Throwable, Tenant] = ???
}