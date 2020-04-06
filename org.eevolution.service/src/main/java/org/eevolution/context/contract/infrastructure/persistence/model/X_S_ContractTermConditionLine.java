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

/** Generated Model for S_ContractTermConditionLine
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_S_ContractTermConditionLine extends PO implements I_S_ContractTermConditionLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20200405L;

    /** Standard Constructor */
    public X_S_ContractTermConditionLine (Properties ctx, int S_ContractTermConditionLine_ID, String trxName)
    {
      super (ctx, S_ContractTermConditionLine_ID, trxName);
      /** if (S_ContractTermConditionLine_ID == 0)
        {
			setS_ContractTermConditionLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_S_ContractTermConditionLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_S_ContractTermConditionLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	public org.eevolution.context.contract.infrastructure.persistence.model.I_S_AgreementClause getS_AgreementClause() throws RuntimeException
    {
		return (org.eevolution.context.contract.infrastructure.persistence.model.I_S_AgreementClause)MTable.get(getCtx(), org.eevolution.context.contract.infrastructure.persistence.model.I_S_AgreementClause.Table_Name)
			.getPO(getS_AgreementClause_ID(), get_TrxName());	}

	/** Set Agreement Clause.
		@param S_AgreementClause_ID 
		The Agreement Clause Library allows you to maintain a collection of clauses to define the terms and conditions of a contract.
	  */
	public void setS_AgreementClause_ID (int S_AgreementClause_ID)
	{
		if (S_AgreementClause_ID < 1) 
			set_Value (COLUMNNAME_S_AgreementClause_ID, null);
		else 
			set_Value (COLUMNNAME_S_AgreementClause_ID, Integer.valueOf(S_AgreementClause_ID));
	}

	/** Get Agreement Clause.
		@return The Agreement Clause Library allows you to maintain a collection of clauses to define the terms and conditions of a contract.
	  */
	public int getS_AgreementClause_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_S_AgreementClause_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.context.contract.infrastructure.persistence.model.I_S_ContractTermCondition getS_ContractTermCondition() throws RuntimeException
    {
		return (org.eevolution.context.contract.infrastructure.persistence.model.I_S_ContractTermCondition)MTable.get(getCtx(), org.eevolution.context.contract.infrastructure.persistence.model.I_S_ContractTermCondition.Table_Name)
			.getPO(getS_ContractTermCondition_ID(), get_TrxName());	}

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

	/** Set Contract Terms And Condition Lines.
		@param S_ContractTermConditionLine_ID 
		Allows defining the terms and conditions lines of a Contract.
	  */
	public void setS_ContractTermConditionLine_ID (int S_ContractTermConditionLine_ID)
	{
		if (S_ContractTermConditionLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_S_ContractTermConditionLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_S_ContractTermConditionLine_ID, Integer.valueOf(S_ContractTermConditionLine_ID));
	}

	/** Get Contract Terms And Condition Lines.
		@return Allows defining the terms and conditions lines of a Contract.
	  */
	public int getS_ContractTermConditionLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_S_ContractTermConditionLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getSeqNo()));
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
}