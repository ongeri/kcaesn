package com.ubuniworks.webapp.controller;

import org.apache.commons.lang.StringUtils;
import org.appfuse.service.GenericManager;
import com.ubuniworks.model.Tag;

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
@RequestMapping("/tagform*")
public class TagFormController extends BaseFormController {
    private GenericManager<Tag, Integer> tagManager = null;

    @Autowired
    public void setTagManager(@Qualifier("tagManager") GenericManager<Tag, Integer> tagManager) {
        this.tagManager = tagManager;
    }

    public TagFormController() {
        setCancelView("redirect:tags");
        setSuccessView("redirect:tags");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Tag showForm(HttpServletRequest request)
    throws Exception {
        String idtag = request.getParameter("idtag");

        if (!StringUtils.isBlank(idtag)) {
            return tagManager.get(new Integer(idtag));
        }

        return new Tag();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Tag tag, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(tag, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "tagform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (tag.getIdtag() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            tagManager.remove(tag.getIdtag());
            saveMessage(request, getText("tag.deleted", locale));
        } else {
            tagManager.save(tag);
            String key = (isNew) ? "tag.added" : "tag.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:tagform?idtag=" + tag.getIdtag();
            }
        }

        return success;
    }
}
