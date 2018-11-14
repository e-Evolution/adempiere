/**
  * Copyright (C) 2003-2018, e-Evolution Consultants S.A. , http://www.e-evolution.com
  * This program is free software, you can redistribute it and/or modify it
  * under the terms version 2 of the GNU General Public License as published
  * or (at your option) any later version.
  * by the Free Software Foundation. This program is distributed in the hope
  * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied
  * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
  * See the GNU General Public License for more details.
  * You should have received a copy of the GNU General Public License along
  * with this program, if not, write to the Free Software Foundation, Inc.,
  * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
  * For the text or an alternative of this public license, you may reach us
  * or via info@adempiere.net or http://www.adempiere.net/license.html
  * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
  * Created by victor.perez@e-evolution.com , www.e-evolution.com
  */

package org.eevolution.infrastructure.repository

import org.compiere.model.{I_S_TimeExpenseLine, Query}
import org.compiere.util.Env
import org.eevolution.domain.api.repository.TimeExpenseLineRepositoryComponent
import org.eevolution.domain.ubiquitouslanguage.{ContractLine, InvoiceLine, TimeExpenseLine}

import scala.collection.JavaConverters._
import scala.collection.immutable.List
import scala.util.Try

/**
  * Time Expense Line Repository Implementation the infrastructure for Time Expense Line Entity
  */
trait TimeExpenseLineRepository extends TimeExpenseLineRepositoryComponent {

  final override object timeExpenseLineRepository extends TimeExpenseLineRepositoryTrait {

    override def newInstance(): TimeExpenseLine = {
      val timeExpenseLine = new TimeExpenseLine(Env.getCtx, 0, null)
      timeExpenseLine
    }

    override def createFromContractLine(contractLine: ContractLine): Try[TimeExpenseLine] = {
      Try {
        val timeExpenseLine = newInstance()
        timeExpenseLine.setAD_Org_ID(contractLine.getAD_Org_ID)
        timeExpenseLine.setS_ContractLine_ID(contractLine.get_ID())
        timeExpenseLine.setC_BPartner_ID(contractLine.getC_BPartner_ID)
        timeExpenseLine.setLine(contractLine.getLine)
        timeExpenseLine.setM_Product_ID(contractLine.getM_Product_ID)
        timeExpenseLine.setS_ResourceAssignment_ID(contractLine.getS_ResourceAssignment_ID)
        timeExpenseLine.setDescription(contractLine.getDescription)
        timeExpenseLine.setIsInvoiced(true)
        timeExpenseLine.setC_Currency_ID(contractLine.getC_Campaign_ID)
        timeExpenseLine.setPP_Period_ID(contractLine.getPP_Period_ID)
        timeExpenseLine.setQty(contractLine.getQtyOrdered)
        timeExpenseLine.setQtyInvoiced(contractLine.getQtyOrdered)
        timeExpenseLine.setC_Tax_ID(contractLine.getC_Tax_ID)
        timeExpenseLine.setC_Project_ID(contractLine.getC_Project_ID)
        timeExpenseLine.setC_ProjectPhase_ID(contractLine.getC_ProjectPhase_ID)
        timeExpenseLine.setC_ProjectTask_ID(contractLine.getC_ProjectTask_ID)
        timeExpenseLine.setC_Activity_ID(contractLine.getC_Activity_ID)
        timeExpenseLine.setC_Campaign_ID(contractLine.getC_Campaign_ID)
        timeExpenseLine.setAD_OrgTrx_ID(contractLine.getAD_OrgTrx_ID)
        timeExpenseLine.setAD_Org_ID(contractLine.getAD_Org_ID)
        timeExpenseLine.setUser1_ID(contractLine.getUser1_ID)
        timeExpenseLine.setUser2_ID(contractLine.getUser2_ID)
        timeExpenseLine.setUser3_ID(contractLine.getUser3_ID)
        timeExpenseLine.setUser4_ID(contractLine.getUser4_ID)
        timeExpenseLine
      }
    }

    override def getById(id: Integer): Option[TimeExpenseLine] = {
      val whereClause = s"${I_S_TimeExpenseLine.COLUMNNAME_S_TimeExpenseLine_ID}=?"
      Option(new Query(Env.getCtx, I_S_TimeExpenseLine.Table_Name, whereClause, null)
        .setClient_ID()
        .setParameters(id)
        .first())
    }

    override def getByTimeExpense(timeExpenseId: Integer): List[TimeExpenseLine] = {
      val whereClause = s"${I_S_TimeExpenseLine.COLUMNNAME_S_TimeExpense_ID}=?"
      new Query(Env.getCtx, I_S_TimeExpenseLine.Table_Name, whereClause, null)
        .setClient_ID()
        .setParameters(timeExpenseId)
        .list().asScala.toList
    }

    override def query(whereClause: String, parameters: List[Object]): List[TimeExpenseLine] = {
      new Query(Env.getCtx, I_S_TimeExpenseLine.Table_Name, whereClause, null)
        .setClient_ID()
        .setParameters(parameters.asJava)
        .list().asScala.toList
    }

    override def save(timeExpenseLine: TimeExpenseLine): Try[TimeExpenseLine] = {
      Try {
        timeExpenseLine.save()
        timeExpenseLine
      }
    }

    override def update(timeExpenseLine: TimeExpenseLine, invoiceLine: InvoiceLine): Try[TimeExpenseLine] = {
      timeExpenseLine.setC_InvoiceLine_ID(invoiceLine.get_ID())
      save(timeExpenseLine)
    }

  }

}
