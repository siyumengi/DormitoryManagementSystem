package com.ruoyi.web.controller.sym.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * dormitory对象 sym_dormitory
 * 
 * @author sym
 * @date 2023-12-18
 */
public class SymDormitory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 宿舍ID */
    private Long id;

    /** 宿舍楼ID（外键） */
    @Excel(name = "宿舍楼ID", readConverterExp = "外=键")
    private Long residenceHallId;

    /** 宿舍房间号 */
    @Excel(name = "宿舍房间号")
    private String roomNumber;

    /** 宿舍面积或大小 */
    @Excel(name = "宿舍面积或大小")
    private String size;

    /** 实际入住的学生数量 */
    @Excel(name = "实际入住的学生数量")
    private Long occupancyCount;

    /** 宿舍状态 */
    @Excel(name = "宿舍状态")
    private String status;

    /** 总床位数量 */
    @Excel(name = "总床位数量")
    private Long totalBedCount;

    /** 剩余床位数量 */
    @Excel(name = "剩余床位数量")
    private Long remainingBedCount;

    /** 宿舍描述 */
    @Excel(name = "宿舍描述")
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
    public void setResidenceHallId(Long residenceHallId) 
    {
        this.residenceHallId = residenceHallId;
    }

    public Long getResidenceHallId() 
    {
        return residenceHallId;
    }
    public void setRoomNumber(String roomNumber) 
    {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() 
    {
        return roomNumber;
    }
    public void setSize(String size) 
    {
        this.size = size;
    }

    public String getSize() 
    {
        return size;
    }
    public void setOccupancyCount(Long occupancyCount) 
    {
        this.occupancyCount = occupancyCount;
    }

    public Long getOccupancyCount() 
    {
        return occupancyCount;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setTotalBedCount(Long totalBedCount) 
    {
        this.totalBedCount = totalBedCount;
    }

    public Long getTotalBedCount() 
    {
        return totalBedCount;
    }
    public void setRemainingBedCount(Long remainingBedCount) 
    {
        this.remainingBedCount = remainingBedCount;
    }

    public Long getRemainingBedCount() 
    {
        return remainingBedCount;
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
            .append("residenceHallId", getResidenceHallId())
            .append("roomNumber", getRoomNumber())
            .append("size", getSize())
            .append("occupancyCount", getOccupancyCount())
            .append("status", getStatus())
            .append("totalBedCount", getTotalBedCount())
            .append("remainingBedCount", getRemainingBedCount())
            .append("description", getDescription())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
