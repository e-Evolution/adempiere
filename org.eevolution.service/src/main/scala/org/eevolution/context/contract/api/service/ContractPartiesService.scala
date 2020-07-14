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

object ContractPartiesService {

	type ContractPartiesService = Has[Service]

	trait Service {
		def getByBPName(bPName: Option[String]): RIO[Any,List[ContractParties]]

		def getByContractId(contractId: Option[TableDirect]): RIO[Any,List[ContractParties]]

		def getByContractPartiesId(contractPartiesId: Id): RIO[Any, Option[ContractParties]]

		def getByContractPartiesTypeId(contractPartiesTypeId: Option[TableDirect]): RIO[Any,List[ContractParties]]

		def getByNotificationType(notificationType: Option[ListType]): RIO[Any,List[ContractParties]]

		def getByUserId(userId: Option[TableDirect]): RIO[Any,List[ContractParties]]

		def getAll: RIO[Any,List[ContractParties]]

		def getAll(clientId: TableDirect): RIO[Any,List[ContractParties]]

	}

	def live: ZLayer[ContractPartiesRepository.ContractPartiesRepository, Nothing, Has[Service]]
	= ZLayer.fromService[ContractPartiesRepository.Service, Service] { contractPartiesRepository => ContractPartiesServiceLive(contractPartiesRepository) }

	def getByBPName(bPName: Option[String]): RIO[ContractPartiesService,List[ContractParties]]
	= RIO.accessM(_.get.getByBPName(bPName))

	def getByContractId(contractId: Option[TableDirect]): RIO[ContractPartiesService,List[ContractParties]]
	= RIO.accessM(_.get.getByContractId(contractId))

	def getByContractPartiesId(contractPartiesId: Id): RIO[ContractPartiesService, Option[ContractParties]]
	= RIO.accessM(_.get.getByContractPartiesId(contractPartiesId))

	def getByContractPartiesTypeId(contractPartiesTypeId: Option[TableDirect]): RIO[ContractPartiesService,List[ContractParties]]
	= RIO.accessM(_.get.getByContractPartiesTypeId(contractPartiesTypeId))

	def getByNotificationType(notificationType: Option[ListType]): RIO[ContractPartiesService,List[ContractParties]]
	= RIO.accessM(_.get.getByNotificationType(notificationType))

	def getByUserId(userId: Option[TableDirect]): RIO[ContractPartiesService,List[ContractParties]]
	= RIO.accessM(_.get.getByUserId(userId))

	def getAll: RIO[ContractPartiesService,List[ContractParties]]
	= RIO.accessM(_.get.getAll)

	def getAll(clientId: TableDirect): RIO[ContractPartiesService,List[ContractParties]]
	= RIO.accessM(_.get.getAll(clientId))

}