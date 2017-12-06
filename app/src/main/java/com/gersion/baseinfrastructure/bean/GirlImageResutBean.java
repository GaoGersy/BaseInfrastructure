package com.gersion.baseinfrastructure.bean;

import java.util.List;

/**
 * Created by aa326 on 2017/11/29.
 */

public class GirlImageResutBean extends BaseResultBean {

    private List<GirlImage> data;

    public List<GirlImage> getData() {
        return data;
    }

    public void setData(List<GirlImage> data) {
        this.data = data;
    }

}