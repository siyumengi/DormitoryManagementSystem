package com.ruoyi.web.controller.sym.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * record对象 sym_payment_record
 * 
 * @author sym
 * @date 2023-12-25
 */
public class SymPaymentRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 缴费记录ID */
    private Long id;

    /** 学生ID（外键） */
    @Excel(name = "学生ID", readConverterExp = "外=键")
    private Long studentId;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String studentName;

    /** 学生联系方式 */
    @Excel(name = "学生联系方式")
    private String studentContact;

    /** 宿舍楼ID（外键） */
    @Excel(name = "宿舍楼ID", readConverterExp = "外=键")
    private Long residenceHallId;

    /** 宿舍房间号 */
    @Excel(name = "宿舍房间号")
    private String roomNumber;

    /** 缴费金额 */
    @Excel(name = "缴费金额")
    private BigDecimal amount;

    /** 缴费状态 */
    @Excel(name = "缴费状态")
    private String paymentStatus;

    /** 缴费时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "缴费时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date paymentTime;

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
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setStudentName(String studentName) 
    {
        this.studentName = studentName;
    }

    public String getStudentName() 
    {
        return studentName;
    }
    public void setStudentContact(String studentContact) 
    {
        this.studentContact = studentContact;
    }

    public String getStudentContact() 
    {
        return studentContact;
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
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setPaymentStatus(String paymentStatus) 
    {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentStatus() 
    {
        return paymentStatus;
    }
    public void setPaymentTime(Date paymentTime) 
    {
        this.paymentTime = paymentTime;
    }

    public Date getPaymentTime() 
    {
        return paymentTime;
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
            .append("studentId", getStudentId())
            .append("studentName", getStudentName())
            .append("studentContact", getStudentContact())
            .append("residenceHallId", getResidenceHallId())
            .append("roomNumber", getRoomNumber())
            .append("amount", getAmount())
            .append("paymentStatus", getPaymentStatus())
            .append("paymentTime", getPaymentTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
