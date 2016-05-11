package com.ubuniworks.webapp.controller;

import org.apache.commons.lang.StringUtils;
import org.appfuse.service.GenericManager;
import com.ubuniworks.model.Activities;
import com.ubuniworks.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/activitiesform*")
public class ActivitiesFormController extends BaseFormController {
    private GenericManager<Activities, Integer> activitiesManager = null;

    @Autowired
    public void setActivitiesManager(@Qualifier("activitiesManager") GenericManager<Activities, Integer> activitiesManager) {
        this.activitiesManager = activitiesManager;
    }

    public ActivitiesFormController() {
        setCancelView("redirect:activitiess");
        setSuccessView("redirect:activitiess");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Activities showForm(HttpServletRequest request)
    throws Exception {
        String idactivities = request.getParameter("idactivities");

        if (!StringUtils.isBlank(idactivities)) {
            return activitiesManager.get(new Integer(idactivities));
        }

        return new Activities();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Activities activities, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(activities, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "activitiesform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (activities.getIdactivities() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            activitiesManager.remove(activities.getIdactivities());
            saveMessage(request, getText("activities.deleted", locale));
        } else {
            activitiesManager.save(activities);
            String key = (isNew) ? "activities.added" : "activities.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:activitiesform?idactivities=" + activities.getIdactivities();
            }
        }

        return success;
    }
}
