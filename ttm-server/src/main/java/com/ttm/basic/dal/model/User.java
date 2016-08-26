package com.ttm.basic.dal.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by liguoqing on 2016/4/27.
 */
public class User implements Serializable{
    //主键
    private Long pkId;
    //用户名
    private String userName;
    //昵称
    private String nickName;
    //身份证
    private String identityCardNo;
    //创建时间
    private Date createTime;
    //是否新用户
    private Boolean isNew = Boolean.TRUE;
    //账号是否被锁定
    private Boolean isNonLocked = Boolean.TRUE;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    //更新时间
    private Date updateTime;
    //逻辑删除状态位
    private Boolean isDelete;


    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Boolean getIsNonLocked() {
        return isNonLocked;
    }

    public void setIsNonLocked(Boolean isNonLocked) {
        this.isNonLocked = isNonLocked;
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIdentityCardNo() {
        return identityCardNo;
    }

    public void setIdentityCardNo(String identityCardNo) {
        this.identityCardNo = identityCardNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "User{" +
                "pkId=" + pkId +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", identityCardNo='" + identityCardNo + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                '}';
    }
}
