int sitesToAdd = 10;

String siteNamePrefix = "Site ";
String siteNamePostfix = "";

String siteFriendlyUrlPrefix = "/site-";
String siteFriendlyUrlPostfix = "";

int pagesToAdd = 10;

String pageNamePrefix = "Page ";
String pageNamePostfix = "";

String pageFriendlyUrlPrefix = "/page-";
String pageFriendlyUrlPostfix = "";

//
// End Configuration
//

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.PortalUtil;

serviceContext = ServiceContextFactory.getInstance(actionRequest);

userId = PortalUtil.getUserId(actionRequest);

for (int i = 1; i <= sitesToAdd; i++) {
	String siteName = siteNamePrefix + i + siteNamePostfix;
	String siteFriendlyUrl = siteFriendlyUrlPrefix + i +
		siteFriendlyUrlPostfix;

	Group group = GroupLocalServiceUtil.addGroup(
		userId, 0, Group.class.getName(), 0,
		GroupConstants.DEFAULT_LIVE_GROUP_ID, siteName, "",
		GroupConstants.TYPE_SITE_OPEN, true,
		GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION, siteFriendlyUrl, true,
		true, null);

	for (int j = 1; j <= pagesToAdd; j++) {
		String pageName = pageNamePrefix + j + pageNamePostfix;
		String pageFriendlyUrl = pageFriendlyUrlPrefix + j +
			pageFriendlyUrlPostfix;

		LayoutLocalServiceUtil.addLayout(
			userId, group.getGroupId(), false, 0, pageName, "", "",
			LayoutConstants.TYPE_PORTLET, false, pageFriendlyUrl,
			serviceContext);
	}
}