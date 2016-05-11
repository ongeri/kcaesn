package com.ubuniworks.webapp.controller;

import org.apache.commons.lang.StringUtils;
import org.appfuse.service.GenericManager;
import com.ubuniworks.model.Valueproposition;
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
@RequestMapping("/valuepropositionform*")
public class ValuepropositionFormController extends BaseFormController {
    private GenericManager<Valueproposition, Integer> valuepropositionManager = null;

    @Autowired
    public void setValuepropositionManager(@Qualifier("valuepropositionManager") GenericManager<Valueproposition, Integer> valuepropositionManager) {
        this.valuepropositionManager = valuepropositionManager;
    }

    public ValuepropositionFormController() {
        setCancelView("redirect:valuepropositions");
        setSuccessView("redirect:valuepropositions");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Valueproposition showForm(HttpServletRequest request)
    throws Exception {
        String idvalueproposition = request.getParameter("idvalueproposition");

        if (!StringUtils.isBlank(idvalueproposition)) {
            return valuepropositionManager.get(new Integer(idvalueproposition));
        }

        return new Valueproposition();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Valueproposition valueproposition, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(valueproposition, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "valuepropositionform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (valueproposition.getIdvalueproposition() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            valuepropositionManager.remove(valueproposition.getIdvalueproposition());
            saveMessage(request, getText("valueproposition.deleted", locale));
        } else {
            valuepropositionManager.save(valueproposition);
            String key = (isNew) ? "valueproposition.added" : "valueproposition.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:valuepropositionform?idvalueproposition=" + valueproposition.getIdvalueproposition();
            }
        }

        return success;
    }
}
