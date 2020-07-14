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

case class TimeExpenseLineRepositoryLive() extends TimeExpenseLineRepository.Service with TimeExpenseLineMapping {
	override def getByDescription(description: Option[String]): RIO[Any,List[TimeExpenseLine]] =
		for {
			timeExpenseLineList <- RIO.fromTry {
				Try {
					val timeExpenseLineList = run(queryTimeExpenseLine.filter(_.description == lift(description)))
					timeExpenseLineList
			}
		}
	} yield timeExpenseLineList

	override def getByTimeExpenseLineId(timeExpenseLineId: Id): RIO[Any, Option[TimeExpenseLine]] =
		for {
			timeExpenseLine <- RIO.fromTry {
				Try {
					val timeExpenseLine = run(queryTimeExpenseLine.filter(_.timeExpenseLineId == lift(timeExpenseLineId))).headOption
					timeExpenseLine
				}
			}
		} yield timeExpenseLine

	override def getByLine(line: Number): RIO[Any,List[TimeExpenseLine]] =
		for {
			timeExpenseLineList <- RIO.fromTry {
				Try {
					val timeExpenseLineList = run(queryTimeExpenseLine.filter(_.line == lift(line)))
					timeExpenseLineList
			}
		}
	} yield timeExpenseLineList

	override def getAll: RIO[Any,List[TimeExpenseLine]] =
		for {
			timeExpenseLineList <- RIO.fromTry {
				Try {
					val timeExpenseLineList = run(queryTimeExpenseLine)
					timeExpenseLineList
			}
		}
	} yield timeExpenseLineList

	override def getAll(clientId: TableDirect): RIO[Any,List[TimeExpenseLine]] =
		for {
			timeExpenseLineList <- RIO.fromTry {
				Try {
					val timeExpenseLineList = run(queryTimeExpenseLine.filter(_.clientId == lift(clientId)))
					timeExpenseLineList
			}
		}
	} yield timeExpenseLineList

}