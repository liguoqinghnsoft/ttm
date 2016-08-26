package com.ttm.basic.api.dto;

import java.io.Serializable;

/**
 * Created by liguoqing on 2016/4/18.
 */
public class ReactorDTO implements Serializable {

    private static final long serialVersionUID = 1187111210727114833L;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public String toString() {
        return "ReactorDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
