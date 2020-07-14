/**
 * Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
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
 * Email: emeris.hernandez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by emeris.hernandez@e-evolution.com , www.e-evolution.com
 **/

package org.eevolution.context.contract.api.service

import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import org.eevolution.context.contract.domain.model._
import org.eevolution.context.contract.api.repository._
import org.eevolution.context.contract.domain.service._
import zio.{Has, RIO, ZLayer}

object ServiceTypeService {

	type ServiceTypeService = Has[Service]

	trait Service {
		def getByDescription(description: Option[String]): RIO[Any,List[ServiceType]]

		def getByName(name: String): RIO[Any, Option[ServiceType]]

		def getByValue(value: Option[String]): RIO[Any, Option[ServiceType]]

		def getByServiceTypeId(serviceTypeId: Id): RIO[Any, Option[ServiceType]]

		def getAll: RIO[Any,List[ServiceType]]

		def getAll(clientId: TableDirect): RIO[Any,List[ServiceType]]

	}

	def live: ZLayer[ServiceTypeRepository.ServiceTypeRepository, Nothing, Has[Service]]
	= ZLayer.fromService[ServiceTypeRepository.Service, Service] { serviceTypeRepository => ServiceTypeServiceLive(serviceTypeRepository) }

	def getByDescription(description: Option[String]): RIO[ServiceTypeService,List[ServiceType]]
	= RIO.accessM(_.get.getByDescription(description))

	def getByName(name: String): RIO[ServiceTypeService, Option[ServiceType]]
	= RIO.accessM(_.get.getByName(name))

	def getByValue(value: Option[String]): RIO[ServiceTypeService, Option[ServiceType]]
	= RIO.accessM(_.get.getByValue(value))

	def getByServiceTypeId(serviceTypeId: Id): RIO[ServiceTypeService, Option[ServiceType]]
	= RIO.accessM(_.get.getByServiceTypeId(serviceTypeId))

	def getAll: RIO[ServiceTypeService,List[ServiceType]]
	= RIO.accessM(_.get.getAll)

	def getAll(clientId: TableDirect): RIO[ServiceTypeService,List[ServiceType]]
	= RIO.accessM(_.get.getAll(clientId))

}