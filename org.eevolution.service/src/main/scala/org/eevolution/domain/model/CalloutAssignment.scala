package org.eevolution.domain.model

import java.util.Properties

import org.adempiere.model.GridTabWrapper
import org.compiere.model.{CalloutEngine, GridField, GridTab, MProduct, MResourceAssignment}

class CalloutAssignment extends CalloutEngine {

  def product(context: Properties, windowNo: Int, gridTab: GridTab, gridField: GridField, value: Object): String = {
    val contractLine = GridTabWrapper.create(gridTab, classOf[I_S_ContractLine])
    if (isCalloutActive || value == null)
      return ""
    val resourceAssignmentId = value.asInstanceOf[Integer]
    if (resourceAssignmentId == 0)
      return ""
    val resourceAssignment = new MResourceAssignment(context, resourceAssignmentId, null)
    val product = MProduct.forS_Resource_ID(context, resourceAssignment.getS_Resource_ID, null)
    log.fine(s"S_ResourceAssignment_ID ${resourceAssignmentId} - M_Product_ID= ${product.getM_Product_ID}");
    if (product.getM_Product_ID > 0) {
      contractLine.setM_Product_ID(product.getM_Product_ID)
      val description = Option(product.getDescription).map(description => product.getName + " (" + description + ")")
      contractLine.setDescription(description.getOrElse(product.getName))
      if (gridTab.getTableName.startsWith("S_ContractLine")) {
        contractLine.setQtyEntered(resourceAssignment.getQty)
      }
    }
    return ""
  }
}
