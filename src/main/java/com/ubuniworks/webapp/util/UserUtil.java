package com.ubuniworks.webapp.util;

import com.ubuniworks.model.User;
import com.ubuniworks.service.UserManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by Th31nk4l1m3v4 on 07-May-16.
 */
public final class UserUtil {

    public synchronized static User getCurrentUser(UserManager mgr) {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (obj instanceof UserDetails) {
            username = ((UserDetails) obj).getUsername();
        } else {
            username = obj.toString();
        }

        return username != null ? mgr.getUserByUsername(username) : null;
    }
}