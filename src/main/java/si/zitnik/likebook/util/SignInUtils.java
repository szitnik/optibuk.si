package si.zitnik.likebook.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: slavkoz
 * Date: 4/1/13
 * Time: 1:40 AM
 * To change this template use Campaign | Settings | Campaign Templates.
 */
public class SignInUtils {

    /**
     * Programmatically signs in the user with the given the user ID.
     */
    public static void signin(String userId) {
        //slavko, dean, timi
        if ("518219683".equals(userId) || "736213652".equals(userId) || "1450963470".equals(userId)) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userId, null, authorities));
        } else {
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userId, null, null));
        }
    }

    public static String getCurrentUserFbId() {
        return (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static boolean isAuthenticated() {
        String principal = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal != null && principal.toLowerCase().startsWith("anonymous")) {
            return false;
        } else  {
            return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        }
    }

}
