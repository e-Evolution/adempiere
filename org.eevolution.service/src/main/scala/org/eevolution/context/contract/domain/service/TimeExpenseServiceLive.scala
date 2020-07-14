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

case class TimeExpenseServiceLive(timeExpenseRepository: TimeExpenseRepository.Service) extends TimeExpenseService.Service {

	def getByBPartnerId(bPartnerId: Table): RIO[Any,List[TimeExpense]] = timeExpenseRepository.getByBPartnerId(bPartnerId)

	def getByDescription(description: Option[String]): RIO[Any,List[TimeExpense]] = timeExpenseRepository.getByDescription(description)

	def getByDocumentNo(documentNo: String): RIO[Any,List[TimeExpense]] = timeExpenseRepository.getByDocumentNo(documentNo)

	def getByTimeExpenseId(timeExpenseId: Id): RIO[Any, Option[TimeExpense]] = timeExpenseRepository.getByTimeExpenseId(timeExpenseId)

	def getByDateReport(dateReport: DateTime = java.time.LocalDateTime.now): RIO[Any,List[TimeExpense]] = timeExpenseRepository.getByDateReport(dateReport)

		def getAll: RIO[Any,List[TimeExpense]] = timeExpenseRepository.getAll

		def getAll(clientId: TableDirect): RIO[Any,List[TimeExpense]] = timeExpenseRepository.getAll(clientId)

}
