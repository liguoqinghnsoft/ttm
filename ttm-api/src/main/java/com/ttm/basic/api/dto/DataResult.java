package com.ttm.basic.api.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Created by liguoqing on 2016/9/6.
 */
@JacksonXmlRootElement(localName = "response")
public class DataResult extends DataBaseResult{

    @JacksonXmlProperty(localName = "resp")
    @JacksonXmlElementWrapper(useWrapping = false)
    public UserDTO[] getUserDTOs() {
        return userDTOs;
    }

    public void setUserDTOs(UserDTO[] userDTOs) {
        this.userDTOs = userDTOs;
    }

    private UserDTO[] userDTOs;

}
