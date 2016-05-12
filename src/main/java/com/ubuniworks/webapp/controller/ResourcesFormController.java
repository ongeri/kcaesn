package com.ubuniworks.webapp.controller;

import com.ubuniworks.model.Resources;
import com.ubuniworks.service.GenericManager;
import com.ubuniworks.service.IdeaManager;
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
@RequestMapping("/resourcesform*")
public class ResourcesFormController extends BaseFormController {
    private GenericManager<Resources, Integer> resourcesManager = null;
    private IdeaManager ideaManager = null;

    @Autowired
    public void setResourcesManager(@Qualifier("resourcesManager") GenericManager<Resources, Integer> resourcesManager) {
        this.resourcesManager = resourcesManager;
    }

    public ResourcesFormController() {
        setCancelView("redirect:ideas");
        setSuccessView("redirect:ideas");
    }

    @Autowired
    public void setIdeaManager(IdeaManager ideaManager) {
        this.ideaManager = ideaManager;
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Resources showForm(HttpServletRequest request)
            throws Exception {
        String idresources = request.getParameter("idresources");
        String ididea = request.getParameter("ididea");

        Resources resources = new Resources();
        if (!StringUtils.isBlank(idresources)) {
            resources = resourcesManager.get(new Integer(idresources));
        } else if (!StringUtils.isBlank(ididea)) {
            resources.setIdea(ideaManager.get(Integer.valueOf(ididea)));
        }

        return resources;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Resources resources, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(resources, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "resourcesform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (resources.getIdresources() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            resourcesManager.remove(resources.getIdresources());
            saveMessage(request, getText("resources.deleted", locale));
        } else {
            resources = resourcesManager.save(resources);
            String key = (isNew) ? "resources.added" : "resources.updated";
            saveMessage(request, getText(key, locale));

            success = "redirect:ideadisplay?ididea=" + resources.getIdea().getIdidea() + "#businesscanvas";

        }

        return success;
    }
}
