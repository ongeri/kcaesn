package com.ubuniworks.webapp.controller;

import org.apache.commons.lang.StringUtils;
import org.appfuse.service.GenericManager;
import com.ubuniworks.model.Customersegment;
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
@RequestMapping("/customersegmentform*")
public class CustomersegmentFormController extends BaseFormController {
    private GenericManager<Customersegment, Integer> customersegmentManager = null;

    @Autowired
    public void setCustomersegmentManager(@Qualifier("customersegmentManager") GenericManager<Customersegment, Integer> customersegmentManager) {
        this.customersegmentManager = customersegmentManager;
    }

    public CustomersegmentFormController() {
        setCancelView("redirect:customersegments");
        setSuccessView("redirect:customersegments");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Customersegment showForm(HttpServletRequest request)
    throws Exception {
        String idcustomersegment = request.getParameter("idcustomersegment");

        if (!StringUtils.isBlank(idcustomersegment)) {
            return customersegmentManager.get(new Integer(idcustomersegment));
        }

        return new Customersegment();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Customersegment customersegment, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(customersegment, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "customersegmentform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (customersegment.getIdcustomersegment() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            customersegmentManager.remove(customersegment.getIdcustomersegment());
            saveMessage(request, getText("customersegment.deleted", locale));
        } else {
            customersegmentManager.save(customersegment);
            String key = (isNew) ? "customersegment.added" : "customersegment.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:customersegmentform?idcustomersegment=" + customersegment.getIdcustomersegment();
            }
        }

        return success;
    }
}
