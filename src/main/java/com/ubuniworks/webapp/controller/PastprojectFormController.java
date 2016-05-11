package com.ubuniworks.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.ubuniworks.service.GenericManager;
import com.ubuniworks.model.Pastproject;

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
@RequestMapping("/pastprojectform*")
public class PastprojectFormController extends BaseFormController {
    private GenericManager<Pastproject, Integer> pastprojectManager = null;

    @Autowired
    public void setPastprojectManager(@Qualifier("pastprojectManager") GenericManager<Pastproject, Integer> pastprojectManager) {
        this.pastprojectManager = pastprojectManager;
    }

    public PastprojectFormController() {
        setCancelView("redirect:pastprojects");
        setSuccessView("redirect:pastprojects");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Pastproject showForm(HttpServletRequest request)
    throws Exception {
        String idpastproject = request.getParameter("idpastproject");

        if (!StringUtils.isBlank(idpastproject)) {
            return pastprojectManager.get(new Integer(idpastproject));
        }

        return new Pastproject();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Pastproject pastproject, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(pastproject, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "pastprojectform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (pastproject.getIdpastproject() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            pastprojectManager.remove(pastproject.getIdpastproject());
            saveMessage(request, getText("pastproject.deleted", locale));
        } else {
            pastprojectManager.save(pastproject);
            String key = (isNew) ? "pastproject.added" : "pastproject.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:pastprojectform?idpastproject=" + pastproject.getIdpastproject();
            }
        }

        return success;
    }
}
