int numberToAdd = 10;

String namePrefix = "Organization ";
String namePostfix = "";

boolean addSite = false;

//
// End Configuration
//

import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

userId = PortalUtil.getUserId(actionRequest);

for (int i = 1; i <= numberToAdd; i++) {
	String name = namePrefix + i + namePostfix;

	OrganizationLocalServiceUtil.addOrganization(userId, 0, name, addSite);
}