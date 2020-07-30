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

package org.eevolution.context.tax.api.repository

import org.eevolution.context.tax.domain.model._
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import zio.{Has, RIO, ZLayer}

object TaxDeclarationAcctRepository {

	type TaxDeclarationAcctRepository = Has[TaxDeclarationAcctRepository.Service]

	trait Service {
		def getByDescription(description: Option[String]): RIO[Any,List[TaxDeclarationAcct]]

		def getByTaxDeclarationId(taxDeclarationId: TableDirect): RIO[Any,List[TaxDeclarationAcct]]

		def getByTaxDeclarationAcctId(taxDeclarationAcctId: Id): RIO[Any, Option[TaxDeclarationAcct]]

		def getAll: RIO[Any,List[TaxDeclarationAcct]]

		def getAll(clientId: TableDirect): RIO[Any,List[TaxDeclarationAcct]]

	}

	def live(service: Service): ZLayer[Any, Throwable, Has[Service]] = ZLayer.succeed(service)

	def getByDescription(description: Option[String]): RIO[TaxDeclarationAcctRepository,List[TaxDeclarationAcct]]
	= RIO.accessM(_.get.getByDescription(description))

	def getByTaxDeclarationId(taxDeclarationId: TableDirect): RIO[TaxDeclarationAcctRepository,List[TaxDeclarationAcct]]
	= RIO.accessM(_.get.getByTaxDeclarationId(taxDeclarationId))

	def getByTaxDeclarationAcctId(taxDeclarationAcctId: Id): RIO[TaxDeclarationAcctRepository, Option[TaxDeclarationAcct]]
	= RIO.accessM(_.get.getByTaxDeclarationAcctId(taxDeclarationAcctId))

	def getAll: RIO[TaxDeclarationAcctRepository,List[TaxDeclarationAcct]]
	= RIO.accessM(_.get.getAll)

	def getAll(clientId: TableDirect): RIO[TaxDeclarationAcctRepository,List[TaxDeclarationAcct]]
	= RIO.accessM(_.get.getAll(clientId))
}