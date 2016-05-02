package com.ubuniworks.webapp.controller;

import com.ubuniworks.model.Ideabody;
import com.ubuniworks.service.GenericManager;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping("/ideabodyform*")
public class IdeabodyFormController extends BaseFormController {
    private GenericManager<Ideabody, Integer> ideabodyManager = null;

    public IdeabodyFormController() {
        setCancelView("redirect:ideabodies");
        setSuccessView("redirect:ideabodies");
    }

    @Autowired
    public void setIdeabodyManager(@Qualifier("ideabodyManager") GenericManager<Ideabody, Integer> ideabodyManager) {
        this.ideabodyManager = ideabodyManager;
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Ideabody showForm(HttpServletRequest request)
            throws Exception {
        String idideabody = request.getParameter("idideabody");

        if (!StringUtils.isBlank(idideabody)) {
            return ideabodyManager.get(new Integer(idideabody));
        }

        return new Ideabody();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Ideabody ideabody, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(ideabody, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "ideabodyform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (ideabody.getIdideabody() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            ideabodyManager.remove(ideabody.getIdideabody());
            saveMessage(request, getText("ideabody.deleted", locale));
        } else {
            ideabodyManager.save(ideabody);
            String key = (isNew) ? "ideabody.added" : "ideabody.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:ideabodyform?idideabody=" + ideabody.getIdideabody();
            }
        }

        return success;
    }
}
