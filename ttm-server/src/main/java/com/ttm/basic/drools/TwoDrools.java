package com.ttm.basic.drools;

import java.util.Date;

/**
 * Created by liguoqing on 2016/6/30.
 */
public class TwoDrools {

    private Boolean valid = true;

    public Date getDateApply() {
        return dateApply;
    }

    public void setDateApply(Date dateApply) {
        this.dateApply = dateApply;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    private Date dateApply;

}
