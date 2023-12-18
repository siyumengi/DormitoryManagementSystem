package com.ruoyi.web.controller.sym.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * hall对象 sym_residence_hall
 * 
 * @author sym
 * @date 2023-12-18
 */
public class SymResidenceHall extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 宿舍楼ID */
    private Long id;

    /** 宿舍楼名称 */
    @Excel(name = "宿舍楼名称")
    private String name;

    /** 宿舍楼地址 */
    @Excel(name = "宿舍楼地址")
    private String address;

    /** 建造年份 */
    @Excel(name = "建造年份")
    private String constructionYear;

    /** 设施信息 */
    @Excel(name = "设施信息")
    private String facilities;

    /** 宿舍楼描述 */
    @Excel(name = "宿舍楼描述")
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
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setConstructionYear(String constructionYear) 
    {
        this.constructionYear = constructionYear;
    }

    public String getConstructionYear() 
    {
        return constructionYear;
    }
    public void setFacilities(String facilities) 
    {
        this.facilities = facilities;
    }

    public String getFacilities() 
    {
        return facilities;
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
            .append("address", getAddress())
            .append("constructionYear", getConstructionYear())
            .append("facilities", getFacilities())
            .append("description", getDescription())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
