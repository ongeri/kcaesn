package com.ubuniworks.service;

import com.ubuniworks.model.Idea;

/**
 * Created by Th31nk4l1m3v4 on 06-May-16.
 */
public interface milestoneManager extends GenericManager<Idea, Integer> {
    Idea getWithCommentsAndMilestones(Integer id);
}
