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

/** Generated Interface for S_ContractTermConditionLine
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3
 */
public interface I_S_ContractTermConditionLine 
{

    /** TableName=S_ContractTermConditionLine */
    public static final String Table_Name = "S_ContractTermConditionLine";

    /** AD_Table_ID=54712 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

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

    /** Column name S_AgreementClause_ID */
    public static final String COLUMNNAME_S_AgreementClause_ID = "S_AgreementClause_ID";

	/** Set Agreement Clause.
	  * The Agreement Clause Library allows you to maintain a collection of clauses to define the terms and conditions of a contract.
	  */
	public void setS_AgreementClause_ID (int S_AgreementClause_ID);

	/** Get Agreement Clause.
	  * The Agreement Clause Library allows you to maintain a collection of clauses to define the terms and conditions of a contract.
	  */
	public int getS_AgreementClause_ID();

	public org.eevolution.domain.model.I_S_AgreementClause getS_AgreementClause() throws RuntimeException;

    /** Column name S_ContractTermCondition_ID */
    public static final String COLUMNNAME_S_ContractTermCondition_ID = "S_ContractTermCondition_ID";

	/** Set Contract Terms And Condition.
	  * Terms and Conditions of a Contract
	  */
	public void setS_ContractTermCondition_ID (int S_ContractTermCondition_ID);

	/** Get Contract Terms And Condition.
	  * Terms and Conditions of a Contract
	  */
	public int getS_ContractTermCondition_ID();

	public org.eevolution.domain.model.I_S_ContractTermCondition getS_ContractTermCondition() throws RuntimeException;

    /** Column name S_ContractTermConditionLine_ID */
    public static final String COLUMNNAME_S_ContractTermConditionLine_ID = "S_ContractTermConditionLine_ID";

	/** Set Contract Terms And Condition Lines.
	  * Allows defining the terms and conditions lines of a Contract.
	  */
	public void setS_ContractTermConditionLine_ID (int S_ContractTermConditionLine_ID);

	/** Get Contract Terms And Condition Lines.
	  * Allows defining the terms and conditions lines of a Contract.
	  */
	public int getS_ContractTermConditionLine_ID();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

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
}
