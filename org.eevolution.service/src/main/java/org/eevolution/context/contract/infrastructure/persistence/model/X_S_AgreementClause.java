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

/** Generated Model for S_AgreementClause
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_S_AgreementClause extends PO implements I_S_AgreementClause, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20200405L;

    /** Standard Constructor */
    public X_S_AgreementClause (Properties ctx, int S_AgreementClause_ID, String trxName)
    {
      super (ctx, S_AgreementClause_ID, trxName);
      /** if (S_AgreementClause_ID == 0)
        {
			setName (null);
			setS_AgreementClauseCategory_ID (0);
			setS_AgreementClause_ID (0);
			setS_AgreementType_ID (0);
        } */
    }

    /** Load Constructor */
    public X_S_AgreementClause (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_S_AgreementClause[")
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

	/** Set Mandatory.
		@param IsMandatory 
		Data entry is required in this column
	  */
	public void setIsMandatory (boolean IsMandatory)
	{
		set_Value (COLUMNNAME_IsMandatory, Boolean.valueOf(IsMandatory));
	}

	/** Get Mandatory.
		@return Data entry is required in this column
	  */
	public boolean isMandatory () 
	{
		Object oo = get_Value(COLUMNNAME_IsMandatory);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	public org.eevolution.context.contract.infrastructure.persistence.model.I_S_AgreementClauseCategory getS_AgreementClauseCategory() throws RuntimeException
    {
		return (org.eevolution.context.contract.infrastructure.persistence.model.I_S_AgreementClauseCategory)MTable.get(getCtx(), org.eevolution.context.contract.infrastructure.persistence.model.I_S_AgreementClauseCategory.Table_Name)
			.getPO(getS_AgreementClauseCategory_ID(), get_TrxName());	}

	/** Set Agreement Clause Category.
		@param S_AgreementClauseCategory_ID 
		Agreement Clause Category allows grouping clauses of an Agreement by category
	  */
	public void setS_AgreementClauseCategory_ID (int S_AgreementClauseCategory_ID)
	{
		if (S_AgreementClauseCategory_ID < 1) 
			set_Value (COLUMNNAME_S_AgreementClauseCategory_ID, null);
		else 
			set_Value (COLUMNNAME_S_AgreementClauseCategory_ID, Integer.valueOf(S_AgreementClauseCategory_ID));
	}

	/** Get Agreement Clause Category.
		@return Agreement Clause Category allows grouping clauses of an Agreement by category
	  */
	public int getS_AgreementClauseCategory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_S_AgreementClauseCategory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Agreement Clause.
		@param S_AgreementClause_ID 
		The Agreement Clause Library allows you to maintain a collection of clauses to define the terms and conditions of a contract.
	  */
	public void setS_AgreementClause_ID (int S_AgreementClause_ID)
	{
		if (S_AgreementClause_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_S_AgreementClause_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_S_AgreementClause_ID, Integer.valueOf(S_AgreementClause_ID));
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
			set_Value (COLUMNNAME_S_AgreementType_ID, null);
		else 
			set_Value (COLUMNNAME_S_AgreementType_ID, Integer.valueOf(S_AgreementType_ID));
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

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getValue());
    }
}