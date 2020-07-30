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

package org.eevolution.context.tax.api.service

import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import org.eevolution.context.tax.domain.model._
import org.eevolution.context.tax.api.repository._
import org.eevolution.context.tax.domain.service._
import zio.{Has, RIO, ZLayer}

object TaxTypeService {

	type TaxTypeService = Has[Service]

	trait Service {
		def getByDescription(description: Option[String]): RIO[Any,List[TaxType]]

		def getByName(name: String): RIO[Any, Option[TaxType]]

		def getByValue(value: String): RIO[Any, Option[TaxType]]

		def getByTaxTypeId(taxTypeId: Id): RIO[Any, Option[TaxType]]

		def getAll: RIO[Any,List[TaxType]]

		def getAll(clientId: TableDirect): RIO[Any,List[TaxType]]

	}

	def live: ZLayer[TaxTypeRepository.TaxTypeRepository, Nothing, Has[Service]]
	= ZLayer.fromService[TaxTypeRepository.Service, Service] { taxTypeRepository => TaxTypeServiceLive(taxTypeRepository) }

	def getByDescription(description: Option[String]): RIO[TaxTypeService,List[TaxType]]
	= RIO.accessM(_.get.getByDescription(description))

	def getByName(name: String): RIO[TaxTypeService, Option[TaxType]]
	= RIO.accessM(_.get.getByName(name))

	def getByValue(value: String): RIO[TaxTypeService, Option[TaxType]]
	= RIO.accessM(_.get.getByValue(value))

	def getByTaxTypeId(taxTypeId: Id): RIO[TaxTypeService, Option[TaxType]]
	= RIO.accessM(_.get.getByTaxTypeId(taxTypeId))

	def getAll: RIO[TaxTypeService,List[TaxType]]
	= RIO.accessM(_.get.getAll)

	def getAll(clientId: TableDirect): RIO[TaxTypeService,List[TaxType]]
	= RIO.accessM(_.get.getAll(clientId))

}