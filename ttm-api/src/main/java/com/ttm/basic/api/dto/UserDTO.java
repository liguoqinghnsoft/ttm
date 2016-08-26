package com.ttm.basic.api.dto;


import com.ttm.basic.validator.inter.Email;
import com.ttm.basic.validator.inter.IdCard;
import com.ttm.basic.validator.inter.MobilePhone;

import java.io.Serializable;

/**
 * Created by liguoqing on 2016/7/21.
 */
public class UserDTO implements Serializable{

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private Long id;
    @Email(message = "{email.error}")
    private String email;
    private String userName;
    @MobilePhone(message = "{mobilePhone.error}")
    private String phone;

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @IdCard(message = "{idCard.error}")
    private String idCard;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", idCard='" + idCard + '\'' +
                '}';
    }
}
