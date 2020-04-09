package org.eevolution.context.contract.domain

import java.time.{LocalDate, LocalDateTime}

import org.compiere.model._
import org.eevolution.context.contract.domain
import org.eevolution.context.contract.infrastructure.persistence.model
import org.eevolution.context.contract.infrastructure.persistence.model.MSContract


package object api {
}

/**
 * Ubiquitous Language for Service Process Context
 */
package object ubiquitouslanguage {

  type Tenant = domain.model.Tenant
  type Organization = domain.model.Organization
  type User = domain.model.User
  //type Currency = domain.model.Currency

  type PriceListVersion = MPriceListVersion
  type TimeExpense = MTimeExpense
  type TimeExpenseLine = MTimeExpenseLine
  type PriceList = MPriceList
  type ServiceType = model.MSServiceType
  type Product = MProduct
  type Contract = MSContract
  type ContractLine = model.MSContractLine
  type Invoice = MInvoice
  type InvoiceLine = MInvoiceLine
  type Partner = MBPartner
  type DocumentType = MDocType
  type AccountingPeriod = MPeriod

  type Quantity = BigDecimal
  type Amount = BigDecimal
  type Id = Int
  type TableDirect = Int
  type Table = Int
  type Search = Int
  type List = String
  type Number = BigDecimal
  type YesNo = Boolean
  type Button = String
  type Yes = true
  type No = false
  type DateTime = LocalDateTime
  type Date = LocalDate
  type Text = String
  type Domain = PO


  sealed trait Maybe

  sealed trait Required extends Maybe

  sealed trait Once extends Maybe
}
