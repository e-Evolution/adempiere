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

package org.eevolution.domain.model

import java.io.File
import java.sql.{ResultSet, Timestamp}
import java.util.Properties

import org.compiere.model.ModelValidator._
import org.compiere.model._
import org.compiere.process.{DocAction, DocumentEngine}
import org.compiere.util.DB
import org.eevolution.model.X_S_Contract

/**
  * Contract Entity
  * @param ctx
  * @param id
  * @param rs
  * @param trxName
  */
class MSContract(ctx: Properties, id: Int, rs: ResultSet, trxName: String)
  extends X_S_Contract(ctx: Properties, id: Int, trxName: String) with DocAction {

  def this(ctx: Properties, id: Int, trxName: String) {
    this(ctx, id, null, trxName)
    load(id, trxName)
  }

  def this(ctx: Properties, rs: ResultSet, trxName: String) {
    this(ctx, 999999999, rs, trxName)
    load(rs)
  }


  override protected def beforeSave(newRecord: Boolean): Boolean = {
    return true
  }

  override protected def afterSave(newRecord: Boolean, success: Boolean): Boolean = {
    return true
  }

  override protected def beforeDelete(): Boolean = {
    return true
  }

  def getDocumentInfo(): String = {
    val documentType = MDocType.get(getCtx, getC_DocType_ID)
    return s"${documentType.getName}   ${getDocumentNo}"
  }

  /** Process Message 			 */
  private var processMessage = ""
  /** Just Prepared Flag			 */
  private var justPrepared = false

  override def processIt(processAction: String): Boolean = {
    val engine = new DocumentEngine(this, getDocStatus)
    engine.processIt(processAction, getDocAction)
  }

  override def unlockIt(): Boolean = {
    log.info(s"unlockIt -  ${toString}")
    return true
  }

  override def invalidateIt(): Boolean = {
    log.info(s"invalidateIt - ${toString}")
    setDocAction(X_S_Contract.DOCACTION_Prepare)
    return true
  }

  override def prepareIt(): String = {
    log.info(toString)
    processMessage = ModelValidationEngine.get().fireDocValidate(this, TIMING_BEFORE_PREPARE)
    if (processMessage != null) return DocAction.STATUS_Invalid

    val dt = MDocType.get(getCtx, getC_DocType_ID)

    //	Std Period open?
    if (!MPeriod.isOpen(getCtx, getDateDoc, dt.getDocBaseType, getAD_Org_ID)) {
      processMessage = "@PeriodClosed@"
      return DocAction.STATUS_Invalid
    }
    //	Add up Amounts
    processMessage = ModelValidationEngine.get.fireDocValidate(this, TIMING_AFTER_PREPARE)
    if (processMessage != null) return DocAction.STATUS_Invalid
    justPrepared = true;
    if (!(X_S_Contract.DOCACTION_Complete == getDocAction)) setDocAction(X_S_Contract.DOCACTION_Complete)
    return DocAction.STATUS_InProgress
  }

  override def approveIt(): Boolean = {
    log.info(s"approveIt - ${toString}")
    setIsApproved(true)
    return true
  }

  override def rejectIt(): Boolean = {
    log.info(s"rejectIt - ${toString}")
    setIsApproved(false)
    return true
  }

  override def completeIt(): String = {
    //	Re-Check
    if (!(justPrepared)) {
      val status: String = prepareIt
      if (!(DocAction.STATUS_InProgress == status))
        return status
    }

    processMessage = ModelValidationEngine.get.fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE)
    if (processMessage != null) {
      return DocAction.STATUS_Invalid
    }

    //	Implicit Approval
    if (!(isApproved)) {
      approveIt
    }
    log.info(toString)
    //

    //	User Validation
    val valid: String = ModelValidationEngine.get.fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE)
    if (valid != null) {
      processMessage = valid
      return DocAction.STATUS_Invalid
    }
    //	Set Definitive Document No
    setDefiniteDocumentNo()
    setProcessed(true)
    setDocAction(X_S_Contract.DOCACTION_Close)
    return DocAction.STATUS_Completed
  }

  private def setDefiniteDocumentNo(): Unit = {
    val documentType = MDocType.get(getCtx, getC_DocType_ID)
    if (documentType.isOverwriteDateOnComplete)
      setDateDoc(new Timestamp(System.currentTimeMillis))
    if (documentType.isOverwriteSeqOnComplete) {
      var value = ""
      var index = p_info.getColumnIndex(I_C_DocType.COLUMNNAME_C_DocType_ID)
      if (index != -1)
        value = DB.getDocumentNo(get_ValueAsInt(index), get_TrxName, true)
      if (value != null) setDocumentNo(value)
    }
  }

  override def voidIt(): Boolean = {
    log.info(s"voidIt - ${toString}")
    return closeIt
  }

  override def closeIt(): Boolean = {
    log.info(s"closeIt -  ${toString}")
    setDocAction(X_S_Contract.DOCACTION_None)
    return true
  }

  override def reverseCorrectIt(): Boolean = {
    log.info(s"reverseCorrectIt - ${toString}")
    return false
  }

  override def reverseAccrualIt(): Boolean = {
    log.info(s"reverseAccrualIt - ${toString}")
    return false
  }

  override def reActivateIt(): Boolean = {
    log.info("reActivateIt - " + toString)
    setProcessed(false)
    if (reverseCorrectIt) return true
    return false
  }

  override def getSummary: String = {
    val sb = new StringBuffer
    sb.append(getDocumentNo)
    if (getDescription != null && getDescription.length > 0) sb.append(" - ").append(getDescription)
    return sb.toString
  }

  def createPDF(file: File): File = {
    //val reportEngine = ReportEngine.get (getCtx(), ReportEngine.INVOICE, getC_Invoice_ID());
    //if (re == null)
    null
    //	createPDF//	return re.getPDF(file);
  }

  override def createPDF(): File = {
    try {
      val temp = File.createTempFile(get_TableName + get_ID + "_", ".pdf")
      return createPDF(temp)
    } catch {
      case e: Exception =>
        log.severe("Could not create PDF - " + e.getMessage)
    }
    return null
  }

  override def getProcessMsg: String = processMessage

  override def getDoc_User_ID: Int = getCreatedBy

  override def getApprovalAmt: java.math.BigDecimal = java.math.BigDecimal.ZERO

}