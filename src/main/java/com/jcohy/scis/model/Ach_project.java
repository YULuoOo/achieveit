package com.jcohy.scis.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Description  :项目表
 * 2020-03-17
 */
@Entity
@Table(name = "ach_project")
public class Ach_project {

    private static final long serialVersionUID = 8L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "pro_name")
    private String pro_name;

    @Column(name = "pro_desc")
    private String pro_desc;

    @Column(name = "pro_tech")
    private String pro_tech;

    @Column(name = "pro_area")
    private String pro_area;

    @Column(name = "pro_func")
    private String pro_func;

    @Column(name = "pro_status")
    private int pro_status;

    @Column(name = "pro_enddate")
    private Date pro_enddate;

    @Column(name = "pro_startdate")
    private Date pro_startdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public String getPro_desc() {
        return pro_desc;
    }

    public void setPro_desc(String pro_desc) {
        this.pro_desc = pro_desc;
    }

    public String getPro_tech() {
        return pro_tech;
    }

    public void setPro_tech(String pro_tech) {
        this.pro_tech = pro_tech;
    }

    public String getPro_area() {
        return pro_area;
    }

    public void setPro_area(String pro_area) {
        this.pro_area = pro_area;
    }

    public String getPro_func() {
        return pro_func;
    }

    public void setPro_func(String pro_func) {
        this.pro_func = pro_func;
    }

    public int getPro_status() {
        return pro_status;
    }

    public void setPro_status(int pro_status) {
        this.pro_status = pro_status;
    }

    public Date getPro_enddate() {
        return pro_enddate;
    }

    public void setPro_enddate(Date pro_enddate) {
        this.pro_enddate = pro_enddate;
    }

    public Date getPro_startdate() {
        return pro_startdate;
    }

    public void setPro_startdate(Date pro_startdate) {
        this.pro_startdate = pro_startdate;
    }
}
