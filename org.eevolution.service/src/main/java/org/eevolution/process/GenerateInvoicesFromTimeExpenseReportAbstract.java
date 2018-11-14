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

package org.eevolution.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Generate Invoices from Expense Report)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class GenerateInvoicesFromTimeExpenseReportAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "S_TimeExpense Generate Invoice";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Generate Invoices from Expense Report";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54145;
	/**	Parameter Name for Date Invoiced	*/
	public static final String DATEINVOICED = "DateInvoiced";
	/**	Parameter Name for Expense Date	*/
	public static final String DATEEXPENSE = "DateExpense";
	/**	Parameter Name for Current Period	*/
	public static final String PP_PERIODDEFINITION_ID = "PP_PeriodDefinition_ID";
	/**	Parameter Name for Operational Period	*/
	public static final String PP_PERIOD_ID = "PP_Period_ID";
	/**	Parameter Name for Service Contract	*/
	public static final String S_CONTRACT_ID = "S_Contract_ID";
	/**	Parameter Name for Organization	*/
	public static final String AD_ORG_ID = "AD_Org_ID";
	/**	Parameter Name for Business Partner 	*/
	public static final String C_BPARTNER_ID = "C_BPartner_ID";
	/**	Parameter Name for Invoice Partner	*/
	public static final String BILL_BPARTNER_ID = "Bill_BPartner_ID";
	/**	Parameter Name for Payment BPartner	*/
	public static final String PAY_BPARTNER_ID = "Pay_BPartner_ID";
	/**	Parameter Name for Service Type	*/
	public static final String S_SERVICETYPE_ID = "S_ServiceType_ID";
	/**	Parameter Name for Product	*/
	public static final String M_PRODUCT_ID = "M_Product_ID";
	/**	Parameter Name for Time Type	*/
	public static final String S_TIMETYPE_ID = "S_TimeType_ID";
	/**	Parameter Name for Project	*/
	public static final String C_PROJECT_ID = "C_Project_ID";
	/**	Parameter Name for Project Phase	*/
	public static final String C_PROJECTPHASE_ID = "C_ProjectPhase_ID";
	/**	Parameter Name for Project Task	*/
	public static final String C_PROJECTTASK_ID = "C_ProjectTask_ID";
	/**	Parameter Name for Is Milestone	*/
	public static final String ISMILESTONE = "IsMilestone";
	/**	Parameter Name for Activity	*/
	public static final String C_ACTIVITY_ID = "C_Activity_ID";
	/**	Parameter Name for Campaign	*/
	public static final String C_CAMPAIGN_ID = "C_Campaign_ID";
	/**	Parameter Name for Trx Organization	*/
	public static final String AD_ORGTRX_ID = "AD_OrgTrx_ID";
	/**	Parameter Name for User List 1	*/
	public static final String USER1_ID = "User1_ID";
	/**	Parameter Name for User List 2	*/
	public static final String USER2_ID = "User2_ID";
	/**	Parameter Name for User List 3	*/
	public static final String USER3_ID = "User3_ID";
	/**	Parameter Name for User List 4	*/
	public static final String USER4_ID = "User4_ID";
	/**	Parameter Value for Date Invoiced	*/
	private Timestamp dateInvoiced;
	/**	Parameter Value for Expense Date	*/
	private Timestamp dateExpense;
	/**	Parameter Value for Expense Date(To)	*/
	private Timestamp dateExpenseTo;
	/**	Parameter Value for Current Period	*/
	private int periodDefinitionId;
	/**	Parameter Value for Operational Period	*/
	private int periodId;
	/**	Parameter Value for Service Contract	*/
	private int contractId;
	/**	Parameter Value for Organization	*/
	private int orgId;
	/**	Parameter Value for Business Partner 	*/
	private int bPartnerId;
	/**	Parameter Value for Invoice Partner	*/
	private int billBPartnerId;
	/**	Parameter Value for Payment BPartner	*/
	private int payBPartnerId;
	/**	Parameter Value for Service Type	*/
	private int serviceTypeId;
	/**	Parameter Value for Product	*/
	private int productId;
	/**	Parameter Value for Time Type	*/
	private int timeTypeId;
	/**	Parameter Value for Project	*/
	private int projectId;
	/**	Parameter Value for Project Phase	*/
	private int projectPhaseId;
	/**	Parameter Value for Project Task	*/
	private int projectTaskId;
	/**	Parameter Value for Is Milestone	*/
	private String isMilestone;
	/**	Parameter Value for Activity	*/
	private int activityId;
	/**	Parameter Value for Campaign	*/
	private int campaignId;
	/**	Parameter Value for Trx Organization	*/
	private int orgTrxId;
	/**	Parameter Value for User List 1	*/
	private int user1Id;
	/**	Parameter Value for User List 2	*/
	private int user2Id;
	/**	Parameter Value for User List 3	*/
	private int user3Id;
	/**	Parameter Value for User List 4	*/
	private int user4Id;

	@Override
	protected void prepare() {
		dateInvoiced = getParameterAsTimestamp(DATEINVOICED);
		dateExpense = getParameterAsTimestamp(DATEEXPENSE);
		dateExpenseTo = getParameterToAsTimestamp(DATEEXPENSE);
		periodDefinitionId = getParameterAsInt(PP_PERIODDEFINITION_ID);
		periodId = getParameterAsInt(PP_PERIOD_ID);
		contractId = getParameterAsInt(S_CONTRACT_ID);
		orgId = getParameterAsInt(AD_ORG_ID);
		bPartnerId = getParameterAsInt(C_BPARTNER_ID);
		billBPartnerId = getParameterAsInt(BILL_BPARTNER_ID);
		payBPartnerId = getParameterAsInt(PAY_BPARTNER_ID);
		serviceTypeId = getParameterAsInt(S_SERVICETYPE_ID);
		productId = getParameterAsInt(M_PRODUCT_ID);
		timeTypeId = getParameterAsInt(S_TIMETYPE_ID);
		projectId = getParameterAsInt(C_PROJECT_ID);
		projectPhaseId = getParameterAsInt(C_PROJECTPHASE_ID);
		projectTaskId = getParameterAsInt(C_PROJECTTASK_ID);
		isMilestone = getParameterAsString(ISMILESTONE);
		activityId = getParameterAsInt(C_ACTIVITY_ID);
		campaignId = getParameterAsInt(C_CAMPAIGN_ID);
		orgTrxId = getParameterAsInt(AD_ORGTRX_ID);
		user1Id = getParameterAsInt(USER1_ID);
		user2Id = getParameterAsInt(USER2_ID);
		user3Id = getParameterAsInt(USER3_ID);
		user4Id = getParameterAsInt(USER4_ID);
	}

	/**	 Getter Parameter Value for Date Invoiced	*/
	protected Timestamp getDateInvoiced() {
		return dateInvoiced;
	}

	/**	 Setter Parameter Value for Date Invoiced	*/
	protected void setDateInvoiced(Timestamp dateInvoiced) {
		this.dateInvoiced = dateInvoiced;
	}

	/**	 Getter Parameter Value for Expense Date	*/
	protected Timestamp getDateExpense() {
		return dateExpense;
	}

	/**	 Setter Parameter Value for Expense Date	*/
	protected void setDateExpense(Timestamp dateExpense) {
		this.dateExpense = dateExpense;
	}

	/**	 Getter Parameter Value for Expense Date(To)	*/
	protected Timestamp getDateExpenseTo() {
		return dateExpenseTo;
	}

	/**	 Setter Parameter Value for Expense Date(To)	*/
	protected void setDateExpenseTo(Timestamp dateExpenseTo) {
		this.dateExpenseTo = dateExpenseTo;
	}

	/**	 Getter Parameter Value for Current Period	*/
	protected int getPeriodDefinitionId() {
		return periodDefinitionId;
	}

	/**	 Setter Parameter Value for Current Period	*/
	protected void setPeriodDefinitionId(int periodDefinitionId) {
		this.periodDefinitionId = periodDefinitionId;
	}

	/**	 Getter Parameter Value for Operational Period	*/
	protected int getPeriodId() {
		return periodId;
	}

	/**	 Setter Parameter Value for Operational Period	*/
	protected void setPeriodId(int periodId) {
		this.periodId = periodId;
	}

	/**	 Getter Parameter Value for Service Contract	*/
	protected int getContractId() {
		return contractId;
	}

	/**	 Setter Parameter Value for Service Contract	*/
	protected void setContractId(int contractId) {
		this.contractId = contractId;
	}

	/**	 Getter Parameter Value for Organization	*/
	protected int getOrgId() {
		return orgId;
	}

	/**	 Setter Parameter Value for Organization	*/
	protected void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	/**	 Getter Parameter Value for Business Partner 	*/
	protected int getBPartnerId() {
		return bPartnerId;
	}

	/**	 Setter Parameter Value for Business Partner 	*/
	protected void setBPartnerId(int bPartnerId) {
		this.bPartnerId = bPartnerId;
	}

	/**	 Getter Parameter Value for Invoice Partner	*/
	protected int getBillBPartnerId() {
		return billBPartnerId;
	}

	/**	 Setter Parameter Value for Invoice Partner	*/
	protected void setBillBPartnerId(int billBPartnerId) {
		this.billBPartnerId = billBPartnerId;
	}

	/**	 Getter Parameter Value for Payment BPartner	*/
	protected int getPayBPartnerId() {
		return payBPartnerId;
	}

	/**	 Setter Parameter Value for Payment BPartner	*/
	protected void setPayBPartnerId(int payBPartnerId) {
		this.payBPartnerId = payBPartnerId;
	}

	/**	 Getter Parameter Value for Service Type	*/
	protected int getServiceTypeId() {
		return serviceTypeId;
	}

	/**	 Setter Parameter Value for Service Type	*/
	protected void setServiceTypeId(int serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	/**	 Getter Parameter Value for Product	*/
	protected int getProductId() {
		return productId;
	}

	/**	 Setter Parameter Value for Product	*/
	protected void setProductId(int productId) {
		this.productId = productId;
	}

	/**	 Getter Parameter Value for Time Type	*/
	protected int getTimeTypeId() {
		return timeTypeId;
	}

	/**	 Setter Parameter Value for Time Type	*/
	protected void setTimeTypeId(int timeTypeId) {
		this.timeTypeId = timeTypeId;
	}

	/**	 Getter Parameter Value for Project	*/
	protected int getProjectId() {
		return projectId;
	}

	/**	 Setter Parameter Value for Project	*/
	protected void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/**	 Getter Parameter Value for Project Phase	*/
	protected int getProjectPhaseId() {
		return projectPhaseId;
	}

	/**	 Setter Parameter Value for Project Phase	*/
	protected void setProjectPhaseId(int projectPhaseId) {
		this.projectPhaseId = projectPhaseId;
	}

	/**	 Getter Parameter Value for Project Task	*/
	protected int getProjectTaskId() {
		return projectTaskId;
	}

	/**	 Setter Parameter Value for Project Task	*/
	protected void setProjectTaskId(int projectTaskId) {
		this.projectTaskId = projectTaskId;
	}

	/**	 Getter Parameter Value for Is Milestone	*/
	protected String getIsMilestone() {
		return isMilestone;
	}

	/**	 Setter Parameter Value for Is Milestone	*/
	protected void setIsMilestone(String isMilestone) {
		this.isMilestone = isMilestone;
	}

	/**	 Getter Parameter Value for Activity	*/
	protected int getActivityId() {
		return activityId;
	}

	/**	 Setter Parameter Value for Activity	*/
	protected void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	/**	 Getter Parameter Value for Campaign	*/
	protected int getCampaignId() {
		return campaignId;
	}

	/**	 Setter Parameter Value for Campaign	*/
	protected void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

	/**	 Getter Parameter Value for Trx Organization	*/
	protected int getOrgTrxId() {
		return orgTrxId;
	}

	/**	 Setter Parameter Value for Trx Organization	*/
	protected void setOrgTrxId(int orgTrxId) {
		this.orgTrxId = orgTrxId;
	}

	/**	 Getter Parameter Value for User List 1	*/
	protected int getUser1Id() {
		return user1Id;
	}

	/**	 Setter Parameter Value for User List 1	*/
	protected void setUser1Id(int user1Id) {
		this.user1Id = user1Id;
	}

	/**	 Getter Parameter Value for User List 2	*/
	protected int getUser2Id() {
		return user2Id;
	}

	/**	 Setter Parameter Value for User List 2	*/
	protected void setUser2Id(int user2Id) {
		this.user2Id = user2Id;
	}

	/**	 Getter Parameter Value for User List 3	*/
	protected int getUser3Id() {
		return user3Id;
	}

	/**	 Setter Parameter Value for User List 3	*/
	protected void setUser3Id(int user3Id) {
		this.user3Id = user3Id;
	}

	/**	 Getter Parameter Value for User List 4	*/
	protected int getUser4Id() {
		return user4Id;
	}

	/**	 Setter Parameter Value for User List 4	*/
	protected void setUser4Id(int user4Id) {
		this.user4Id = user4Id;
	}

	/**	 Getter Parameter Value for Process ID	*/
	public static final int getProcessId() {
		return ID_FOR_PROCESS;
	}

	/**	 Getter Parameter Value for Process Value	*/
	public static final String getProcessValue() {
		return VALUE_FOR_PROCESS;
	}

	/**	 Getter Parameter Value for Process Name	*/
	public static final String getProcessName() {
		return NAME_FOR_PROCESS;
	}
}