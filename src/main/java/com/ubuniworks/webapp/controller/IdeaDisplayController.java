package com.ubuniworks.webapp.controller;

import com.ubuniworks.model.Idea;
import org.apache.commons.lang.StringUtils;
import org.appfuse.service.GenericManager;
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
@RequestMapping("/ideadisplay*")
public class IdeaDisplayController extends BaseFormController {
    private GenericManager<Idea, Integer> ideaManager = null;

    public IdeaDisplayController() {
        setCancelView("redirect:ideas");
        setSuccessView("redirect:ideas");
    }

    @Autowired
    public void setIdeaManager(@Qualifier("ideaManager") GenericManager<Idea, Integer> ideaManager) {
        this.ideaManager = ideaManager;
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Idea showForm(HttpServletRequest request)
            throws Exception {
        String ididea = request.getParameter("ididea");

        if (!StringUtils.isBlank(ididea)) {
            Idea idea = ideaManager.get(new Integer(ididea));
            return idea;
        }

        return new Idea();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Idea idea, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(idea, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "ideaform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (idea.getIdidea() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            ideaManager.remove(idea.getIdidea());
            saveMessage(request, getText("idea.deleted", locale));
        } else {
            ideaManager.save(idea);
            String key = (isNew) ? "idea.added" : "idea.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:ideaform?ididea=" + idea.getIdidea();
            }
        }

        return success;
    }
}
