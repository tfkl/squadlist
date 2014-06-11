package uk.co.squadlist.web.urls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import uk.co.squadlist.web.api.InstanceConfig;
import uk.co.squadlist.web.model.Member;
import uk.co.squadlist.web.model.Outing;
import uk.co.squadlist.web.model.Squad;

@Component("urlBuilder")
public class UrlBuilder {
	
	private final String baseUrl;
	private final InstanceConfig instanceConfig;
	
	@Autowired
	public UrlBuilder(@Value("#{squadlist['baseUrl']}") String baseUrl, InstanceConfig instanceConfig) {
		this.baseUrl = baseUrl;
		this.instanceConfig = instanceConfig;
	}

	public String applicationUrl(String uri) {
		return getBaseUrl() + uri;
	}
	
	public String loginUrl() {
		return applicationUrl("/");
	}
	
	public String staticUrl(String uri) {
		return applicationUrl("/static/") + uri;
	}
	
	public String memberUrl(Member member) {
		return memberUrl(member.getId());
	}
	
	public String memberUrl(String memberId) {
		return applicationUrl("/member/" + memberId);
	}
	
	public String newMemberUrl() {
		return applicationUrl("/member/new");
	}
	
	public String newSquadUrl() {
		return applicationUrl("/squad/new");
	}
	
	public String editSquadUrl(Squad squad) {
		return applicationUrl("/squad/" + squad.getId() + "/edit");
	}
	
	public String adminUrl() {
		return applicationUrl("/admin");
	}
	
	public String outingsUrl() {
		return applicationUrl("/outings");
	}
	
	public String outingUrl(Outing outing) {
		return outingsUrl() + "/" + outing.getId();
	}
	
	public String outingCloseUrl(Outing outing) {
		return outingUrl(outing) + "/close";
	}
	
	public String outingReopenUrl(Outing outing) {
		return outingUrl(outing) + "/reopen";
	}
	
	public String outingEditUrl(Outing outing) {
		return outingUrl(outing) + "/edit";
	}
	
	public String outings(Squad squad) {
		return applicationUrl("/outings?squad=" + squad.getId());
	}
	
	public String outings(Squad squad, String month) {
		return outings(squad) + "&month=" + month;
	}
	
	public String editMemberUrl(Member member) {
		return memberUrl(member) + "/edit";
	}
	
	public String editMemberUrl(String memberId) {
		return memberUrl(memberId) + "/edit";
	}
	
	public String changePassword() {
		return applicationUrl("/change-password");
	}
	
	public String socialMediaAccounts() {
		return applicationUrl("/social");
	}
	
	public String linkFacebookUrl() {
		return applicationUrl("/social/facebook/link");
	}
	
	public String removeFacebookUrl() {
		return applicationUrl("/social/facebook/remove");
	}
	
	public String getLinkFacebookCallbackUrl() {
		return applicationUrl("/social/facebook/link/callback");
	}
	
	public String facebookSigninCallbackUrl() {
		return applicationUrl("/social/facebook/signin/callback");
	}
		
	public String facebookSignin() {
		return applicationUrl("/social/facebook/signin");
	}
	
	public String getBaseUrl() {
		return baseUrl.replace("INSTANCE", instanceConfig.getInstance());		
	}

	

	
}
