package com.ubuniworks.webapp.controller;

import com.ubuniworks.model.User;
import com.ubuniworks.service.RoleManager;
import com.ubuniworks.service.UserManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Implementation of <strong>SimpleFormController</strong> that interacts with
 * the {@link UserManager} to retrieve/persist values to the database.
 * <p>
 * <p><a href="UserFormController.java.html"><i>View Source</i></a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Controller
@RequestMapping("/profile*")
public class ProfileController extends BaseFormController {

    private RoleManager roleManager;

    public ProfileController() {
        setCancelView("redirect:/home");
        setSuccessView("redirect:/admin/users");
    }

    @Autowired
    public void setRoleManager(final RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    @Override
    @InitBinder
    protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) {
        super.initBinder(request, binder);
        binder.setDisallowedFields("password", "confirmPassword");
    }

    /**
     * Load user object from db before web data binding in order to keep properties not populated from web post.
     *
     * @param request
     * @return
     */
    @ModelAttribute("user")
    @RequestMapping(method = RequestMethod.GET)
    protected User loadUser(final HttpServletRequest request) {
        final String userId = request.getParameter("id");
        User user = new User();
        if (StringUtils.isNotBlank(userId)) {
            user = getUserManager().getUser(userId);
        } else {
            user = getUserUtil().getCurrentUser();
        }
        user.setPastprojects(getUserManager().getPastprojects(user));
        user.setExperiences(getUserManager().getExperiences(user));
//        user.setSummary(getUserManager().getSummary(user));
        return user;
    }


    private boolean isFormSubmission(final HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase("post");
    }

    protected boolean isAdd(final HttpServletRequest request) {
        final String method = request.getParameter("method");
        return (method != null && method.equalsIgnoreCase("add"));
    }
}
