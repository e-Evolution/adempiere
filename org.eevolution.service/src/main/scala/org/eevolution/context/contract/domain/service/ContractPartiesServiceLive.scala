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

package org.eevolution.context.contract.domain.service

import org.eevolution.context.contract.domain.model._
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import org.eevolution.context.contract.api.repository._
import org.eevolution.context.contract.api.service._
import zio.RIO

case class ContractPartiesServiceLive(contractPartiesRepository: ContractPartiesRepository.Service) extends ContractPartiesService.Service {

	def getByBPName(bPName: Option[String]): RIO[Any,List[ContractParties]] = contractPartiesRepository.getByBPName(bPName)

	def getByContractId(contractId: Option[TableDirect]): RIO[Any,List[ContractParties]] = contractPartiesRepository.getByContractId(contractId)

	def getByContractPartiesId(contractPartiesId: Id): RIO[Any, Option[ContractParties]] = contractPartiesRepository.getByContractPartiesId(contractPartiesId)

	def getByContractPartiesTypeId(contractPartiesTypeId: Option[TableDirect]): RIO[Any,List[ContractParties]] = contractPartiesRepository.getByContractPartiesTypeId(contractPartiesTypeId)

	def getByNotificationType(notificationType: Option[ListType]): RIO[Any,List[ContractParties]] = contractPartiesRepository.getByNotificationType(notificationType)

	def getByUserId(userId: Option[TableDirect]): RIO[Any,List[ContractParties]] = contractPartiesRepository.getByUserId(userId)

		def getAll: RIO[Any,List[ContractParties]] = contractPartiesRepository.getAll

		def getAll(clientId: TableDirect): RIO[Any,List[ContractParties]] = contractPartiesRepository.getAll(clientId)

}
