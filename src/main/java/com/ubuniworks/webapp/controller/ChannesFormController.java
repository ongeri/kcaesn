package com.ubuniworks.webapp.controller;

import com.ubuniworks.model.Channes;
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
@RequestMapping("/channesform*")
public class ChannesFormController extends BaseFormController {
    private GenericManager<Channes, Integer> channesManager = null;
    private IdeaManager ideaManager = null;

    @Autowired
    public void setChannesManager(@Qualifier("channesManager") GenericManager<Channes, Integer> channesManager) {
        this.channesManager = channesManager;
    }

    public ChannesFormController() {
        setCancelView("redirect:ideas");
        setSuccessView("redirect:ideas");
    }

    @Autowired
    public void setIdeaManager(IdeaManager ideaManager) {
        this.ideaManager = ideaManager;
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Channes showForm(HttpServletRequest request)
            throws Exception {
        String idchannes = request.getParameter("idchannes");
        String ididea = request.getParameter("ididea");

        Channes customerrelationship = new Channes();
        if (!StringUtils.isBlank(idchannes)) {
            customerrelationship = channesManager.get(new Integer(idchannes));
        } else if (!StringUtils.isBlank(ididea)) {
            customerrelationship.setIdea(ideaManager.get(Integer.valueOf(ididea)));
        }

        return customerrelationship;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Channes channes, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(channes, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "channesform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (channes.getIdchannes() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            channesManager.remove(channes.getIdchannes());
            saveMessage(request, getText("channes.deleted", locale));
        } else {
            channes = channesManager.save(channes);
            String key = (isNew) ? "channes.added" : "channes.updated";
            saveMessage(request, getText(key, locale));

            success = "redirect:ideadisplay?ididea=" + channes.getIdea().getIdidea() + "#businesscanvas";

        }

        return success;
    }
}
