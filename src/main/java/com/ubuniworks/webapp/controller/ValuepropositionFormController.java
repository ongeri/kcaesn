package com.ubuniworks.webapp.controller;

import com.ubuniworks.model.Valueproposition;
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
@RequestMapping("/valuepropositionform*")
public class ValuepropositionFormController extends BaseFormController {
    private GenericManager<Valueproposition, Integer> valuepropositionManager = null;
    private IdeaManager ideaManager = null;

    @Autowired
    public void setValuepropositionManager(@Qualifier("valuepropositionManager") GenericManager<Valueproposition, Integer> valuepropositionManager) {
        this.valuepropositionManager = valuepropositionManager;
    }

    @Autowired
    public void setIdeaManager(IdeaManager ideaManager) {
        this.ideaManager = ideaManager;
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
        String ididea = request.getParameter("ididea");

        Valueproposition valueproposition = new Valueproposition();
        if (!StringUtils.isBlank(idvalueproposition)) {
            valueproposition = valuepropositionManager.get(new Integer(idvalueproposition));
        } else if (!StringUtils.isBlank(ididea)) {
            valueproposition.setIdea(ideaManager.get(Integer.valueOf(ididea)));
        }

        return valueproposition;
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
            valueproposition = valuepropositionManager.save(valueproposition);
            String key = (isNew) ? "valueproposition.added" : "valueproposition.updated";
            saveMessage(request, getText(key, locale));

            success = "redirect:ideadisplay?ididea=" + valueproposition.getIdea().getIdidea() + "#businesscanvas";
        }

        return success;
    }
}
