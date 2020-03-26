package org.eevolution.context.contract.domain.process

/*
Generate Invoice from Time Expense Report Component is a contract to implement this business logic
 */
trait GenerateInvoicesFromTimeExpenseReportComponent {
  val generateInvoicesFromTimeExpenseReport: GenerateInvoicesFromTimeExpenseReportTrait

  trait GenerateInvoicesFromTimeExpenseReportTrait {
    def doIt(): String
  }

}
