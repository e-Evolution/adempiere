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

case class ContractServiceLive(contractRepository: ContractRepository.Service) extends ContractService.Service {

	def getByBPartnerId(bPartnerId: Search): RIO[Any,List[Contract]] = contractRepository.getByBPartnerId(bPartnerId)

	def getByContractId(contractId: Id): RIO[Any, Option[Contract]] = contractRepository.getByContractId(contractId)

	def getByDateContract(dateContract: DateTime = java.time.LocalDateTime.now): RIO[Any,List[Contract]] = contractRepository.getByDateContract(dateContract)

	def getByDateStart(dateStart: Option[DateTime]): RIO[Any,List[Contract]] = contractRepository.getByDateStart(dateStart)

	def getByDocumentNo(documentNo: String): RIO[Any,List[Contract]] = contractRepository.getByDocumentNo(documentNo)

	def getByDocTypeId(docTypeId: TableDirect): RIO[Any,List[Contract]] = contractRepository.getByDocTypeId(docTypeId)

	def getByDateFinish(dateFinish: Option[DateTime]): RIO[Any,List[Contract]] = contractRepository.getByDateFinish(dateFinish)

	def getByUserId(userId: Option[TableDirect]): RIO[Any,List[Contract]] = contractRepository.getByUserId(userId)

		def getAll: RIO[Any,List[Contract]] = contractRepository.getAll

		def getAll(clientId: TableDirect): RIO[Any,List[Contract]] = contractRepository.getAll(clientId)

}
