package com.ubuniworks.webapp.controller;

import com.ubuniworks.model.Customerrelationship;
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
@RequestMapping("/customerrelationshipform*")
public class CustomerrelationshipFormController extends BaseFormController {
    private GenericManager<Customerrelationship, Integer> customerrelationshipManager = null;
    private IdeaManager ideaManager = null;

    @Autowired
    public void setCustomerrelationshipManager(@Qualifier("customerrelationshipManager") GenericManager<Customerrelationship, Integer> customerrelationshipManager) {
        this.customerrelationshipManager = customerrelationshipManager;
    }

    public CustomerrelationshipFormController() {
        setCancelView("redirect:ideas");
        setSuccessView("redirect:ideas");
    }

    @Autowired
    public void setIdeaManager(IdeaManager ideaManager) {
        this.ideaManager = ideaManager;
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Customerrelationship showForm(HttpServletRequest request)
            throws Exception {
        String idcustomerrelationship = request.getParameter("idcustomerrelationship");
        String ididea = request.getParameter("ididea");

        Customerrelationship customerrelationship = new Customerrelationship();
        if (!StringUtils.isBlank(idcustomerrelationship)) {
            customerrelationship = customerrelationshipManager.get(new Integer(idcustomerrelationship));
        } else if (!StringUtils.isBlank(ididea)) {
            customerrelationship.setIdea(ideaManager.get(Integer.valueOf(ididea)));
        }

        return customerrelationship;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Customerrelationship customerrelationship, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(customerrelationship, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "customerrelationshipform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (customerrelationship.getIdcustomerrelationship() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            customerrelationshipManager.remove(customerrelationship.getIdcustomerrelationship());
            saveMessage(request, getText("customerrelationship.deleted", locale));
        } else {
            customerrelationship = customerrelationshipManager.save(customerrelationship);
            String key = (isNew) ? "customerrelationship.added" : "customerrelationship.updated";
            saveMessage(request, getText(key, locale));

            success = "redirect:ideadisplay?ididea=" + customerrelationship.getIdea().getIdidea() + "#businesscanvas";

        }

        return success;
    }
}
