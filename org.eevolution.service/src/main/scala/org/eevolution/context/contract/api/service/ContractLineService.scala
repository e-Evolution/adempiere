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

object ContractLineService {

	type ContractLineService = Has[Service]

	trait Service {
		def getByContractId(contractId: TableDirect): RIO[Any,List[ContractLine]]

		def getByContractLineId(contractLineId: Id): RIO[Any, Option[ContractLine]]

		def getByDescription(description: Option[String]): RIO[Any,List[ContractLine]]

		def getByLineNetAmt(lineNetAmt: Amount): RIO[Any,List[ContractLine]]

		def getByLine(line: Number): RIO[Any,List[ContractLine]]

		def getAll: RIO[Any,List[ContractLine]]

		def getAll(clientId: TableDirect): RIO[Any,List[ContractLine]]

	}

	def live: ZLayer[ContractLineRepository.ContractLineRepository, Nothing, Has[Service]]
	= ZLayer.fromService[ContractLineRepository.Service, Service] { contractLineRepository => ContractLineServiceLive(contractLineRepository) }

	def getByContractId(contractId: TableDirect): RIO[ContractLineService,List[ContractLine]]
	= RIO.accessM(_.get.getByContractId(contractId))

	def getByContractLineId(contractLineId: Id): RIO[ContractLineService, Option[ContractLine]]
	= RIO.accessM(_.get.getByContractLineId(contractLineId))

	def getByDescription(description: Option[String]): RIO[ContractLineService,List[ContractLine]]
	= RIO.accessM(_.get.getByDescription(description))

	def getByLineNetAmt(lineNetAmt: Amount): RIO[ContractLineService,List[ContractLine]]
	= RIO.accessM(_.get.getByLineNetAmt(lineNetAmt))

	def getByLine(line: Number): RIO[ContractLineService,List[ContractLine]]
	= RIO.accessM(_.get.getByLine(line))

	def getAll: RIO[ContractLineService,List[ContractLine]]
	= RIO.accessM(_.get.getAll)

	def getAll(clientId: TableDirect): RIO[ContractLineService,List[ContractLine]]
	= RIO.accessM(_.get.getAll(clientId))

}