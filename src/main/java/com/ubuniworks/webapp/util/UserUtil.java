package com.ubuniworks.webapp.util;

import com.ubuniworks.model.User;
import com.ubuniworks.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


/**
 * Created by ramses-iv on 10/21/2015.
 */
@Component
public final class UserUtil {
    private UserManager userManager = null;

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public synchronized User getCurrentUser() {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (obj instanceof UserDetails) {
            username = ((UserDetails) obj).getUsername();
        } else {
            username = obj.toString();
        }

        return username != null ? this.userManager.getUserByUsername(username) : null;
    }


//    public synchronized static User getCurrentUser(UserManager mgr) {
//        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username;
//        if (obj instanceof UserDetails) {
//            username = ((UserDetails) obj).getUsername();
//        } else {
//            username = obj.toString();
//        }
//
//        return username != null ? mgr.getUserByUsername(username) : null;
//    }
}