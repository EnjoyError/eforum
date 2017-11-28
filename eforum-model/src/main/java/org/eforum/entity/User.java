package org.eforum.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户
 */
@Entity(name = "User")
@Table(name = "USER")
public class User extends BaseEntity {
    /**
     * 登录账号
     */
    @Column
    private String name;
    /**
     * 电子邮箱
     */
    @Column
    private String email;
    /**
     * 登录密码
     */
    @Column
    private String password;

    /**
     * 头像文件名。例如 1000.jpg
     */
    @Column(name = "HEAD_PORTRAIT_FILE_NAME")
    private String headPortraitFileName;

    /**
     * 真实姓名
     */
    @Column(name = "REAL_NAME")
    private String realName;

    /**
     * 手机号码
     */
    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    /**
     * 地址
     */
    @Column(name = "ADDRESS")
    private String address;

    /**
     * 个性签名
     */
    @Column(name = "PERSONALIZED_SIGNATURE")
    private String personalizedSignature;

    /**
     * 性别
     */
    @Column(name = "GENDER")
    private String gender;

    /** 是否禁言*/
    @Column(name = "BE_SHUTUP")
    private Boolean beShutup = Boolean.FALSE;

    public Boolean getBeShutup() {
        return beShutup;
    }

    public void setBeShutup(Boolean beShutup) {
        this.beShutup = beShutup;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPersonalizedSignature() {
        return personalizedSignature;
    }

    public void setPersonalizedSignature(String personalizedSignature) {
        this.personalizedSignature = personalizedSignature;
    }

    public String getHeadPortraitFileName() {
        return headPortraitFileName;
    }

    public void setHeadPortraitFileName(String headPortraitFileName) {
        this.headPortraitFileName = headPortraitFileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}