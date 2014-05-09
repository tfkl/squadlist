package uk.co.squadlist.web.auth;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import uk.co.squadlist.web.api.SquadlistApi;
import uk.co.squadlist.web.controllers.InstanceConfig;
import uk.co.squadlist.web.model.Member;

import com.google.common.collect.Lists;

@Component("authenticationProvider")
public class ApiBackedAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	
	private static Logger log = Logger.getLogger(ApiBackedAuthenticationProvider.class);
	
    private static final String INVALID_USERNAME_OR_PASSWORD = "Invalid username or password";
    
    private final SquadlistApi api;
    private final InstanceConfig instanceConfig;    
    
    @Autowired
    public ApiBackedAuthenticationProvider(SquadlistApi api, InstanceConfig instanceConfig) {
		this.api = api;
		this.instanceConfig = instanceConfig;
	}

	@Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authToken) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authToken) throws AuthenticationException { 
    	final String password = authToken.getCredentials().toString();
    	log.info("Attempting to auth user: " + username);
		final Member authenticatedMember = api.auth(instanceConfig.getInstance(), username, password);
		if (authenticatedMember != null) {
			log.info("Auth successful for user: " + username);
			Collection<SimpleGrantedAuthority> authorities = Lists.newArrayList();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			return new org.springframework.security.core.userdetails.User(authenticatedMember.getId(), password, authorities);    		
		}
		
		log.info("Auth attempt unsuccessful for user: " + username);		
    	throw new BadCredentialsException(INVALID_USERNAME_OR_PASSWORD);	 
    }

}
