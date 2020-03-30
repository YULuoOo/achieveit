package com.jcohy.scis.model;

public class WorkingHourTable extends WorkingHour
{
    private Long staff_num;

    private String staff_name;

    public WorkingHourTable(WorkingHour workingHour, Staff staff){
        setId(workingHour.getId());
        setStaff_id(workingHour.getStaff_id());
        setWork_content(workingHour.getWork_content());
        setWork_date(workingHour.getWork_date());
        setWork_length(workingHour.getWork_length());
        staff_num = staff.getNum();
        staff_name = staff.getName();
    }

    public Long getStaff_num()
    {
        return staff_num;
    }

    public void setStaff_num(Long staff_num)
    {
        this.staff_num = staff_num;
    }

    public String getStaff_name()
    {
        return staff_name;
    }

    public void setStaff_name(String staff_name)
    {
        this.staff_name = staff_name;
    }
}