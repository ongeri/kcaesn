package com.ubuniworks.webapp.controller;

import com.ubuniworks.model.Comment;
import com.ubuniworks.model.Idea;
import com.ubuniworks.service.GenericManager;
import com.ubuniworks.service.IdeaManager;
import com.ubuniworks.service.UserManager;
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
import java.util.Locale;

@Controller
@RequestMapping("/commentform*")
public class CommentFormController extends BaseFormController {
    private GenericManager<Comment, Integer> commentManager = null;
    private IdeaManager ideaManager;
    private UserManager userManager;

    public CommentFormController() {
        setCancelView("redirect:ideas");
        setSuccessView("redirect:comments");
    }

    @Autowired
    public void setCommentManager(@Qualifier("commentManager") GenericManager<Comment, Integer> commentManager) {
        this.commentManager = commentManager;
    }

    @Autowired
    public void setIdeaManager(IdeaManager ideaManager) {
        this.ideaManager = ideaManager;
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Comment showForm(HttpServletRequest request)
            throws Exception {
        String idcomment = request.getParameter("idcomment");
        String ididea = request.getParameter("ididea");
        Comment comment = new Comment();

        if (!StringUtils.isBlank(idcomment)) {
            comment = commentManager.get(new Integer(idcomment));
        } else if (!StringUtils.isBlank(ididea)) {
            comment = new Comment();
            comment.setIdea(ideaManager.get(Integer.valueOf(ididea)));
        }

        return comment;
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

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Comment comment, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(comment, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "commentform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (comment.getIdcomment() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            commentManager.remove(comment.getIdcomment());
            saveMessage(request, getText("comment.deleted", locale));
        } else {
            if (isNew) {
                comment.setUser(getUserUtil().getCurrentUser());
                comment.setDatecreated(new Date());
            }
            commentManager.save(comment);
            String key = (isNew) ? "comment.added" : "comment.updated";
            saveMessage(request, getText(key, locale));

            success = "redirect:ideadisplay?ididea=" + comment.getIdea().getIdidea() + "#comments";
        }

        return success;
    }
}
