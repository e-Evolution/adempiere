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

object ContractRepository {

	type ContractRepository = Has[ContractRepository.Service]

	trait Service {
		def getByBPartnerId(bPartnerId: Search): RIO[Any,List[Contract]]

		def getByContractId(contractId: Id): RIO[Any, Option[Contract]]

		def getByDateContract(dateContract: DateTime = java.time.LocalDateTime.now): RIO[Any,List[Contract]]

		def getByDateStart(dateStart: Option[DateTime]): RIO[Any,List[Contract]]

		def getByDocumentNo(documentNo: String): RIO[Any,List[Contract]]

		def getByDocTypeId(docTypeId: TableDirect): RIO[Any,List[Contract]]

		def getByDateFinish(dateFinish: Option[DateTime]): RIO[Any,List[Contract]]

		def getByUserId(userId: Option[TableDirect]): RIO[Any,List[Contract]]

		def getAll: RIO[Any,List[Contract]]

		def getAll(clientId: TableDirect): RIO[Any,List[Contract]]

	}

	def live(service: Service): ZLayer[Any, Throwable, Has[Service]] = ZLayer.succeed(service)

	def getByBPartnerId(bPartnerId: Search): RIO[ContractRepository,List[Contract]]
	= RIO.accessM(_.get.getByBPartnerId(bPartnerId))

	def getByContractId(contractId: Id): RIO[ContractRepository, Option[Contract]]
	= RIO.accessM(_.get.getByContractId(contractId))

	def getByDateContract(dateContract: DateTime = java.time.LocalDateTime.now): RIO[ContractRepository,List[Contract]]
	= RIO.accessM(_.get.getByDateContract(dateContract))

	def getByDateStart(dateStart: Option[DateTime]): RIO[ContractRepository,List[Contract]]
	= RIO.accessM(_.get.getByDateStart(dateStart))

	def getByDocumentNo(documentNo: String): RIO[ContractRepository,List[Contract]]
	= RIO.accessM(_.get.getByDocumentNo(documentNo))

	def getByDocTypeId(docTypeId: TableDirect): RIO[ContractRepository,List[Contract]]
	= RIO.accessM(_.get.getByDocTypeId(docTypeId))

	def getByDateFinish(dateFinish: Option[DateTime]): RIO[ContractRepository,List[Contract]]
	= RIO.accessM(_.get.getByDateFinish(dateFinish))

	def getByUserId(userId: Option[TableDirect]): RIO[ContractRepository,List[Contract]]
	= RIO.accessM(_.get.getByUserId(userId))

	def getAll: RIO[ContractRepository,List[Contract]]
	= RIO.accessM(_.get.getAll)

	def getAll(clientId: TableDirect): RIO[ContractRepository,List[Contract]]
	= RIO.accessM(_.get.getAll(clientId))
}