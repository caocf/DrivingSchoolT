package com.zjc.drivingSchoolT.db.models;

import com.zjc.drivingSchoolT.db.model.AppResponse;


public class UserInfo extends AppResponse {


    /**
     * experience : null
     * gender : null
     * identityno :
     * loginname : jiaolian2
     * phone : 13568974896
     * photo : /images/nan.jpg
     * teachername : 舟舟
     * uid : fccffbf2624942ad99ade86daa47f195
     */

    private int experience;
    private boolean gender;
    private String identityno;
    private String loginname;
    private String phone;
    private String photo;
    private String teachername;
    private String uid;

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getIdentityno() {
        return identityno;
    }

    public void setIdentityno(String identityno) {
        this.identityno = identityno;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
