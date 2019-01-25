/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.eevolution.domain.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for S_Contract
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_S_Contract 
{

    /** TableName=S_Contract */
    public static final String Table_Name = "S_Contract";

    /** AD_Table_ID=54441 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_OrgTrx_ID */
    public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";

	/** Set Trx Organization.
	  * Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID);

	/** Get Trx Organization.
	  * Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException;

    /** Column name Bill_BPartner_ID */
    public static final String COLUMNNAME_Bill_BPartner_ID = "Bill_BPartner_ID";

	/** Set Invoice Partner.
	  * Business Partner to be invoiced
	  */
	public void setBill_BPartner_ID (int Bill_BPartner_ID);

	/** Get Invoice Partner.
	  * Business Partner to be invoiced
	  */
	public int getBill_BPartner_ID();

	public org.compiere.model.I_C_BPartner getBill_BPartner() throws RuntimeException;

    /** Column name Bill_Location_ID */
    public static final String COLUMNNAME_Bill_Location_ID = "Bill_Location_ID";

	/** Set Invoice Location.
	  * Business Partner Location for invoicing
	  */
	public void setBill_Location_ID (int Bill_Location_ID);

	/** Get Invoice Location.
	  * Business Partner Location for invoicing
	  */
	public int getBill_Location_ID();

	public org.compiere.model.I_C_BPartner_Location getBill_Location() throws RuntimeException;

    /** Column name Bill_User_ID */
    public static final String COLUMNNAME_Bill_User_ID = "Bill_User_ID";

	/** Set Invoice Contact.
	  * Business Partner Contact for invoicing
	  */
	public void setBill_User_ID (int Bill_User_ID);

	/** Get Invoice Contact.
	  * Business Partner Contact for invoicing
	  */
	public int getBill_User_ID();

	public org.compiere.model.I_AD_User getBill_User() throws RuntimeException;

    /** Column name C_Activity_ID */
    public static final String COLUMNNAME_C_Activity_ID = "C_Activity_ID";

	/** Set Activity.
	  * Business Activity
	  */
	public void setC_Activity_ID (int C_Activity_ID);

	/** Get Activity.
	  * Business Activity
	  */
	public int getC_Activity_ID();

	public org.compiere.model.I_C_Activity getC_Activity() throws RuntimeException;

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_BPartner_Location_ID */
    public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";

	/** Set Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID);

	/** Get Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID();

	public org.compiere.model.I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException;

    /** Column name C_Campaign_ID */
    public static final String COLUMNNAME_C_Campaign_ID = "C_Campaign_ID";

	/** Set Campaign.
	  * Marketing Campaign
	  */
	public void setC_Campaign_ID (int C_Campaign_ID);

	/** Get Campaign.
	  * Marketing Campaign
	  */
	public int getC_Campaign_ID();

	public org.compiere.model.I_C_Campaign getC_Campaign() throws RuntimeException;

    /** Column name C_Charge_ID */
    public static final String COLUMNNAME_C_Charge_ID = "C_Charge_ID";

	/** Set Charge.
	  * Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID);

	/** Get Charge.
	  * Additional document charges
	  */
	public int getC_Charge_ID();

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException;

    /** Column name C_ConversionType_ID */
    public static final String COLUMNNAME_C_ConversionType_ID = "C_ConversionType_ID";

	/** Set Currency Type.
	  * Currency Conversion Rate Type
	  */
	public void setC_ConversionType_ID (int C_ConversionType_ID);

	/** Get Currency Type.
	  * Currency Conversion Rate Type
	  */
	public int getC_ConversionType_ID();

	public org.compiere.model.I_C_ConversionType getC_ConversionType() throws RuntimeException;

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException;

    /** Column name C_DocType_ID */
    public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

	/** Set Document Type.
	  * Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID);

	/** Get Document Type.
	  * Document type or rules
	  */
	public int getC_DocType_ID();

	public org.compiere.model.I_C_DocType getC_DocType() throws RuntimeException;

    /** Column name C_Opportunity_ID */
    public static final String COLUMNNAME_C_Opportunity_ID = "C_Opportunity_ID";

	/** Set Sales Opportunity	  */
	public void setC_Opportunity_ID (int C_Opportunity_ID);

	/** Get Sales Opportunity	  */
	public int getC_Opportunity_ID();

	public org.compiere.model.I_C_Opportunity getC_Opportunity() throws RuntimeException;

    /** Column name C_PaymentTerm_ID */
    public static final String COLUMNNAME_C_PaymentTerm_ID = "C_PaymentTerm_ID";

	/** Set Payment Term.
	  * The terms of Payment (timing, discount)
	  */
	public void setC_PaymentTerm_ID (int C_PaymentTerm_ID);

	/** Get Payment Term.
	  * The terms of Payment (timing, discount)
	  */
	public int getC_PaymentTerm_ID();

	public org.compiere.model.I_C_PaymentTerm getC_PaymentTerm() throws RuntimeException;

    /** Column name C_Project_ID */
    public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";

	/** Set Project.
	  * Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID);

	/** Get Project.
	  * Financial Project
	  */
	public int getC_Project_ID();

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException;

    /** Column name ChargeAmt */
    public static final String COLUMNNAME_ChargeAmt = "ChargeAmt";

	/** Set Charge amount.
	  * Charge Amount
	  */
	public void setChargeAmt (BigDecimal ChargeAmt);

	/** Get Charge amount.
	  * Charge Amount
	  */
	public BigDecimal getChargeAmt();

    /** Column name CommittedAmt */
    public static final String COLUMNNAME_CommittedAmt = "CommittedAmt";

	/** Set Committed Amount.
	  * The (legal) commitment amount
	  */
	public void setCommittedAmt (BigDecimal CommittedAmt);

	/** Get Committed Amount.
	  * The (legal) commitment amount
	  */
	public BigDecimal getCommittedAmt();

    /** Column name CommittedQty */
    public static final String COLUMNNAME_CommittedQty = "CommittedQty";

	/** Set Committed Quantity.
	  * The (legal) commitment Quantity
	  */
	public void setCommittedQty (BigDecimal CommittedQty);

	/** Get Committed Quantity.
	  * The (legal) commitment Quantity
	  */
	public BigDecimal getCommittedQty();

    /** Column name CopyFrom */
    public static final String COLUMNNAME_CopyFrom = "CopyFrom";

	/** Set Copy From.
	  * Copy From Record
	  */
	public void setCopyFrom (String CopyFrom);

	/** Get Copy From.
	  * Copy From Record
	  */
	public String getCopyFrom();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name DateAcct */
    public static final String COLUMNNAME_DateAcct = "DateAcct";

	/** Set Account Date.
	  * Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct);

	/** Get Account Date.
	  * Accounting Date
	  */
	public Timestamp getDateAcct();

    /** Column name DateApproved */
    public static final String COLUMNNAME_DateApproved = "DateApproved";

	/** Set Date Approved.
	  * Sets the approval date of a document.
	  */
	public void setDateApproved (Timestamp DateApproved);

	/** Get Date Approved.
	  * Sets the approval date of a document.
	  */
	public Timestamp getDateApproved();

    /** Column name DateContract */
    public static final String COLUMNNAME_DateContract = "DateContract";

	/** Set Contract Date.
	  * The (planned) effective date of this document.
	  */
	public void setDateContract (Timestamp DateContract);

	/** Get Contract Date.
	  * The (planned) effective date of this document.
	  */
	public Timestamp getDateContract();

    /** Column name DateDeadline */
    public static final String COLUMNNAME_DateDeadline = "DateDeadline";

	/** Set Deadline.
	  * Deadline
	  */
	public void setDateDeadline (Timestamp DateDeadline);

	/** Get Deadline.
	  * Deadline
	  */
	public Timestamp getDateDeadline();

    /** Column name DateDoc */
    public static final String COLUMNNAME_DateDoc = "DateDoc";

	/** Set Document Date.
	  * Date of the Document
	  */
	public void setDateDoc (Timestamp DateDoc);

	/** Get Document Date.
	  * Date of the Document
	  */
	public Timestamp getDateDoc();

    /** Column name DateFinish */
    public static final String COLUMNNAME_DateFinish = "DateFinish";

	/** Set Finish Date.
	  * Finish or (planned) completion date
	  */
	public void setDateFinish (Timestamp DateFinish);

	/** Get Finish Date.
	  * Finish or (planned) completion date
	  */
	public Timestamp getDateFinish();

    /** Column name DateFinishSchedule */
    public static final String COLUMNNAME_DateFinishSchedule = "DateFinishSchedule";

	/** Set Finish Schedule.
	  * Scheduled Finish date for this Order
	  */
	public void setDateFinishSchedule (Timestamp DateFinishSchedule);

	/** Get Finish Schedule.
	  * Scheduled Finish date for this Order
	  */
	public Timestamp getDateFinishSchedule();

    /** Column name DateGrace */
    public static final String COLUMNNAME_DateGrace = "DateGrace";

	/** Set Date Grace.
	  * Sets the grace end date of a document.
	  */
	public void setDateGrace (Timestamp DateGrace);

	/** Get Date Grace.
	  * Sets the grace end date of a document.
	  */
	public Timestamp getDateGrace();

    /** Column name DatePrinted */
    public static final String COLUMNNAME_DatePrinted = "DatePrinted";

	/** Set Date printed.
	  * Date the document was printed.
	  */
	public void setDatePrinted (Timestamp DatePrinted);

	/** Get Date printed.
	  * Date the document was printed.
	  */
	public Timestamp getDatePrinted();

    /** Column name DateSigned */
    public static final String COLUMNNAME_DateSigned = "DateSigned";

	/** Set Date Signed.
	  * Sets the signed date of a document.
	  */
	public void setDateSigned (Timestamp DateSigned);

	/** Get Date Signed.
	  * Sets the signed date of a document.
	  */
	public Timestamp getDateSigned();

    /** Column name DateStart */
    public static final String COLUMNNAME_DateStart = "DateStart";

	/** Set Date Start.
	  * Date Start for this Order
	  */
	public void setDateStart (Timestamp DateStart);

	/** Get Date Start.
	  * Date Start for this Order
	  */
	public Timestamp getDateStart();

    /** Column name DateStartSchedule */
    public static final String COLUMNNAME_DateStartSchedule = "DateStartSchedule";

	/** Set Start Schedule.
	  * Scheduled start date for this Order
	  */
	public void setDateStartSchedule (Timestamp DateStartSchedule);

	/** Get Start Schedule.
	  * Scheduled start date for this Order
	  */
	public Timestamp getDateStartSchedule();

    /** Column name DaysToExpiry */
    public static final String COLUMNNAME_DaysToExpiry = "DaysToExpiry";

	/** Set Days To Expiry.
	  * Number of days for a finish date to expire
	  */
	public void setDaysToExpiry (BigDecimal DaysToExpiry);

	/** Get Days To Expiry.
	  * Number of days for a finish date to expire
	  */
	public BigDecimal getDaysToExpiry();

    /** Column name DeliveryRule */
    public static final String COLUMNNAME_DeliveryRule = "DeliveryRule";

	/** Set Delivery Rule.
	  * Defines the timing of Delivery
	  */
	public void setDeliveryRule (String DeliveryRule);

	/** Get Delivery Rule.
	  * Defines the timing of Delivery
	  */
	public String getDeliveryRule();

    /** Column name DeliveryViaRule */
    public static final String COLUMNNAME_DeliveryViaRule = "DeliveryViaRule";

	/** Set Delivery Via.
	  * How the order will be delivered
	  */
	public void setDeliveryViaRule (String DeliveryViaRule);

	/** Get Delivery Via.
	  * How the order will be delivered
	  */
	public String getDeliveryViaRule();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name DocAction */
    public static final String COLUMNNAME_DocAction = "DocAction";

	/** Set Document Action.
	  * The targeted status of the document
	  */
	public void setDocAction (String DocAction);

	/** Get Document Action.
	  * The targeted status of the document
	  */
	public String getDocAction();

    /** Column name DocStatus */
    public static final String COLUMNNAME_DocStatus = "DocStatus";

	/** Set Document Status.
	  * The current status of the document
	  */
	public void setDocStatus (String DocStatus);

	/** Get Document Status.
	  * The current status of the document
	  */
	public String getDocStatus();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name DropShip_BPartner_ID */
    public static final String COLUMNNAME_DropShip_BPartner_ID = "DropShip_BPartner_ID";

	/** Set Drop Shipment Partner.
	  * Business Partner to ship to
	  */
	public void setDropShip_BPartner_ID (int DropShip_BPartner_ID);

	/** Get Drop Shipment Partner.
	  * Business Partner to ship to
	  */
	public int getDropShip_BPartner_ID();

	public org.compiere.model.I_C_BPartner getDropShip_BPartner() throws RuntimeException;

    /** Column name DropShip_Location_ID */
    public static final String COLUMNNAME_DropShip_Location_ID = "DropShip_Location_ID";

	/** Set Drop Shipment Location.
	  * Business Partner Location for shipping to
	  */
	public void setDropShip_Location_ID (int DropShip_Location_ID);

	/** Get Drop Shipment Location.
	  * Business Partner Location for shipping to
	  */
	public int getDropShip_Location_ID();

	public org.compiere.model.I_C_BPartner_Location getDropShip_Location() throws RuntimeException;

    /** Column name DropShip_User_ID */
    public static final String COLUMNNAME_DropShip_User_ID = "DropShip_User_ID";

	/** Set Drop Shipment Contact.
	  * Business Partner Contact for drop shipment
	  */
	public void setDropShip_User_ID (int DropShip_User_ID);

	/** Get Drop Shipment Contact.
	  * Business Partner Contact for drop shipment
	  */
	public int getDropShip_User_ID();

	public org.compiere.model.I_AD_User getDropShip_User() throws RuntimeException;

    /** Column name Duration */
    public static final String COLUMNNAME_Duration = "Duration";

	/** Set Duration.
	  * Normal Duration in Duration Unit
	  */
	public void setDuration (int Duration);

	/** Get Duration.
	  * Normal Duration in Duration Unit
	  */
	public int getDuration();

    /** Column name DurationGrace */
    public static final String COLUMNNAME_DurationGrace = "DurationGrace";

	/** Set Duration Grace	  */
	public void setDurationGrace (BigDecimal DurationGrace);

	/** Get Duration Grace	  */
	public BigDecimal getDurationGrace();

    /** Column name DurationUnit */
    public static final String COLUMNNAME_DurationUnit = "DurationUnit";

	/** Set Duration Unit.
	  * Unit of Duration
	  */
	public void setDurationUnit (String DurationUnit);

	/** Get Duration Unit.
	  * Unit of Duration
	  */
	public String getDurationUnit();

    /** Column name DurationUnitGrace */
    public static final String COLUMNNAME_DurationUnitGrace = "DurationUnitGrace";

	/** Set Duration Unit Grace.
	  * Unit of Duration for Grace
	  */
	public void setDurationUnitGrace (String DurationUnitGrace);

	/** Get Duration Unit Grace.
	  * Unit of Duration for Grace
	  */
	public String getDurationUnitGrace();

    /** Column name FreightAmt */
    public static final String COLUMNNAME_FreightAmt = "FreightAmt";

	/** Set Freight Amount.
	  * Freight Amount 
	  */
	public void setFreightAmt (BigDecimal FreightAmt);

	/** Get Freight Amount.
	  * Freight Amount 
	  */
	public BigDecimal getFreightAmt();

    /** Column name FreightCostRule */
    public static final String COLUMNNAME_FreightCostRule = "FreightCostRule";

	/** Set Freight Cost Rule.
	  * Method for charging Freight
	  */
	public void setFreightCostRule (String FreightCostRule);

	/** Get Freight Cost Rule.
	  * Method for charging Freight
	  */
	public String getFreightCostRule();

    /** Column name GrandTotal */
    public static final String COLUMNNAME_GrandTotal = "GrandTotal";

	/** Set Grand Total.
	  * Total amount of document
	  */
	public void setGrandTotal (BigDecimal GrandTotal);

	/** Get Grand Total.
	  * Total amount of document
	  */
	public BigDecimal getGrandTotal();

    /** Column name InvoiceRule */
    public static final String COLUMNNAME_InvoiceRule = "InvoiceRule";

	/** Set Invoice Rule.
	  * Frequency and method of invoicing 
	  */
	public void setInvoiceRule (String InvoiceRule);

	/** Get Invoice Rule.
	  * Frequency and method of invoicing 
	  */
	public String getInvoiceRule();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsApproved */
    public static final String COLUMNNAME_IsApproved = "IsApproved";

	/** Set Approved.
	  * Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved);

	/** Get Approved.
	  * Indicates if this document requires approval
	  */
	public boolean isApproved();

    /** Column name IsCreditApproved */
    public static final String COLUMNNAME_IsCreditApproved = "IsCreditApproved";

	/** Set Credit Approved.
	  * Credit  has been approved
	  */
	public void setIsCreditApproved (boolean IsCreditApproved);

	/** Get Credit Approved.
	  * Credit  has been approved
	  */
	public boolean isCreditApproved();

    /** Column name IsDelivered */
    public static final String COLUMNNAME_IsDelivered = "IsDelivered";

	/** Set Delivered	  */
	public void setIsDelivered (boolean IsDelivered);

	/** Get Delivered	  */
	public boolean isDelivered();

    /** Column name IsDiscountPrinted */
    public static final String COLUMNNAME_IsDiscountPrinted = "IsDiscountPrinted";

	/** Set Discount Printed.
	  * Print Discount on Invoice and Order
	  */
	public void setIsDiscountPrinted (boolean IsDiscountPrinted);

	/** Get Discount Printed.
	  * Print Discount on Invoice and Order
	  */
	public boolean isDiscountPrinted();

    /** Column name IsDropShip */
    public static final String COLUMNNAME_IsDropShip = "IsDropShip";

	/** Set Drop Shipment.
	  * Drop Shipments are sent from the Vendor directly to the Customer
	  */
	public void setIsDropShip (boolean IsDropShip);

	/** Get Drop Shipment.
	  * Drop Shipments are sent from the Vendor directly to the Customer
	  */
	public boolean isDropShip();

    /** Column name IsIndefinite */
    public static final String COLUMNNAME_IsIndefinite = "IsIndefinite";

	/** Set Indefinite.
	  * Indefinite
	  */
	public void setIsIndefinite (boolean IsIndefinite);

	/** Get Indefinite.
	  * Indefinite
	  */
	public boolean isIndefinite();

    /** Column name IsInvoiced */
    public static final String COLUMNNAME_IsInvoiced = "IsInvoiced";

	/** Set Invoiced.
	  * Is this invoiced?
	  */
	public void setIsInvoiced (boolean IsInvoiced);

	/** Get Invoiced.
	  * Is this invoiced?
	  */
	public boolean isInvoiced();

    /** Column name IsPrinted */
    public static final String COLUMNNAME_IsPrinted = "IsPrinted";

	/** Set Printed.
	  * Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted);

	/** Get Printed.
	  * Indicates if this document / line is printed
	  */
	public boolean isPrinted();

    /** Column name IsSOTrx */
    public static final String COLUMNNAME_IsSOTrx = "IsSOTrx";

	/** Set Sales Transaction.
	  * This is a Sales Transaction
	  */
	public void setIsSOTrx (boolean IsSOTrx);

	/** Get Sales Transaction.
	  * This is a Sales Transaction
	  */
	public boolean isSOTrx();

    /** Column name IsSelected */
    public static final String COLUMNNAME_IsSelected = "IsSelected";

	/** Set Selected	  */
	public void setIsSelected (boolean IsSelected);

	/** Get Selected	  */
	public boolean isSelected();

    /** Column name IsSelfService */
    public static final String COLUMNNAME_IsSelfService = "IsSelfService";

	/** Set Self-Service.
	  * This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public void setIsSelfService (boolean IsSelfService);

	/** Get Self-Service.
	  * This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public boolean isSelfService();

    /** Column name IsSummary */
    public static final String COLUMNNAME_IsSummary = "IsSummary";

	/** Set Summary Level.
	  * This is a summary entity
	  */
	public void setIsSummary (boolean IsSummary);

	/** Get Summary Level.
	  * This is a summary entity
	  */
	public boolean isSummary();

    /** Column name IsTaxIncluded */
    public static final String COLUMNNAME_IsTaxIncluded = "IsTaxIncluded";

	/** Set Price includes Tax.
	  * Tax is included in the price 
	  */
	public void setIsTaxIncluded (boolean IsTaxIncluded);

	/** Get Price includes Tax.
	  * Tax is included in the price 
	  */
	public boolean isTaxIncluded();

    /** Column name IsTransferred */
    public static final String COLUMNNAME_IsTransferred = "IsTransferred";

	/** Set Transferred.
	  * Transferred to General Ledger (i.e. accounted)
	  */
	public void setIsTransferred (boolean IsTransferred);

	/** Get Transferred.
	  * Transferred to General Ledger (i.e. accounted)
	  */
	public boolean isTransferred();

    /** Column name Link_Contract_ID */
    public static final String COLUMNNAME_Link_Contract_ID = "Link_Contract_ID";

	/** Set Linked Contract.
	  * This field links a Customer Contract to the Vendor Contract that is generated from it.
	  */
	public void setLink_Contract_ID (int Link_Contract_ID);

	/** Get Linked Contract.
	  * This field links a Customer Contract to the Vendor Contract that is generated from it.
	  */
	public int getLink_Contract_ID();

	public org.eevolution.domain.model.I_S_Contract getLink_Contract() throws RuntimeException;

    /** Column name M_FreightCategory_ID */
    public static final String COLUMNNAME_M_FreightCategory_ID = "M_FreightCategory_ID";

	/** Set Freight Category.
	  * Category of the Freight
	  */
	public void setM_FreightCategory_ID (int M_FreightCategory_ID);

	/** Get Freight Category.
	  * Category of the Freight
	  */
	public int getM_FreightCategory_ID();

	public org.compiere.model.I_M_FreightCategory getM_FreightCategory() throws RuntimeException;

    /** Column name M_PriceList_ID */
    public static final String COLUMNNAME_M_PriceList_ID = "M_PriceList_ID";

	/** Set Price List.
	  * Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID);

	/** Get Price List.
	  * Unique identifier of a Price List
	  */
	public int getM_PriceList_ID();

	public org.compiere.model.I_M_PriceList getM_PriceList() throws RuntimeException;

    /** Column name M_Shipper_ID */
    public static final String COLUMNNAME_M_Shipper_ID = "M_Shipper_ID";

	/** Set Shipper.
	  * Method or manner of product delivery
	  */
	public void setM_Shipper_ID (int M_Shipper_ID);

	/** Get Shipper.
	  * Method or manner of product delivery
	  */
	public int getM_Shipper_ID();

	public org.compiere.model.I_M_Shipper getM_Shipper() throws RuntimeException;

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException;

    /** Column name POReference */
    public static final String COLUMNNAME_POReference = "POReference";

	/** Set Order Reference.
	  * Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	  */
	public void setPOReference (String POReference);

	/** Get Order Reference.
	  * Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	  */
	public String getPOReference();

    /** Column name PP_Calendar_ID */
    public static final String COLUMNNAME_PP_Calendar_ID = "PP_Calendar_ID";

	/** Set Operational Calendar.
	  * Operational Period, allows to define the periods for the Operational Calendar
	  */
	public void setPP_Calendar_ID (int PP_Calendar_ID);

	/** Get Operational Calendar.
	  * Operational Period, allows to define the periods for the Operational Calendar
	  */
	public int getPP_Calendar_ID();

	public org.eevolution.model.I_PP_Calendar getPP_Calendar() throws RuntimeException;

    /** Column name PP_PeriodDefinition_ID */
    public static final String COLUMNNAME_PP_PeriodDefinition_ID = "PP_PeriodDefinition_ID";

	/** Set Current Period.
	  * Period Definition, allows to define time cycles for the Operational Calendar
	  */
	public void setPP_PeriodDefinition_ID (int PP_PeriodDefinition_ID);

	/** Get Current Period.
	  * Period Definition, allows to define time cycles for the Operational Calendar
	  */
	public int getPP_PeriodDefinition_ID();

	public org.eevolution.model.I_PP_PeriodDefinition getPP_PeriodDefinition() throws RuntimeException;

    /** Column name Pay_BPartner_ID */
    public static final String COLUMNNAME_Pay_BPartner_ID = "Pay_BPartner_ID";

	/** Set Payment BPartner.
	  * Business Partner responsible for the payment
	  */
	public void setPay_BPartner_ID (int Pay_BPartner_ID);

	/** Get Payment BPartner.
	  * Business Partner responsible for the payment
	  */
	public int getPay_BPartner_ID();

	public org.compiere.model.I_C_BPartner getPay_BPartner() throws RuntimeException;

    /** Column name Pay_Location_ID */
    public static final String COLUMNNAME_Pay_Location_ID = "Pay_Location_ID";

	/** Set Payment Location.
	  * Location of the Business Partner responsible for the payment
	  */
	public void setPay_Location_ID (int Pay_Location_ID);

	/** Get Payment Location.
	  * Location of the Business Partner responsible for the payment
	  */
	public int getPay_Location_ID();

	public org.compiere.model.I_C_BPartner_Location getPay_Location() throws RuntimeException;

    /** Column name PaymentRule */
    public static final String COLUMNNAME_PaymentRule = "PaymentRule";

	/** Set Payment Rule.
	  * How you pay the invoice
	  */
	public void setPaymentRule (String PaymentRule);

	/** Get Payment Rule.
	  * How you pay the invoice
	  */
	public String getPaymentRule();

    /** Column name PlannedAmt */
    public static final String COLUMNNAME_PlannedAmt = "PlannedAmt";

	/** Set Planned Amount.
	  * Planned amount for this project
	  */
	public void setPlannedAmt (BigDecimal PlannedAmt);

	/** Get Planned Amount.
	  * Planned amount for this project
	  */
	public BigDecimal getPlannedAmt();

    /** Column name PlannedQty */
    public static final String COLUMNNAME_PlannedQty = "PlannedQty";

	/** Set Planned Quantity.
	  * Planned quantity for this project
	  */
	public void setPlannedQty (BigDecimal PlannedQty);

	/** Get Planned Quantity.
	  * Planned quantity for this project
	  */
	public BigDecimal getPlannedQty();

    /** Column name Posted */
    public static final String COLUMNNAME_Posted = "Posted";

	/** Set Posted.
	  * Posting status
	  */
	public void setPosted (boolean Posted);

	/** Get Posted.
	  * Posting status
	  */
	public boolean isPosted();

    /** Column name PriorityRule */
    public static final String COLUMNNAME_PriorityRule = "PriorityRule";

	/** Set Priority.
	  * Priority of a document
	  */
	public void setPriorityRule (String PriorityRule);

	/** Get Priority.
	  * Priority of a document
	  */
	public String getPriorityRule();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name ProcessedOn */
    public static final String COLUMNNAME_ProcessedOn = "ProcessedOn";

	/** Set Processed On.
	  * The date+time (expressed in decimal format) when the document has been processed
	  */
	public void setProcessedOn (BigDecimal ProcessedOn);

	/** Get Processed On.
	  * The date+time (expressed in decimal format) when the document has been processed
	  */
	public BigDecimal getProcessedOn();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name PromotionCode */
    public static final String COLUMNNAME_PromotionCode = "PromotionCode";

	/** Set Promotion Code.
	  * User entered promotion code at sales time
	  */
	public void setPromotionCode (String PromotionCode);

	/** Get Promotion Code.
	  * User entered promotion code at sales time
	  */
	public String getPromotionCode();

    /** Column name Ref_Contract_ID */
    public static final String COLUMNNAME_Ref_Contract_ID = "Ref_Contract_ID";

	/** Set Referenced Contract.
	  * Reference to corresponding Customer/Vendor Contract
	  */
	public void setRef_Contract_ID (int Ref_Contract_ID);

	/** Get Referenced Contract.
	  * Reference to corresponding Customer/Vendor Contract
	  */
	public int getRef_Contract_ID();

	public org.eevolution.domain.model.I_S_Contract getRef_Contract() throws RuntimeException;

    /** Column name S_Contract_ID */
    public static final String COLUMNNAME_S_Contract_ID = "S_Contract_ID";

	/** Set Service Contract.
	  * Service Contract
	  */
	public void setS_Contract_ID (int S_Contract_ID);

	/** Get Service Contract.
	  * Service Contract
	  */
	public int getS_Contract_ID();

    /** Column name SalesRep_ID */
    public static final String COLUMNNAME_SalesRep_ID = "SalesRep_ID";

	/** Set Sales Representative.
	  * Sales Representative or Company Agent
	  */
	public void setSalesRep_ID (int SalesRep_ID);

	/** Get Sales Representative.
	  * Sales Representative or Company Agent
	  */
	public int getSalesRep_ID();

	public org.compiere.model.I_AD_User getSalesRep() throws RuntimeException;

    /** Column name SendEMail */
    public static final String COLUMNNAME_SendEMail = "SendEMail";

	/** Set Send EMail.
	  * Enable sending Document EMail
	  */
	public void setSendEMail (boolean SendEMail);

	/** Get Send EMail.
	  * Enable sending Document EMail
	  */
	public boolean isSendEMail();

    /** Column name TotalLines */
    public static final String COLUMNNAME_TotalLines = "TotalLines";

	/** Set Total Lines.
	  * Total of all document lines
	  */
	public void setTotalLines (BigDecimal TotalLines);

	/** Get Total Lines.
	  * Total of all document lines
	  */
	public BigDecimal getTotalLines();

    /** Column name UUID */
    public static final String COLUMNNAME_UUID = "UUID";

	/** Set Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID);

	/** Get Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public String getUUID();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name User1_ID */
    public static final String COLUMNNAME_User1_ID = "User1_ID";

	/** Set User List 1.
	  * User defined list element #1
	  */
	public void setUser1_ID (int User1_ID);

	/** Get User List 1.
	  * User defined list element #1
	  */
	public int getUser1_ID();

	public org.compiere.model.I_C_ElementValue getUser1() throws RuntimeException;

    /** Column name User2_ID */
    public static final String COLUMNNAME_User2_ID = "User2_ID";

	/** Set User List 2.
	  * User defined list element #2
	  */
	public void setUser2_ID (int User2_ID);

	/** Get User List 2.
	  * User defined list element #2
	  */
	public int getUser2_ID();

	public org.compiere.model.I_C_ElementValue getUser2() throws RuntimeException;

    /** Column name User3_ID */
    public static final String COLUMNNAME_User3_ID = "User3_ID";

	/** Set User List 3.
	  * User defined list element #3
	  */
	public void setUser3_ID (int User3_ID);

	/** Get User List 3.
	  * User defined list element #3
	  */
	public int getUser3_ID();

	public org.compiere.model.I_C_ElementValue getUser3() throws RuntimeException;

    /** Column name User4_ID */
    public static final String COLUMNNAME_User4_ID = "User4_ID";

	/** Set User List 4.
	  * User defined list element #4
	  */
	public void setUser4_ID (int User4_ID);

	/** Get User List 4.
	  * User defined list element #4
	  */
	public int getUser4_ID();

	public org.compiere.model.I_C_ElementValue getUser4() throws RuntimeException;

    /** Column name Volume */
    public static final String COLUMNNAME_Volume = "Volume";

	/** Set Volume.
	  * Volume of a product
	  */
	public void setVolume (BigDecimal Volume);

	/** Get Volume.
	  * Volume of a product
	  */
	public BigDecimal getVolume();

    /** Column name Weight */
    public static final String COLUMNNAME_Weight = "Weight";

	/** Set Weight.
	  * Weight of a product
	  */
	public void setWeight (BigDecimal Weight);

	/** Get Weight.
	  * Weight of a product
	  */
	public BigDecimal getWeight();
}
