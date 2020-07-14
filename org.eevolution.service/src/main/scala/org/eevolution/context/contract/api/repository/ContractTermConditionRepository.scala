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

package org.eevolution.context.contract.api.repository

import org.eevolution.context.contract.domain.model._
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import zio.{Has, RIO, ZLayer}

object ContractTermConditionRepository {

	type ContractTermConditionRepository = Has[ContractTermConditionRepository.Service]

	trait Service {
		def getByContractId(contractId: TableDirect): RIO[Any,List[ContractTermCondition]]

		def getByContractTermConditionId(contractTermConditionId: Id): RIO[Any, Option[ContractTermCondition]]

		def getAll: RIO[Any,List[ContractTermCondition]]

		def getAll(clientId: TableDirect): RIO[Any,List[ContractTermCondition]]

	}

	def live(service: Service): ZLayer[Any, Throwable, Has[Service]] = ZLayer.succeed(service)

	def getByContractId(contractId: TableDirect): RIO[ContractTermConditionRepository,List[ContractTermCondition]]
	= RIO.accessM(_.get.getByContractId(contractId))

	def getByContractTermConditionId(contractTermConditionId: Id): RIO[ContractTermConditionRepository, Option[ContractTermCondition]]
	= RIO.accessM(_.get.getByContractTermConditionId(contractTermConditionId))

	def getAll: RIO[ContractTermConditionRepository,List[ContractTermCondition]]
	= RIO.accessM(_.get.getAll)

	def getAll(clientId: TableDirect): RIO[ContractTermConditionRepository,List[ContractTermCondition]]
	= RIO.accessM(_.get.getAll(clientId))
}