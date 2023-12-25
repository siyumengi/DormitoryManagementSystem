package com.ruoyi.web.controller.sym.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * assignment对象 sym_repair_assignment
 * 
 * @author sym
 * @date 2023-12-25
 */
public class SymRepairAssignment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 维修派工ID */
    private Long id;

    /** 维修请求ID（外键） */
    @Excel(name = "维修请求ID", readConverterExp = "外=键")
    private Long repairRequestId;

    /** 宿管ID（外键） */
    @Excel(name = "宿管ID", readConverterExp = "外=键")
    private Long dormitoryKeeperId;

    /** 派工时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "派工时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date assignmentTime;

    /** 开始维修时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始维修时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startRepairTime;

    /** 完成维修时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完成维修时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date finishRepairTime;

    /** 派工描述 */
    @Excel(name = "派工描述")
    private String description;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRepairRequestId(Long repairRequestId) 
    {
        this.repairRequestId = repairRequestId;
    }

    public Long getRepairRequestId() 
    {
        return repairRequestId;
    }
    public void setDormitoryKeeperId(Long dormitoryKeeperId) 
    {
        this.dormitoryKeeperId = dormitoryKeeperId;
    }

    public Long getDormitoryKeeperId() 
    {
        return dormitoryKeeperId;
    }
    public void setAssignmentTime(Date assignmentTime) 
    {
        this.assignmentTime = assignmentTime;
    }

    public Date getAssignmentTime() 
    {
        return assignmentTime;
    }
    public void setStartRepairTime(Date startRepairTime) 
    {
        this.startRepairTime = startRepairTime;
    }

    public Date getStartRepairTime() 
    {
        return startRepairTime;
    }
    public void setFinishRepairTime(Date finishRepairTime) 
    {
        this.finishRepairTime = finishRepairTime;
    }

    public Date getFinishRepairTime() 
    {
        return finishRepairTime;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("repairRequestId", getRepairRequestId())
            .append("dormitoryKeeperId", getDormitoryKeeperId())
            .append("assignmentTime", getAssignmentTime())
            .append("startRepairTime", getStartRepairTime())
            .append("finishRepairTime", getFinishRepairTime())
            .append("description", getDescription())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
