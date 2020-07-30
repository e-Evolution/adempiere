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

object TaxRepository {

	type TaxRepository = Has[TaxRepository.Service]

	trait Service {
		def getByCountryId(countryId: Option[Table]): RIO[Any,List[Tax]]

		def getByIsDefault(isDefault: YesNo): RIO[Any,List[Tax]]

		def getByDescription(description: Option[String]): RIO[Any,List[Tax]]

		def getByName(name: String): RIO[Any, Option[Tax]]

		def getByParentTaxId(parentTaxId: Option[Table]): RIO[Any,List[Tax]]

		def getByRegionId(regionId: Option[Table]): RIO[Any,List[Tax]]

		def getByIsSummary(isSummary: YesNo): RIO[Any,List[Tax]]

		def getByTaxId(taxId: Id): RIO[Any, Option[Tax]]

		def getByTaxCategoryId(taxCategoryId: TableDirect): RIO[Any,List[Tax]]

		def getByTaxIndicator(taxIndicator: Option[String]): RIO[Any,List[Tax]]

		def getByToCountryId(toCountryId: Option[Table]): RIO[Any,List[Tax]]

		def getByToRegionId(toRegionId: Option[Table]): RIO[Any,List[Tax]]

		def getAll: RIO[Any,List[Tax]]

		def getAll(clientId: TableDirect): RIO[Any,List[Tax]]

	}

	def live(service: Service): ZLayer[Any, Throwable, Has[Service]] = ZLayer.succeed(service)

	def getByCountryId(countryId: Option[Table]): RIO[TaxRepository,List[Tax]]
	= RIO.accessM(_.get.getByCountryId(countryId))

	def getByIsDefault(isDefault: YesNo): RIO[TaxRepository,List[Tax]]
	= RIO.accessM(_.get.getByIsDefault(isDefault))

	def getByDescription(description: Option[String]): RIO[TaxRepository,List[Tax]]
	= RIO.accessM(_.get.getByDescription(description))

	def getByName(name: String): RIO[TaxRepository, Option[Tax]]
	= RIO.accessM(_.get.getByName(name))

	def getByParentTaxId(parentTaxId: Option[Table]): RIO[TaxRepository,List[Tax]]
	= RIO.accessM(_.get.getByParentTaxId(parentTaxId))

	def getByRegionId(regionId: Option[Table]): RIO[TaxRepository,List[Tax]]
	= RIO.accessM(_.get.getByRegionId(regionId))

	def getByIsSummary(isSummary: YesNo): RIO[TaxRepository,List[Tax]]
	= RIO.accessM(_.get.getByIsSummary(isSummary))

	def getByTaxId(taxId: Id): RIO[TaxRepository, Option[Tax]]
	= RIO.accessM(_.get.getByTaxId(taxId))

	def getByTaxCategoryId(taxCategoryId: TableDirect): RIO[TaxRepository,List[Tax]]
	= RIO.accessM(_.get.getByTaxCategoryId(taxCategoryId))

	def getByTaxIndicator(taxIndicator: Option[String]): RIO[TaxRepository,List[Tax]]
	= RIO.accessM(_.get.getByTaxIndicator(taxIndicator))

	def getByToCountryId(toCountryId: Option[Table]): RIO[TaxRepository,List[Tax]]
	= RIO.accessM(_.get.getByToCountryId(toCountryId))

	def getByToRegionId(toRegionId: Option[Table]): RIO[TaxRepository,List[Tax]]
	= RIO.accessM(_.get.getByToRegionId(toRegionId))

	def getAll: RIO[TaxRepository,List[Tax]]
	= RIO.accessM(_.get.getAll)

	def getAll(clientId: TableDirect): RIO[TaxRepository,List[Tax]]
	= RIO.accessM(_.get.getAll(clientId))
}