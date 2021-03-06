package com.zjc.drivingSchoolT.db.models;

import com.zjc.drivingSchoolT.db.model.AppResponse;

/**
 * 教练Item
 *
 * @author LJ
 * @date 2016年7月21日
 */
public class TeacherItem extends AppResponse {
    private String tid;// 教练收藏id

    private String teachername;// 教练名称

    private String photo;// 教练头像

    private int stars;// 评级1-5

    private boolean gender;// 性别

    private String phone;// 联系电话

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
