package com.quanglv.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Utility class for Spring Security.
 */
public final class SecurityUtils {

    private SecurityUtils() {
    }

    /**
     * Get the login of the current user.
     *
     * @return the login of the current user.
     */
    public static Optional<String> getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(extractPrincipal(securityContext.getAuthentication()));
    }

    private static String extractPrincipal(Authentication authentication) {
        if (authentication == null) {
            return null;
        } else if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            return springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }


    /**
     * Check if a user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise.
     */
    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null &&
            getAuthorities(authentication).noneMatch(AuthoritiesConstants.ANONYMOUS::equals);
    }

    /**
     * If the current user has a specific authority (security role).
     * <p>
     * The name of this method comes from the {@code isUserInRole()} method in the Servlet API.
     *
     * @param authority the authority to check.
     * @return true if the current user has the authority, false otherwise.
     */
    public static boolean isCurrentUserInRole(String authority) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null &&
            getAuthorities(authentication).anyMatch(authority::equals);
    }

    private static Stream<String> getAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority);
    }

    @SuppressWarnings("unchecked")
    public static Long getMerchantIdOfCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var authenticationDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
        LinkedHashMap<String, ?> claims = (LinkedHashMap<String, ?>) authenticationDetails.getDecodedDetails();
        return Long.valueOf(String.valueOf(claims.get("merchant_id")));
    }

    @SuppressWarnings("unchecked")
    public static String getDomainOfCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var authenticationDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
        LinkedHashMap<String, ?> claims = (LinkedHashMap<String, ?>) authenticationDetails.getDecodedDetails();
        return String.valueOf(claims.get("domain"));
    }

    @SuppressWarnings("unchecked")
    public static String getFullnameOfCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var authenticationDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
        LinkedHashMap<String, ?> claims = (LinkedHashMap<String, ?>) authenticationDetails.getDecodedDetails();
        return String.valueOf(claims.get("full_name"));
    }
}
