package com.seleniumexpress.securityConfig;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/*This class automatically registers the Spring Security filter 
 * (DelegatingFilterProxy) into the web.xml for you — without needing
 *  to manually add filter configurations.
 */
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

}
