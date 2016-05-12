package com.ubuniworks.webapp.controller;

import com.ubuniworks.model.Coststructure;
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
@RequestMapping("/coststructureform*")
public class CoststructureFormController extends BaseFormController {
    private GenericManager<Coststructure, Integer> coststructureManager = null;
    private IdeaManager ideaManager = null;

    @Autowired
    public void setCoststructureManager(@Qualifier("coststructureManager") GenericManager<Coststructure, Integer> coststructureManager) {
        this.coststructureManager = coststructureManager;
    }

    public CoststructureFormController() {
        setCancelView("redirect:ideas");
        setSuccessView("redirect:ideas");
    }

    @Autowired
    public void setIdeaManager(IdeaManager ideaManager) {
        this.ideaManager = ideaManager;
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Coststructure showForm(HttpServletRequest request)
            throws Exception {
        String idcoststructure = request.getParameter("idcoststructure");
        String ididea = request.getParameter("ididea");

        Coststructure customerrelationship = new Coststructure();
        if (!StringUtils.isBlank(idcoststructure)) {
            customerrelationship = coststructureManager.get(new Integer(idcoststructure));
        } else if (!StringUtils.isBlank(ididea)) {
            customerrelationship.setIdea(ideaManager.get(Integer.valueOf(ididea)));
        }

        return customerrelationship;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Coststructure coststructure, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(coststructure, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "coststructureform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (coststructure.getIdcoststructure() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            coststructureManager.remove(coststructure.getIdcoststructure());
            saveMessage(request, getText("coststructure.deleted", locale));
        } else {
            coststructure = coststructureManager.save(coststructure);
            String key = (isNew) ? "coststructure.added" : "coststructure.updated";
            saveMessage(request, getText(key, locale));

            success = "redirect:ideadisplay?ididea=" + coststructure.getIdea().getIdidea() + "#businesscanvas";

        }

        return success;
    }
}
