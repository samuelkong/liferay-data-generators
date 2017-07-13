int numberToAdd = 10;

String screenNamePrefix = "user";
String screenNamePostfix = "";

String emailAddressDomain = "liferay.com";

String lastName = "Test";

String password = "test";

String reminderQueryQuestion = "what-is-your-father's-middle-name";
String reminderQueryAnswer = "vader";

Locale locale = Locale.US;

boolean addUsersToGuestSite = true;

//
// End Configuration
//

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.PasswordPolicy;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.PasswordPolicyLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;

import java.util.Locale;

ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
	WebKeys.THEME_DISPLAY);

companyId = themeDisplay.getCompanyId();

Group group = GroupLocalServiceUtil.getGroup(companyId, "Guest");

groupId = group.getGroupId();

ServiceContext serviceContext = ServiceContextFactory.getInstance(
	actionRequest);

serviceContext.setScopeGroupId(groupId);

// Do not require password change after first login

PasswordPolicy defaultPasswordPolicy =
	PasswordPolicyLocalServiceUtil.getDefaultPasswordPolicy(companyId);

PasswordPolicyLocalServiceUtil.updatePasswordPolicy(
	defaultPasswordPolicy.getPasswordPolicyId(),
	defaultPasswordPolicy.getName(), defaultPasswordPolicy.getDescription(),
	defaultPasswordPolicy.getChangeable(), false,
	defaultPasswordPolicy.getMinAge(), defaultPasswordPolicy.getCheckSyntax(),
	defaultPasswordPolicy.getAllowDictionaryWords(),
	defaultPasswordPolicy.getMinAlphanumeric(),
	defaultPasswordPolicy.getMinLength(),
	defaultPasswordPolicy.getMinLowerCase(),
	defaultPasswordPolicy.getMinNumbers(),
	defaultPasswordPolicy.getMinSymbols(),
	defaultPasswordPolicy.getMinUpperCase(), defaultPasswordPolicy.getHistory(),
	defaultPasswordPolicy.getHistoryCount(),
	defaultPasswordPolicy.getExpireable(), defaultPasswordPolicy.getMaxAge(),
	defaultPasswordPolicy.getWarningTime(),
	defaultPasswordPolicy.getGraceLimit(), defaultPasswordPolicy.getLockout(),
	defaultPasswordPolicy.getMaxFailure(),
	defaultPasswordPolicy.getLockoutDuration(),
	defaultPasswordPolicy.getResetFailureCount(),
	defaultPasswordPolicy.getResetTicketMaxAge());

// Add users

for (int i = 1; i <= numberToAdd; i++) {
	String screenName = screenNamePrefix + i + screenNamePostfix;
	String emailAddress = screenName + "@" + emailAddressDomain;
	String firstName = StringUtil.upperCaseFirstLetter(screenName);

	User user = UserLocalServiceUtil.addUser(
		0, companyId, false, password, password, false, screenName,
		emailAddress, 0, null, locale, firstName, null, lastName, 0, 0, true, 0,
		1, 1970, null, null, null, null, null, true, serviceContext);

	if (addUsersToGuestSite) {
		long[] userIds = [user.getUserId()];

		UserLocalServiceUtil.addGroupUsers(groupId, userIds);
	}

	UserLocalServiceUtil.updateAgreedToTermsOfUse(user.getUserId(), true);

	UserLocalServiceUtil.updateReminderQuery(
		user.getUserId(), reminderQueryQuestion, reminderQueryAnswer);
}