package com.ruoyi.web.controller.sym.domain;

import com.ruoyi.common.annotation.Excel;

public class CurrentUser  {

    /** 学号 */
    @Excel(name = "学号")
    private String studentNumber;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 宿舍楼ID（外键） */
    @Excel(name = "宿舍楼ID", readConverterExp = "外=键")
    private Long residenceHallId;

    /** 宿舍房间号 */
    @Excel(name = "宿舍房间号")
    private String dormitoryRoomNumber;

    public String getStudentNumber() {
        return "12312";
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return "aaa";
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getResidenceHallId() {
        return 1L;
    }

    public void setResidenceHallId(Long residenceHallId) {
        this.residenceHallId = residenceHallId;
    }

    public String getDormitoryRoomNumber() {
        return "101";
    }

    public void setDormitoryRoomNumber(String dormitoryRoomNumber) {
        this.dormitoryRoomNumber = dormitoryRoomNumber;
    }
}
