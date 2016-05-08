package com.ubuniworks.webapp.controller;

import com.ubuniworks.model.Idea;
import com.ubuniworks.model.Milestone;
import com.ubuniworks.service.GenericManager;
import com.ubuniworks.service.IdeaManager;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Controller
@RequestMapping("/milestoneform*")
public class MilestoneFormController extends BaseFormController {
    private GenericManager<Milestone, Integer> milestoneManager = null;
    private IdeaManager ideaManager = null;

    public MilestoneFormController() {
        setCancelView("redirect:ideas");
        setSuccessView("redirect:milestones");
    }

    @Autowired
    public void setMilestoneManager(@Qualifier("milestoneManager") GenericManager<Milestone, Integer> milestoneManager) {
        this.milestoneManager = milestoneManager;
    }

    @Autowired
    public void setIdeaManager(IdeaManager ideaManager) {
        this.ideaManager = ideaManager;
    }

    @ModelAttribute("otherMilestones")
    public Set<Milestone> getOtherMilestones(HttpServletRequest request) {
        String idmilestone = request.getParameter("idmilestone");
        String ididea = request.getParameter("ididea");
        Set<Milestone> otherMilestones = new HashSet<>();
        if (ididea != null && !ididea.trim().equals("")) {
            otherMilestones = ideaManager.getMilestones(ideaManager.get(Integer.valueOf(ididea)));
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


    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Idea.class, "idea", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                Idea type = ideaManager.get(Integer.valueOf(text));
                setValue(type);
            }
        });
        binder.registerCustomEditor(Milestone.class, "parentMilestone", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                try {
                    Integer id = Integer.parseInt(text);
                    Milestone milestone = milestoneManager.get(id);
                    setValue(milestone);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            public String getAsText() {
                String date = "";
                try {
                    date = new SimpleDateFormat("MM/dd/yyyy").format((Date) getValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return date;
            }

            public void setAsText(String value) {
                try {
                    setValue(new SimpleDateFormat("MM/dd/yyyy").parse(value));
                } catch (ParseException e) {
                    setValue(null);
                }
            }

        });
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

            success = "redirect:ideadisplay?ididea=" + milestone.getIdea().getIdidea() + "#milestones";
        }

        return success;
    }
}
