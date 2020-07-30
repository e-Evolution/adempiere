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
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 **/

package org.eevolution.context.tax.infrastructure.repository

import org.eevolution.context.tax.domain.model._
import org.eevolution.context.tax.api.repository._
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import org.eevolution.context.kernel.infrastructure.database.context._
import zio.RIO

import scala.util.Try

case class TaxDeclarationAcctRepositoryLive() extends TaxDeclarationAcctRepository.Service with TaxDeclarationAcctMapping {
	override def getByDescription(description: Option[String]): RIO[Any,List[TaxDeclarationAcct]] =
		for {
			taxDeclarationAcctList <- RIO.fromTry {
				Try {
					val taxDeclarationAcctList = run(query[TaxDeclarationAcct].filter(_.description == lift(description)))
					taxDeclarationAcctList
			}
		}
	} yield taxDeclarationAcctList

	override def getByTaxDeclarationId(taxDeclarationId: TableDirect): RIO[Any,List[TaxDeclarationAcct]] =
		for {
			taxDeclarationAcctList <- RIO.fromTry {
				Try {
					val taxDeclarationAcctList = run(query[TaxDeclarationAcct].filter(_.taxDeclarationId == lift(taxDeclarationId)))
					taxDeclarationAcctList
			}
		}
	} yield taxDeclarationAcctList

	override def getByTaxDeclarationAcctId(taxDeclarationAcctId: Id): RIO[Any, Option[TaxDeclarationAcct]] =
		for {
			taxDeclarationAcct <- RIO.fromTry {
				Try {
					val taxDeclarationAcct = run(query[TaxDeclarationAcct].filter(_.taxDeclarationAcctId == lift(taxDeclarationAcctId))).headOption
					taxDeclarationAcct
				}
			}
		} yield taxDeclarationAcct

	override def getAll: RIO[Any,List[TaxDeclarationAcct]] =
		for {
			taxDeclarationAcctList <- RIO.fromTry {
				Try {
					val taxDeclarationAcctList = run(query[TaxDeclarationAcct])
					taxDeclarationAcctList
			}
		}
	} yield taxDeclarationAcctList

	override def getAll(clientId: TableDirect): RIO[Any,List[TaxDeclarationAcct]] =
		for {
			taxDeclarationAcctList <- RIO.fromTry {
				Try {
					val taxDeclarationAcctList = run(query[TaxDeclarationAcct].filter(_.clientId == lift(clientId)))
					taxDeclarationAcctList
			}
		}
	} yield taxDeclarationAcctList

}