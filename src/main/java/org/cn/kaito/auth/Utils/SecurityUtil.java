package org.cn.kaito.auth.Utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {

    private UserDetails getAuthUserDetail() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        return (UserDetails) auth.getPrincipal();
    }
    public String getUserName(){
        return getAuthUserDetail().getUsername();
    }
}
