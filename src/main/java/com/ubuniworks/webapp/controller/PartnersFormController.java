package com.ubuniworks.webapp.controller;

import com.ubuniworks.model.Idea;
import com.ubuniworks.model.Partners;
import com.ubuniworks.service.GenericManager;
import com.ubuniworks.service.IdeaManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.util.Locale;

@Controller
@RequestMapping("/partnersform*")
public class PartnersFormController extends BaseFormController {
    private GenericManager<Partners, Integer> partnersManager = null;
    private IdeaManager ideaManager = null;

    public PartnersFormController() {
        setCancelView("redirect:ideas");
        setSuccessView("redirect:ideas");
    }

    @Autowired
    public void setPartnersManager(@Qualifier("partnersManager") GenericManager<Partners, Integer> partnersManager) {
        this.partnersManager = partnersManager;
    }

    @Autowired
    public void setIdeaManager(IdeaManager ideaManager) {
        this.ideaManager = ideaManager;
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Idea.class, "idea.ididea", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                Idea type = ideaManager.get(Integer.valueOf(text));
                setValue(type);
            }
        });
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Partners showForm(HttpServletRequest request)
            throws Exception {
        String idpartners = request.getParameter("idpartners");
        Partners partner = new Partners();
        String ididea = request.getParameter("ididea");

        if (!StringUtils.isBlank(idpartners)) {
            partner = partnersManager.get(new Integer(idpartners));
        } else if (!StringUtils.isBlank(ididea)) {
            partner.setIdea(ideaManager.get(Integer.valueOf(ididea)));
        }

        return partner;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Partners partners, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(partners, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "partnersform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (partners.getIdpartners() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            partnersManager.remove(partners.getIdpartners());
            saveMessage(request, getText("partners.deleted", locale));
        } else {
            partners = partnersManager.save(partners);
            String key = (isNew) ? "partners.added" : "partners.updated";
            saveMessage(request, getText(key, locale));
            success = "redirect:ideadisplay?ididea=" + partners.getIdea().getIdidea() + "#businesscanvas";
        }

        return success;
    }
}
