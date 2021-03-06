package com.ubuniworks.webapp.controller;

import com.ubuniworks.model.Revenuestream;
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
@RequestMapping("/revenuestreamform*")
public class RevenuestreamFormController extends BaseFormController {
    private GenericManager<Revenuestream, Integer> revenuestreamManager = null;
    private IdeaManager ideaManager = null;

    @Autowired
    public void setRevenuestreamManager(@Qualifier("revenuestreamManager") GenericManager<Revenuestream, Integer> revenuestreamManager) {
        this.revenuestreamManager = revenuestreamManager;
    }

    public RevenuestreamFormController() {
        setCancelView("redirect:ideas");
        setSuccessView("redirect:ideas");
    }

    @Autowired
    public void setIdeaManager(IdeaManager ideaManager) {
        this.ideaManager = ideaManager;
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Revenuestream showForm(HttpServletRequest request)
            throws Exception {
        String idrevenuestream = request.getParameter("idrevenuestream");
        String ididea = request.getParameter("ididea");

        Revenuestream customerrelationship = new Revenuestream();
        if (!StringUtils.isBlank(idrevenuestream)) {
            customerrelationship = revenuestreamManager.get(new Integer(idrevenuestream));
        } else if (!StringUtils.isBlank(ididea)) {
            customerrelationship.setIdea(ideaManager.get(Integer.valueOf(ididea)));
        }

        return customerrelationship;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Revenuestream revenuestream, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(revenuestream, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "revenuestreamform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (revenuestream.getIdrevenuestream() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            revenuestreamManager.remove(revenuestream.getIdrevenuestream());
            saveMessage(request, getText("revenuestream.deleted", locale));
        } else {
            revenuestream = revenuestreamManager.save(revenuestream);
            String key = (isNew) ? "revenuestream.added" : "revenuestream.updated";
            saveMessage(request, getText(key, locale));

            success = "redirect:ideadisplay?ididea=" + revenuestream.getIdea().getIdidea() + "#businesscanvas";

        }

        return success;
    }
}
