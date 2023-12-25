package com.ruoyi.web.controller.sym.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * balance对象 sym_dormitory_balance
 * 
 * @author sym
 * @date 2023-12-25
 */
public class SymDormitoryBalance extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 宿舍余额ID */
    private Long id;

    /** 宿舍ID（外键） */
    @Excel(name = "宿舍ID", readConverterExp = "外=键")
    private Long dormitoryId;

    /** 宿舍房间号 */
    @Excel(name = "宿舍房间号")
    private String roomNumber;

    /** 水费 */
    @Excel(name = "水费")
    private BigDecimal waterFee;

    /** 电费 */
    @Excel(name = "电费")
    private BigDecimal electricityFee;

    /** 是否欠费（0：不欠费；1：欠费） */
    @Excel(name = "是否欠费", readConverterExp = "0=：不欠费；1：欠费")
    private Long isOverdue;

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
    public void setDormitoryId(Long dormitoryId) 
    {
        this.dormitoryId = dormitoryId;
    }

    public Long getDormitoryId() 
    {
        return dormitoryId;
    }
    public void setRoomNumber(String roomNumber) 
    {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() 
    {
        return roomNumber;
    }
    public void setWaterFee(BigDecimal waterFee) 
    {
        this.waterFee = waterFee;
    }

    public BigDecimal getWaterFee() 
    {
        return waterFee;
    }
    public void setElectricityFee(BigDecimal electricityFee) 
    {
        this.electricityFee = electricityFee;
    }

    public BigDecimal getElectricityFee() 
    {
        return electricityFee;
    }
    public void setIsOverdue(Long isOverdue) 
    {
        this.isOverdue = isOverdue;
    }

    public Long getIsOverdue() 
    {
        return isOverdue;
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
            .append("dormitoryId", getDormitoryId())
            .append("roomNumber", getRoomNumber())
            .append("waterFee", getWaterFee())
            .append("electricityFee", getElectricityFee())
            .append("isOverdue", getIsOverdue())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
