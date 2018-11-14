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


import org.compiere.model.{I_C_Invoice, Query}
import org.compiere.util.Env
import org.eevolution.domain.api.repository.InvoiceRepositoryComponent
import org.eevolution.domain.ubiquitouslanguage.Invoice

import scala.collection.JavaConverters._
import scala.util.Try

/**
  * Invoice Repository Implementation the infrastructure for Invoice Entity
  */
trait InvoiceRepository extends InvoiceRepositoryComponent {

  final override object invoiceRepository extends InvoiceRepositoryTrait {

    override def newInstance(): Try[Invoice] = {
      Try {
        new Invoice(Env.getCtx, 0, null)
      }
    }

    override def save(invoice: Invoice): Try[Invoice] = {
      Try {
        invoice.saveEx()
        invoice
      }
    }

    override def getById(id: Integer): Option[Invoice] = {
      val parameters = List(id)
      val whereClause = s"${I_C_Invoice.COLUMNNAME_C_Invoice_ID}=?"
      Option(new Query(Env.getCtx, I_C_Invoice.Table_Name, whereClause.toString, null)
        .setClient_ID()
        .setParameters(parameters.asJava)
        .first()
      )
    }
  }

}
