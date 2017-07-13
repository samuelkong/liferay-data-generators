int numberToAdd = 10;

String namePrefix = "Site ";
String namePostfix = "";

String friendlyUrlPrefix = "/site-";
String friendlyUrlPostfix = "";

//
// End Configuration
//

import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

userId = PortalUtil.getUserId(actionRequest);

for (int i = 1; i <= numberToAdd; i++) {
	String name = namePrefix + i + namePostfix;
	String friendlyUrl = friendlyUrlPrefix + i + friendlyUrlPostfix;

	GroupLocalServiceUtil.addGroup(
		userId, Group.class.getName(), 0, GroupConstants.DEFAULT_LIVE_GROUP_ID,
		name, "", GroupConstants.TYPE_SITE_OPEN, friendlyUrl, true, true, null);
}