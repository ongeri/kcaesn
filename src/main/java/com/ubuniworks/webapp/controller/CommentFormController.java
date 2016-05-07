package com.ubuniworks.webapp.controller;

import com.ubuniworks.model.Comment;
import com.ubuniworks.service.GenericManager;
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
@RequestMapping("/commentform*")
public class CommentFormController extends BaseFormController {
    private GenericManager<Comment, Integer> commentManager = null;

    public CommentFormController() {
        setCancelView("redirect:comments");
        setSuccessView("redirect:comments");
    }

    @Autowired
    public void setCommentManager(@Qualifier("commentManager") GenericManager<Comment, Integer> commentManager) {
        this.commentManager = commentManager;
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Comment showForm(HttpServletRequest request)
            throws Exception {
        String idcomment = request.getParameter("idcomment");

        if (!StringUtils.isBlank(idcomment)) {
            return commentManager.get(new Integer(idcomment));
        }

        return new Comment();
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
            commentManager.save(comment);
            String key = (isNew) ? "comment.added" : "comment.updated";
            saveMessage(request, getText(key, locale));

            success = "redirect:ideadisplay?ididea=" + comment.getIdea().getIdidea() + "#comments";
        }

        return success;
    }
}
