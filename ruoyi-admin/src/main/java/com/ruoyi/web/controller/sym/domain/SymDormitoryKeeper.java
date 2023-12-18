package com.ruoyi.web.controller.sym.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * keeper对象 sym_dormitory_keeper
 * 
 * @author sym
 * @date 2023-12-18
 */
public class SymDormitoryKeeper extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 宿管ID */
    private Long id;

    /** 宿管姓名 */
    @Excel(name = "宿管姓名")
    private String name;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 负责的宿舍楼ID（外键） */
    @Excel(name = "负责的宿舍楼ID", readConverterExp = "外=键")
    private Long residenceHallId;

    /** 入职日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入职日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date hireDate;

    /** 工作职责 */
    @Excel(name = "工作职责")
    private String duties;

    /** 上级主管ID（外键） */
    @Excel(name = "上级主管ID", readConverterExp = "外=键")
    private Long supervisorId;

    /** 宿管描述 */
    @Excel(name = "宿管描述")
    private String description;

    /** 是否删除标记 */
    @Excel(name = "是否删除标记")
    private Long isDeleted;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setResidenceHallId(Long residenceHallId) 
    {
        this.residenceHallId = residenceHallId;
    }

    public Long getResidenceHallId() 
    {
        return residenceHallId;
    }
    public void setHireDate(Date hireDate) 
    {
        this.hireDate = hireDate;
    }

    public Date getHireDate() 
    {
        return hireDate;
    }
    public void setDuties(String duties) 
    {
        this.duties = duties;
    }

    public String getDuties() 
    {
        return duties;
    }
    public void setSupervisorId(Long supervisorId) 
    {
        this.supervisorId = supervisorId;
    }

    public Long getSupervisorId() 
    {
        return supervisorId;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setIsDeleted(Long isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Long getIsDeleted() 
    {
        return isDeleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("gender", getGender())
            .append("phone", getPhone())
            .append("residenceHallId", getResidenceHallId())
            .append("hireDate", getHireDate())
            .append("duties", getDuties())
            .append("supervisorId", getSupervisorId())
            .append("description", getDescription())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
