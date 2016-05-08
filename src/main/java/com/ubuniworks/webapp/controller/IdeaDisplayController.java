package com.ubuniworks.webapp.controller;

import com.ubuniworks.model.Comment;
import com.ubuniworks.model.Idea;
import com.ubuniworks.service.GenericManager;
import com.ubuniworks.service.IdeaManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/ideadisplay*")
public class IdeaDisplayController extends BaseFormController {
    private IdeaManager ideaManager = null;
    private GenericManager<Comment, Integer> commentManager;

    public IdeaDisplayController() {
        setCancelView("redirect:ideas");
        setSuccessView("redirect:ideas");
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            public String getAsText() {
                String date = "";
                try {
                    date = new SimpleDateFormat("dd/MM/yyyy").format((Date) getValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return date;
            }

            public void setAsText(String value) {
                try {
                    setValue(new SimpleDateFormat("dd/MM/yyyy").parse(value));
                } catch (ParseException e) {
                    setValue(null);
                }
            }

        });
    }
    @Autowired
    public void setIdeaManager(IdeaManager ideaManager) {
        this.ideaManager = ideaManager;
    }

    @Autowired
    public void setCommentManager(GenericManager<Comment, Integer> commentManager) {
        this.commentManager = commentManager;
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Idea showForm(HttpServletRequest request)
            throws Exception {
        String ididea = request.getParameter("ididea");

        if (!StringUtils.isBlank(ididea)) {
            Idea idea = ideaManager.get(new Integer(ididea));
            idea.setComments(ideaManager.getTopLevelComments(idea));
            idea.setMilestones(ideaManager.getMilestones(idea));
            return idea;
        }

        return new Idea();
    }
}
