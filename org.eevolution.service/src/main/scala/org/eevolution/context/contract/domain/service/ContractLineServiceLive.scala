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

case class ContractLineServiceLive(contractLineRepository: ContractLineRepository.Service) extends ContractLineService.Service {

	def getByContractId(contractId: TableDirect): RIO[Any,List[ContractLine]] = contractLineRepository.getByContractId(contractId)

	def getByContractLineId(contractLineId: Id): RIO[Any, Option[ContractLine]] = contractLineRepository.getByContractLineId(contractLineId)

	def getByDescription(description: Option[String]): RIO[Any,List[ContractLine]] = contractLineRepository.getByDescription(description)

	def getByLineNetAmt(lineNetAmt: Amount): RIO[Any,List[ContractLine]] = contractLineRepository.getByLineNetAmt(lineNetAmt)

	def getByLine(line: Number): RIO[Any,List[ContractLine]] = contractLineRepository.getByLine(line)

		def getAll: RIO[Any,List[ContractLine]] = contractLineRepository.getAll

		def getAll(clientId: TableDirect): RIO[Any,List[ContractLine]] = contractLineRepository.getAll(clientId)

}