package com.ubuniworks.webapp.propertyeditor;

import com.ubuniworks.model.Idea;
import com.ubuniworks.service.GenericManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

/**
 * Created by Th31nk4l1m3v4 on 05-May-16.
 */
@Component
public class IdeaPropertyEditor extends PropertyEditorSupport {
    private GenericManager<Idea, Integer> ideaManager;

    @Autowired
    public void setIdeaManager(GenericManager<Idea, Integer> ideaManager) {
        this.ideaManager = ideaManager;
    }

    @Override
    public void setAsText(String text) {
        Idea idea = ideaManager.get(Integer.valueOf(text));
        setValue(idea);
    }
}
