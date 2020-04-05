package com.jcohy.scis.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Description  :项目表
 * 2020-03-17
 */
@Entity
@Table(name = "config")
public class Config {

    private static final long serialVersionUID = 8L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "project_id")
    private int project_id;

    @Column(name = "giturl")
    private String giturl;

    @Column(name = "root")
    private String root;

    @Column(name = "disk_size")
    private String disk_size;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getGiturl() {
        return giturl;
    }

    public void setGiturl(String giturl) {
        this.giturl = giturl;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getDisk_size() {
        return disk_size;
    }

    public void setDisk_size(String disk_size) {
        this.disk_size = disk_size;
    }
}
