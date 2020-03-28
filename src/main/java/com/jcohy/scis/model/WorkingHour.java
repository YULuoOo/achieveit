package com.jcohy.scis.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Description  :工时表
 */
@Entity
@Table(name = "workinghour")
public class WorkingHour implements Serializable{

    private static final long serialVersionUID = 8L;
    //Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //员工id
    @Column(name = "staff_id")
    private Integer staff_id;
    //工作内容
    @Column(name = "work_content")
    private String work_content;
    //日期
    @Column(name = "work_date")
    private Date work_date;
    //时长
    @Column(name = "work_length")
    private Float work_length;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(Integer staff_id) {
        this.staff_id = staff_id;
    }

    public String getWork_content() {
        return work_content;
    }

    public void setWork_content(String work_content) {
        this.work_content = work_content;
    }

    public Date getWork_date() {
        return work_date;
    }

    public void setWork_date(Date work_date) {
        this.work_date = work_date;
    }

    public Float getWork_length() {
        return work_length;
    }

    public void setWork_length(Float work_length) {
        this.work_length = work_length;
    }
}