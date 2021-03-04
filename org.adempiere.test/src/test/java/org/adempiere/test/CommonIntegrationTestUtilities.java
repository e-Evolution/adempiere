/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2020 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
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
package org.adempiere.test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;

public class CommonIntegrationTestUtilities {

    private Timestamp currentLoginDate = TimeUtil.getDay(System.currentTimeMillis());
    private static final String DATE_PROPERTY = "#Date";

    public void resetLoginDate(Properties ctx) {

        ctx.setProperty(DATE_PROPERTY, currentLoginDate.toString());

    }

    public void setLoginDate(Properties ctx, Timestamp newLoginDate) {

        currentLoginDate = Env.getContextAsDate(ctx, DATE_PROPERTY);
        ctx.setProperty(DATE_PROPERTY, newLoginDate.toString());

    }

    public static void assertEnEsMessageTranslationsExist(String msgKey) {

        Language base = Language.getBaseLanguage();
        Language esMx = Language.getLanguage("es_MX");
        String translatedMsgBase = Msg.translate(base, msgKey);
        String translatedMsgEsMx = Msg.translate(esMx, msgKey);

        assertNotEquals(msgKey, translatedMsgBase,
                "Message not translated in " + base.getAD_Language() + ": " + msgKey);
        assertNotEquals(translatedMsgBase, translatedMsgEsMx,
                "Message not translated in " + esMx.getAD_Language() + ": " + msgKey);

    }

}