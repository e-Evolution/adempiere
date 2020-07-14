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

package org.eevolution.context.contract.infrastructure.repository

import org.eevolution.context.contract.domain.model._
import org.eevolution.context.contract.api.repository._
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import org.eevolution.context.kernel.infrastructure.database.context._
import zio.RIO

import scala.util.Try

case class TimeExpenseRepositoryLive() extends TimeExpenseRepository.Service with TimeExpenseMapping {
	override def getByBPartnerId(bPartnerId: Table): RIO[Any,List[TimeExpense]] =
		for {
			timeExpenseList <- RIO.fromTry {
				Try {
					val timeExpenseList = run(queryTimeExpense.filter(_.bPartnerId == lift(bPartnerId)))
					timeExpenseList
			}
		}
	} yield timeExpenseList

	override def getByDescription(description: Option[String]): RIO[Any,List[TimeExpense]] =
		for {
			timeExpenseList <- RIO.fromTry {
				Try {
					val timeExpenseList = run(queryTimeExpense.filter(_.description == lift(description)))
					timeExpenseList
			}
		}
	} yield timeExpenseList

	override def getByDocumentNo(documentNo: String): RIO[Any,List[TimeExpense]] =
		for {
			timeExpenseList <- RIO.fromTry {
				Try {
					val timeExpenseList = run(queryTimeExpense.filter(_.documentNo == lift(documentNo)))
					timeExpenseList
			}
		}
	} yield timeExpenseList

	override def getByTimeExpenseId(timeExpenseId: Id): RIO[Any, Option[TimeExpense]] =
		for {
			timeExpense <- RIO.fromTry {
				Try {
					val timeExpense = run(queryTimeExpense.filter(_.timeExpenseId == lift(timeExpenseId))).headOption
					timeExpense
				}
			}
		} yield timeExpense

	override def getByDateReport(dateReport: DateTime = java.time.LocalDateTime.now): RIO[Any,List[TimeExpense]] =
		for {
			timeExpenseList <- RIO.fromTry {
				Try {
					val timeExpenseList = run(queryTimeExpense.filter(_.dateReport == lift(dateReport)))
					timeExpenseList
			}
		}
	} yield timeExpenseList

	override def getAll: RIO[Any,List[TimeExpense]] =
		for {
			timeExpenseList <- RIO.fromTry {
				Try {
					val timeExpenseList = run(queryTimeExpense)
					timeExpenseList
			}
		}
	} yield timeExpenseList

	override def getAll(clientId: TableDirect): RIO[Any,List[TimeExpense]] =
		for {
			timeExpenseList <- RIO.fromTry {
				Try {
					val timeExpenseList = run(queryTimeExpense.filter(_.clientId == lift(clientId)))
					timeExpenseList
			}
		}
	} yield timeExpenseList

}