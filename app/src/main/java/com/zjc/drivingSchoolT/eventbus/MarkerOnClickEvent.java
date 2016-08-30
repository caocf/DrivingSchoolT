package com.zjc.drivingSchoolT.eventbus;


import com.zjc.drivingSchoolT.db.model.School;

/**
 * Created by Administrator on 2015/7/14.
 */
public class MarkerOnClickEvent {
    private School school;

    public MarkerOnClickEvent(School school) {
        this.school = school;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
