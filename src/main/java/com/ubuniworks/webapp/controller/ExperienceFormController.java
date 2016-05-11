package com.ubuniworks.webapp.controller;

import org.apache.commons.lang.StringUtils;
import org.appfuse.service.GenericManager;
import com.ubuniworks.model.Experience;

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
@RequestMapping("/experienceform*")
public class ExperienceFormController extends BaseFormController {
    private GenericManager<Experience, Integer> experienceManager = null;

    @Autowired
    public void setExperienceManager(@Qualifier("experienceManager") GenericManager<Experience, Integer> experienceManager) {
        this.experienceManager = experienceManager;
    }

    public ExperienceFormController() {
        setCancelView("redirect:experiences");
        setSuccessView("redirect:experiences");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Experience showForm(HttpServletRequest request)
    throws Exception {
        String idexperience = request.getParameter("idexperience");

        if (!StringUtils.isBlank(idexperience)) {
            return experienceManager.get(new Integer(idexperience));
        }

        return new Experience();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Experience experience, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(experience, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "experienceform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (experience.getIdexperience() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            experienceManager.remove(experience.getIdexperience());
            saveMessage(request, getText("experience.deleted", locale));
        } else {
            experienceManager.save(experience);
            String key = (isNew) ? "experience.added" : "experience.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:experienceform?idexperience=" + experience.getIdexperience();
            }
        }

        return success;
    }
}
