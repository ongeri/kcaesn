package com.ubuniworks.webapp.controller;

import com.ubuniworks.model.Idea;
import com.ubuniworks.model.Milestone;
import com.ubuniworks.service.GenericManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/milestoneform*")
public class MilestoneFormController extends BaseFormController {
    private GenericManager<Milestone, Integer> milestoneManager = null;
    private GenericManager<Idea, Integer> ideaManager = null;

    public MilestoneFormController() {
        setCancelView("redirect:milestones");
        setSuccessView("redirect:milestones");
    }

    @Autowired
    public void setMilestoneManager(@Qualifier("milestoneManager") GenericManager<Milestone, Integer> milestoneManager) {
        this.milestoneManager = milestoneManager;
    }

    @Autowired
    public void setIdeaManager(@Qualifier("ideaManager") com.ubuniworks.service.GenericManager<Idea, Integer> ideaManager) {
        this.ideaManager = ideaManager;
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Idea.class, "idea", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                Idea type = ideaManager.get(Integer.valueOf(text));
                setValue(type);
            }
        });
    }

    @ModelAttribute("otherMilestones")
    public List<Milestone> getOtherMilestones(HttpServletRequest request) {
        String idmilestone = request.getParameter("idmilestone");
        String ididea = request.getParameter("ididea");
        List<Milestone> otherMilestones = new ArrayList<>();
        if (ididea != null && !ididea.trim().equals("")) {
            otherMilestones = milestoneManager.search("ideaid = " + ididea, Milestone.class);
        }
        return otherMilestones;
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Milestone showForm(HttpServletRequest request)
            throws Exception {
        String idmilestone = request.getParameter("idmilestone");
        String ididea = request.getParameter("ididea");
        Milestone milestone;

        if (!StringUtils.isBlank(idmilestone)) {
            milestone = milestoneManager.get(new Integer(idmilestone));
        } else if (!StringUtils.isBlank(ididea)) {
            milestone = new Milestone();
            milestone.setIdea(ideaManager.get(Integer.valueOf(ididea)));
        } else {
            milestone = new Milestone();
        }

        return milestone;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Milestone milestone, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(milestone, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "milestoneform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (milestone.getIdmilestone() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            milestoneManager.remove(milestone.getIdmilestone());
            saveMessage(request, getText("milestone.deleted", locale));
        } else {
            if (isNew) {
                milestone.setDatecreated(new Date());
            }
            milestoneManager.save(milestone);
            String key = (isNew) ? "milestone.added" : "milestone.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:milestoneform?idmilestone=" + milestone.getIdmilestone();
            }
        }

        return success;
    }
}
