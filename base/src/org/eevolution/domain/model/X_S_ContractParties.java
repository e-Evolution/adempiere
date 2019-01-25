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
package org.eevolution.domain.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for S_ContractParties
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_S_ContractParties extends PO implements I_S_ContractParties, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190125L;

    /** Standard Constructor */
    public X_S_ContractParties (Properties ctx, int S_ContractParties_ID, String trxName)
    {
      super (ctx, S_ContractParties_ID, trxName);
      /** if (S_ContractParties_ID == 0)
        {
			setS_ContractParties_ID (0);
        } */
    }

    /** Load Constructor */
    public X_S_ContractParties (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_S_ContractParties[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getAD_User_ID(), get_TrxName());	}

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getAD_User_ID()));
    }

	/** Set BP Name.
		@param BPName BP Name	  */
	public void setBPName (String BPName)
	{
		throw new IllegalArgumentException ("BPName is virtual column");	}

	/** Get BP Name.
		@return BP Name	  */
	public String getBPName () 
	{
		return (String)get_Value(COLUMNNAME_BPName);
	}

	/** NotificationType AD_Reference_ID=344 */
	public static final int NOTIFICATIONTYPE_AD_Reference_ID=344;
	/** EMail = E */
	public static final String NOTIFICATIONTYPE_EMail = "E";
	/** Notice = N */
	public static final String NOTIFICATIONTYPE_Notice = "N";
	/** None = X */
	public static final String NOTIFICATIONTYPE_None = "X";
	/** EMail+Notice = B */
	public static final String NOTIFICATIONTYPE_EMailPlusNotice = "B";
	/** Set Notification Type.
		@param NotificationType 
		Type of Notifications
	  */
	public void setNotificationType (String NotificationType)
	{

		set_Value (COLUMNNAME_NotificationType, NotificationType);
	}

	/** Get Notification Type.
		@return Type of Notifications
	  */
	public String getNotificationType () 
	{
		return (String)get_Value(COLUMNNAME_NotificationType);
	}

	public org.eevolution.domain.model.I_S_ContractPartiesType getS_ContractPartiesType() throws RuntimeException
    {
		return (org.eevolution.domain.model.I_S_ContractPartiesType)MTable.get(getCtx(), org.eevolution.domain.model.I_S_ContractPartiesType.Table_Name)
			.getPO(getS_ContractPartiesType_ID(), get_TrxName());	}

	/** Set Contract Parties Type.
		@param S_ContractPartiesType_ID Contract Parties Type	  */
	public void setS_ContractPartiesType_ID (int S_ContractPartiesType_ID)
	{
		if (S_ContractPartiesType_ID < 1) 
			set_Value (COLUMNNAME_S_ContractPartiesType_ID, null);
		else 
			set_Value (COLUMNNAME_S_ContractPartiesType_ID, Integer.valueOf(S_ContractPartiesType_ID));
	}

	/** Get Contract Parties Type.
		@return Contract Parties Type	  */
	public int getS_ContractPartiesType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_S_ContractPartiesType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Contract Parties ID.
		@param S_ContractParties_ID Contract Parties ID	  */
	public void setS_ContractParties_ID (int S_ContractParties_ID)
	{
		if (S_ContractParties_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_S_ContractParties_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_S_ContractParties_ID, Integer.valueOf(S_ContractParties_ID));
	}

	/** Get Contract Parties ID.
		@return Contract Parties ID	  */
	public int getS_ContractParties_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_S_ContractParties_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.domain.model.I_S_Contract getS_Contract() throws RuntimeException
    {
		return (org.eevolution.domain.model.I_S_Contract)MTable.get(getCtx(), org.eevolution.domain.model.I_S_Contract.Table_Name)
			.getPO(getS_Contract_ID(), get_TrxName());	}

	/** Set Service Contract.
		@param S_Contract_ID 
		Service Contract
	  */
	public void setS_Contract_ID (int S_Contract_ID)
	{
		if (S_Contract_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_S_Contract_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_S_Contract_ID, Integer.valueOf(S_Contract_ID));
	}

	/** Get Service Contract.
		@return Service Contract
	  */
	public int getS_Contract_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_S_Contract_ID);
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
}