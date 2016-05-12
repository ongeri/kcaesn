package com.ubuniworks.webapp.controller;

import com.ubuniworks.model.Customersegment;
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
@RequestMapping("/customersegmentform*")
public class CustomersegmentFormController extends BaseFormController {
    private GenericManager<Customersegment, Integer> customersegmentManager = null;
    private IdeaManager ideaManager = null;

    @Autowired
    public void setCustomersegmentManager(@Qualifier("customersegmentManager") GenericManager<Customersegment, Integer> customersegmentManager) {
        this.customersegmentManager = customersegmentManager;
    }

    @Autowired
    public void setIdeaManager(IdeaManager ideaManager) {
        this.ideaManager = ideaManager;
    }


    public CustomersegmentFormController() {
        setCancelView("redirect:ideas");
        setSuccessView("redirect:ideas");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Customersegment showForm(HttpServletRequest request)
            throws Exception {
        String idcustomersegment = request.getParameter("idcustomersegment");
        String ididea = request.getParameter("ididea");

        Customersegment customersegment = new Customersegment();
        if (!StringUtils.isBlank(idcustomersegment)) {
            customersegment = customersegmentManager.get(new Integer(idcustomersegment));
        } else if (!StringUtils.isBlank(ididea)) {
            customersegment.setIdea(ideaManager.get(Integer.valueOf(ididea)));
        }

        return customersegment;
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
            customersegment = customersegmentManager.save(customersegment);
            String key = (isNew) ? "customersegment.added" : "customersegment.updated";
            saveMessage(request, getText(key, locale));

            success = "redirect:ideadisplay?ididea=" + customersegment.getIdea().getIdidea() + "#businesscanvas";

        }

        return success;
    }
}
