package com.ai.cas.domain;

import oracle.sql.DATE;

/**
 * Created by jiahh on 2018/7/13.
 */
public class SysSwitch {
    private String dict_name;
    private String item_name;
    private String item_no;
    private int item_num;
    private String item_state;
    private String item_desc;
    private DATE create_date;

    public String getDict_name() {
        return dict_name;
    }

    public void setDict_name(String dict_name) {
        this.dict_name = dict_name;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_no() {
        return item_no;
    }

    public void setItem_no(String item_no) {
        this.item_no = item_no;
    }

    public int getItem_num() {
        return item_num;
    }

    public void setItem_num(int item_num) {
        this.item_num = item_num;
    }

    public String getItem_state() {
        return item_state;
    }

    public void setItem_state(String item_state) {
        this.item_state = item_state;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }

    public DATE getCreate_date() {
        return create_date;
    }

    public void setCreate_date(DATE create_date) {
        this.create_date = create_date;
    }
}
