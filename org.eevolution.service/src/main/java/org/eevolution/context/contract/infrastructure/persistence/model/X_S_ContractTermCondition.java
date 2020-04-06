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
/** Generated Model - DO NOT CHANGE */
package org.eevolution.context.contract.infrastructure.persistence.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for S_ContractTermCondition
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_S_ContractTermCondition extends PO implements I_S_ContractTermCondition, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20200405L;

    /** Standard Constructor */
    public X_S_ContractTermCondition (Properties ctx, int S_ContractTermCondition_ID, String trxName)
    {
      super (ctx, S_ContractTermCondition_ID, trxName);
      /** if (S_ContractTermCondition_ID == 0)
        {
			setS_ContractTermCondition_ID (0);
        } */
    }

    /** Load Constructor */
    public X_S_ContractTermCondition (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_S_ContractTermCondition[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Create lines from.
		@param CreateFrom 
		Process which will generate a new document lines based on an existing document
	  */
	public void setCreateFrom (String CreateFrom)
	{
		set_Value (COLUMNNAME_CreateFrom, CreateFrom);
	}

	/** Get Create lines from.
		@return Process which will generate a new document lines based on an existing document
	  */
	public String getCreateFrom () 
	{
		return (String)get_Value(COLUMNNAME_CreateFrom);
	}

	public org.eevolution.context.contract.infrastructure.persistence.model.I_S_AgreementType getS_AgreementType() throws RuntimeException
    {
		return (org.eevolution.context.contract.infrastructure.persistence.model.I_S_AgreementType)MTable.get(getCtx(), org.eevolution.context.contract.infrastructure.persistence.model.I_S_AgreementType.Table_Name)
			.getPO(getS_AgreementType_ID(), get_TrxName());	}

	/** Set Agreement Type.
		@param S_AgreementType_ID 
		The Agreement Types are used to define the terms and conditions of a contract
	  */
	public void setS_AgreementType_ID (int S_AgreementType_ID)
	{
		if (S_AgreementType_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_S_AgreementType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_S_AgreementType_ID, Integer.valueOf(S_AgreementType_ID));
	}

	/** Get Agreement Type.
		@return The Agreement Types are used to define the terms and conditions of a contract
	  */
	public int getS_AgreementType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_S_AgreementType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.context.contract.infrastructure.persistence.model.I_S_Contract getS_Contract() throws RuntimeException
    {
		return (org.eevolution.context.contract.infrastructure.persistence.model.I_S_Contract)MTable.get(getCtx(), org.eevolution.context.contract.infrastructure.persistence.model.I_S_Contract.Table_Name)
			.getPO(getS_Contract_ID(), get_TrxName());	}

	/** Set Contract.
		@param S_Contract_ID 
		Contract
	  */
	public void setS_Contract_ID (int S_Contract_ID)
	{
		if (S_Contract_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_S_Contract_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_S_Contract_ID, Integer.valueOf(S_Contract_ID));
	}

	/** Get Contract.
		@return Contract
	  */
	public int getS_Contract_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_S_Contract_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getS_Contract_ID()));
    }

	/** Set Contract Terms And Condition.
		@param S_ContractTermCondition_ID 
		Terms and Conditions of a Contract
	  */
	public void setS_ContractTermCondition_ID (int S_ContractTermCondition_ID)
	{
		if (S_ContractTermCondition_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_S_ContractTermCondition_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_S_ContractTermCondition_ID, Integer.valueOf(S_ContractTermCondition_ID));
	}

	/** Get Contract Terms And Condition.
		@return Terms and Conditions of a Contract
	  */
	public int getS_ContractTermCondition_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_S_ContractTermCondition_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Terms And Conditions.
		@param TermsAndConditions 
		Terms and Conditions for a Contract
	  */
	public void setTermsAndConditions (String TermsAndConditions)
	{
		set_ValueNoCheck (COLUMNNAME_TermsAndConditions, TermsAndConditions);
	}

	/** Get Terms And Conditions.
		@return Terms and Conditions for a Contract
	  */
	public String getTermsAndConditions () 
	{
		return (String)get_Value(COLUMNNAME_TermsAndConditions);
	}

	/** Set Immutable Universally Unique Identifier.
		@param UUID 
		Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID)
	{
		set_Value (COLUMNNAME_UUID, UUID);
	}

	/** Get Immutable Universally Unique Identifier.
		@return Immutable Universally Unique Identifier
	  */
	public String getUUID () 
	{
		return (String)get_Value(COLUMNNAME_UUID);
	}

	/** Set Version No.
		@param VersionNo 
		Version Number
	  */
	public void setVersionNo (String VersionNo)
	{
		set_Value (COLUMNNAME_VersionNo, VersionNo);
	}

	/** Get Version No.
		@return Version Number
	  */
	public String getVersionNo () 
	{
		return (String)get_Value(COLUMNNAME_VersionNo);
	}
}