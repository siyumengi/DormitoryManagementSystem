package com.ruoyi.web.controller.sym.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * student对象 sym_student
 * 
 * @author sym
 * @date 2023-12-18
 */
public class SymStudent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生ID */
    private Long id;

    /** 学号 */
    @Excel(name = "学号")
    private String studentNumber;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long age;

    /** 专业 */
    @Excel(name = "专业")
    private String major;

    /** 班级名称 */
    @Excel(name = "班级名称")
    private String className;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 宿舍楼ID（外键） */
    @Excel(name = "宿舍楼ID", readConverterExp = "外=键")
    private Long residenceHallId;

    /** 宿舍房间号 */
    @Excel(name = "宿舍房间号")
    private String dormitoryRoomNumber;

    /** 入住日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入住日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date moveInDate;

    /** 退宿日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "退宿日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date moveOutDate;

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
    public void setStudentNumber(String studentNumber) 
    {
        this.studentNumber = studentNumber;
    }

    public String getStudentNumber() 
    {
        return studentNumber;
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
    public void setAge(Long age) 
    {
        this.age = age;
    }

    public Long getAge() 
    {
        return age;
    }
    public void setMajor(String major) 
    {
        this.major = major;
    }

    public String getMajor() 
    {
        return major;
    }
    public void setClassName(String className) 
    {
        this.className = className;
    }

    public String getClassName() 
    {
        return className;
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
    public void setDormitoryRoomNumber(String dormitoryRoomNumber) 
    {
        this.dormitoryRoomNumber = dormitoryRoomNumber;
    }

    public String getDormitoryRoomNumber() 
    {
        return dormitoryRoomNumber;
    }
    public void setMoveInDate(Date moveInDate) 
    {
        this.moveInDate = moveInDate;
    }

    public Date getMoveInDate() 
    {
        return moveInDate;
    }
    public void setMoveOutDate(Date moveOutDate) 
    {
        this.moveOutDate = moveOutDate;
    }

    public Date getMoveOutDate() 
    {
        return moveOutDate;
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
            .append("studentNumber", getStudentNumber())
            .append("name", getName())
            .append("gender", getGender())
            .append("age", getAge())
            .append("major", getMajor())
            .append("className", getClassName())
            .append("phone", getPhone())
            .append("residenceHallId", getResidenceHallId())
            .append("dormitoryRoomNumber", getDormitoryRoomNumber())
            .append("moveInDate", getMoveInDate())
            .append("moveOutDate", getMoveOutDate())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
