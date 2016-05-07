package com.ubuniworks.webapp.controller;

import com.ubuniworks.model.Idea;
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
@RequestMapping("/ideaform*")
public class IdeaFormController extends BaseFormController {
    private GenericManager<Idea, Integer> ideaManager = null;

    public IdeaFormController() {
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
            return ideaManager.get(new Integer(ididea));
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
            idea = ideaManager.save(idea);
            String key = (isNew) ? "idea.added" : "idea.updated";
            saveMessage(request, getText(key, locale));

            success = "redirect:ideadisplay?ididea=" + idea.getIdidea() + "#details";
        }

        return success;
    }
}
