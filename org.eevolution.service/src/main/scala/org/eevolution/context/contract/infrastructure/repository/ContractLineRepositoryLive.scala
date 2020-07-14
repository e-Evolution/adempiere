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

case class ContractLineRepositoryLive() extends ContractLineRepository.Service with ContractLineMapping {
	override def getByContractId(contractId: TableDirect): RIO[Any,List[ContractLine]] =
		for {
			contractLineList <- RIO.fromTry {
				Try {
					val contractLineList = run(query[ContractLine].filter(_.contractId == lift(contractId)))
					contractLineList
			}
		}
	} yield contractLineList

	override def getByContractLineId(contractLineId: Id): RIO[Any, Option[ContractLine]] =
		for {
			contractLine <- RIO.fromTry {
				Try {
					val contractLine = run(query[ContractLine].filter(_.contractLineId == lift(contractLineId))).headOption
					contractLine
				}
			}
		} yield contractLine

	override def getByDescription(description: Option[String]): RIO[Any,List[ContractLine]] =
		for {
			contractLineList <- RIO.fromTry {
				Try {
					val contractLineList = run(query[ContractLine].filter(_.description == lift(description)))
					contractLineList
			}
		}
	} yield contractLineList

	override def getByLineNetAmt(lineNetAmt: Amount): RIO[Any,List[ContractLine]] =
		for {
			contractLineList <- RIO.fromTry {
				Try {
					val contractLineList = run(query[ContractLine].filter(_.lineNetAmt == lift(lineNetAmt)))
					contractLineList
			}
		}
	} yield contractLineList

	override def getByLine(line: Number): RIO[Any,List[ContractLine]] =
		for {
			contractLineList <- RIO.fromTry {
				Try {
					val contractLineList = run(query[ContractLine].filter(_.line == lift(line)))
					contractLineList
			}
		}
	} yield contractLineList

	override def getAll: RIO[Any,List[ContractLine]] =
		for {
			contractLineList <- RIO.fromTry {
				Try {
					val contractLineList = run(query[ContractLine])
					contractLineList
			}
		}
	} yield contractLineList

	override def getAll(clientId: TableDirect): RIO[Any,List[ContractLine]] =
		for {
			contractLineList <- RIO.fromTry {
				Try {
					val contractLineList = run(query[ContractLine].filter(_.clientId == lift(clientId)))
					contractLineList
			}
		}
	} yield contractLineList

}