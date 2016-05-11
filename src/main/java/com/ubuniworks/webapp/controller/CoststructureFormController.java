package com.ubuniworks.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.ubuniworks.service.GenericManager;
import com.ubuniworks.model.Coststructure;
import com.ubuniworks.webapp.controller.BaseFormController;

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

    @Autowired
    public void setCoststructureManager(@Qualifier("coststructureManager") GenericManager<Coststructure, Integer> coststructureManager) {
        this.coststructureManager = coststructureManager;
    }

    public CoststructureFormController() {
        setCancelView("redirect:coststructures");
        setSuccessView("redirect:coststructures");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Coststructure showForm(HttpServletRequest request)
    throws Exception {
        String idcoststructure = request.getParameter("idcoststructure");

        if (!StringUtils.isBlank(idcoststructure)) {
            return coststructureManager.get(new Integer(idcoststructure));
        }

        return new Coststructure();
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
            coststructureManager.save(coststructure);
            String key = (isNew) ? "coststructure.added" : "coststructure.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:coststructureform?idcoststructure=" + coststructure.getIdcoststructure();
            }
        }

        return success;
    }
}
