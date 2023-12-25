package com.ruoyi.web.controller.sym.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * request对象 sym_repair_request
 * 
 * @author sym
 * @date 2023-12-25
 */
public class SymRepairRequest extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 维修请求ID */
    private Long id;

    /** 学生ID（外键） */
    @Excel(name = "学生ID", readConverterExp = "外=键")
    private Long studentId;

    /** 宿舍ID（外键） */
    @Excel(name = "宿舍ID", readConverterExp = "外=键")
    private Long dormitoryId;

    /** 宿舍房间号 */
    @Excel(name = "宿舍房间号")
    private String dormitoryRoomNumber;

    /** 维修描述 */
    @Excel(name = "维修描述")
    private String repairDescription;

    /** 维修状态 */
    @Excel(name = "维修状态")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setDormitoryId(Long dormitoryId) 
    {
        this.dormitoryId = dormitoryId;
    }

    public Long getDormitoryId() 
    {
        return dormitoryId;
    }
    public void setDormitoryRoomNumber(String dormitoryRoomNumber) 
    {
        this.dormitoryRoomNumber = dormitoryRoomNumber;
    }

    public String getDormitoryRoomNumber() 
    {
        return dormitoryRoomNumber;
    }
    public void setRepairDescription(String repairDescription) 
    {
        this.repairDescription = repairDescription;
    }

    public String getRepairDescription() 
    {
        return repairDescription;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("studentId", getStudentId())
            .append("dormitoryId", getDormitoryId())
            .append("dormitoryRoomNumber", getDormitoryRoomNumber())
            .append("repairDescription", getRepairDescription())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
