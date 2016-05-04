package com.ubuniworks.webapp.controller;

import org.apache.commons.lang.StringUtils;
import org.appfuse.service.GenericManager;
import com.ubuniworks.model.Milestone;
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
@RequestMapping("/milestoneform*")
public class MilestoneFormController extends BaseFormController {
    private GenericManager<Milestone, Integer> milestoneManager = null;

    @Autowired
    public void setMilestoneManager(@Qualifier("milestoneManager") GenericManager<Milestone, Integer> milestoneManager) {
        this.milestoneManager = milestoneManager;
    }

    public MilestoneFormController() {
        setCancelView("redirect:milestones");
        setSuccessView("redirect:milestones");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Milestone showForm(HttpServletRequest request)
    throws Exception {
        String idmilestone = request.getParameter("idmilestone");

        if (!StringUtils.isBlank(idmilestone)) {
            return milestoneManager.get(new Integer(idmilestone));
        }

        return new Milestone();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Milestone milestone, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(milestone, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "milestoneform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (milestone.getIdmilestone() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            milestoneManager.remove(milestone.getIdmilestone());
            saveMessage(request, getText("milestone.deleted", locale));
        } else {
            milestoneManager.save(milestone);
            String key = (isNew) ? "milestone.added" : "milestone.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:milestoneform?idmilestone=" + milestone.getIdmilestone();
            }
        }

        return success;
    }
}
