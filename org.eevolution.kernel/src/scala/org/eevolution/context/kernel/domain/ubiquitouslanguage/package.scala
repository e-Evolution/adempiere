package org.eevolution.context.kernel.domain

import java.time.{LocalDate, LocalDateTime}

import org.compiere.model.PO

package object ubiquitouslanguage {

  type Tenant = model.Tenant
  type Organization = model.Organization
  type User = model.User
  type Quantity = BigDecimal
  type Amount = BigDecimal
  type Id = Int
  type TableDirect = Int
  type Table = Int
  type Search = Int
  type ListType = String
  type Number = BigDecimal
  type CostPrice = BigDecimal
  type YesNo = Boolean
  type Button = String
  type Yes = true
  type No = false
  type DateTime = LocalDateTime
  type Date = LocalDate
  type Text = String
  type Domain = PO
  type AttributeSetInstance = Int
  type ResourceAssignment = Int


  sealed trait Maybe

  sealed trait Required extends Maybe

  sealed trait Once extends Maybe


}
