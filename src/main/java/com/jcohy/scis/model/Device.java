package com.jcohy.scis.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Description  :设备（资产）表
 */
@Entity
@Table(name = "device")
public class Device implements Serializable{

    private static final long serialVersionUID = 8L;
    //Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //名字
    @Column(name = "name")
    private String name;
    //类别
    @Column(name = "category")
    private String category;
    //资产管理者id
    @Column(name = "owner_id")
    private Integer owner_id;
    //资产管理者
    @Column(name = "owner")
    private String owner;
    //租借状态
    @Column(name = "state")
    private String state;
    //损坏情况
    @Column(name = "condition")
    private String condition;
    //借用者
    @Column(name = "borrower")
    private String borrower;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public Integer getOwner_id()
    {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id)
    {
        this.owner_id = owner_id;
    }

    public String getOwner()
    {
        return owner;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getCondition()
    {
        return condition;
    }

    public void setCondition(String condition)
    {
        this.condition = condition;
    }

    public String getBorrower()
    {
        return borrower;
    }

    public void setBorrower(String borrower)
    {
        this.borrower = borrower;
    }
}
