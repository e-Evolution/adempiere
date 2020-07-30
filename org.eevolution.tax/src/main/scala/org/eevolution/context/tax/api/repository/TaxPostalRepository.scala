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

object TaxPostalRepository {

	type TaxPostalRepository = Has[TaxPostalRepository.Service]

	trait Service {
		def getByTaxId(taxId: TableDirect): RIO[Any,List[TaxPostal]]

		def getByTaxPostalId(taxPostalId: Id): RIO[Any, Option[TaxPostal]]

		def getByPostal(postal: String): RIO[Any,List[TaxPostal]]

		def getByPostalTo(postalTo: Option[String]): RIO[Any,List[TaxPostal]]

		def getAll: RIO[Any,List[TaxPostal]]

		def getAll(clientId: TableDirect): RIO[Any,List[TaxPostal]]

	}

	def live(service: Service): ZLayer[Any, Throwable, Has[Service]] = ZLayer.succeed(service)

	def getByTaxId(taxId: TableDirect): RIO[TaxPostalRepository,List[TaxPostal]]
	= RIO.accessM(_.get.getByTaxId(taxId))

	def getByTaxPostalId(taxPostalId: Id): RIO[TaxPostalRepository, Option[TaxPostal]]
	= RIO.accessM(_.get.getByTaxPostalId(taxPostalId))

	def getByPostal(postal: String): RIO[TaxPostalRepository,List[TaxPostal]]
	= RIO.accessM(_.get.getByPostal(postal))

	def getByPostalTo(postalTo: Option[String]): RIO[TaxPostalRepository,List[TaxPostal]]
	= RIO.accessM(_.get.getByPostalTo(postalTo))

	def getAll: RIO[TaxPostalRepository,List[TaxPostal]]
	= RIO.accessM(_.get.getAll)

	def getAll(clientId: TableDirect): RIO[TaxPostalRepository,List[TaxPostal]]
	= RIO.accessM(_.get.getAll(clientId))
}