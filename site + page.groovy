int sitesToAdd = 10;

String siteNamePrefix = "Site ";
String siteNamePostfix = "";

String siteFriendlyUrlPrefix = "/site-";
String siteFriendlyUrlPostfix = "";

//
// End Configuration
//

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;

userId = PortalUtil.getUserId(actionRequest);

for (int i = 1; i <= sitesToAdd; i++) {
	String name = siteNamePrefix + i + siteNamePostfix;
	String friendlyUrl = siteFriendlyUrlPrefix + i + siteFriendlyUrlPostfix;

	GroupLocalServiceUtil.addGroup(
		userId, 0, Group.class.getName(), 0,
		GroupConstants.DEFAULT_LIVE_GROUP_ID, name, "",
		GroupConstants.TYPE_SITE_OPEN, true,
		GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION, friendlyUrl, true, true,
		null);
}