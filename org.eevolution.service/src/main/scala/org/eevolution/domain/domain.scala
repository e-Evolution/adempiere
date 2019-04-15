package org.eevolution.domain

import org.compiere.model._
import org.eevolution.domain.model.{MSContract, MSContractLine, MSServiceType}

/**
  * Ubiquitous Language for Service Process Context
  */
package object ubiquitouslanguage{

  type PriceListVersion = MPriceListVersion
  type TimeExpense = MTimeExpense
  type TimeExpenseLine = MTimeExpenseLine
  type PriceList = MPriceList
  type ServiceType = MSServiceType
  type Product = MProduct
  type Contract = MSContract
  type ContractLine = MSContractLine
  type Invoice = MInvoice
  type InvoiceLine = MInvoiceLine
  type Partner = MBPartner
  type DocumentType = MDocType
  type AccountingPeriod = MPeriod
  type Domain = PO
}
