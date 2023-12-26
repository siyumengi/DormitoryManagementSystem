package com.ruoyi.web.controller.sym.service.impl;

import java.util.Currency;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.web.controller.sym.domain.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.web.controller.sym.mapper.SymRepairRequestMapper;
import com.ruoyi.web.controller.sym.domain.SymRepairRequest;
import com.ruoyi.web.controller.sym.service.ISymRepairRequestService;

/**
 * requestService业务层处理
 *
 * @author sym
 * @date 2023-12-25
 */
@Service
public class SymRepairRequestServiceImpl implements ISymRepairRequestService {
    @Autowired
    private SymRepairRequestMapper symRepairRequestMapper;

    private final CurrentUser currentUser = new CurrentUser();

    /**
     * 查询request
     *
     * @param id request主键
     * @return request
     */
    @Override
    public SymRepairRequest selectSymRepairRequestById(Long id) {
        return symRepairRequestMapper.selectSymRepairRequestById(id);
    }

    /**
     * 查询request列表
     *
     * @param symRepairRequest request
     * @return request
     */
    @Override
    public List<SymRepairRequest> selectSymRepairRequestList(SymRepairRequest symRepairRequest) {
        return symRepairRequestMapper.selectSymRepairRequestList(symRepairRequest);
    }

    /**
     * 新增request
     *
     * @param symRepairRequest request
     * @return 结果
     */
    @Override
    public int insertSymRepairRequest(SymRepairRequest symRepairRequest) {
        symRepairRequest.setCreateTime(DateUtils.getNowDate());
        return symRepairRequestMapper.insertSymRepairRequest(symRepairRequest);
    }

    /**
     * 修改request
     *
     * @param symRepairRequest request
     * @return 结果
     */
    @Override
    public int updateSymRepairRequest(SymRepairRequest symRepairRequest) {
        // 维修映射 0-待处理', '1-已派工', '2-维修中', '3-已完成'
        // 根据维修请求的状态进行相应状态的设置
        if (symRepairRequest.getStatus().equals("0")) {
            symRepairRequest.setStatus("待处理");
        } else if (symRepairRequest.getStatus().equals("1")) {
            symRepairRequest.setStatus("已派工");
        } else if (symRepairRequest.getStatus().equals("2")) {
            symRepairRequest.setStatus("维修中");
        } else if (symRepairRequest.getStatus().equals("3")) {
            symRepairRequest.setStatus("已完成");
        }

        // 更新维修请求的更新时间
        symRepairRequest.setUpdateTime(DateUtils.getNowDate());

        // 更新维修请求信息至数据库
        return symRepairRequestMapper.updateSymRepairRequest(symRepairRequest);

    }

    /**
     * 批量删除request
     *
     * @param ids 需要删除的request主键
     * @return 结果
     */
    @Override
    public int deleteSymRepairRequestByIds(Long[] ids) {
        return symRepairRequestMapper.deleteSymRepairRequestByIds(ids);
    }

    /**
     * 删除request信息
     *
     * @param id request主键
     * @return 结果
     */
    @Override
    public int deleteSymRepairRequestById(Long id) {
        return symRepairRequestMapper.deleteSymRepairRequestById(id);
    }

    @Override
    public List<SymRepairRequest> selectRequestList(SymRepairRequest symRepairRequest) {

        // 获取当前用户的宿舍楼ID
        Long residenceHallId = currentUser.getResidenceHallId();
        // 获取当前用户的宿舍房间号
        String dormitoryRoomNumber = currentUser.getDormitoryRoomNumber();

        // 创建一个SymRepairRequest对象
        SymRepairRequest symRepairRequest1 = new SymRepairRequest();
        // 将宿舍楼ID设置到SymRepairRequest对象中
        symRepairRequest1.setDormitoryId(residenceHallId);
        // 将宿舍房间号设置到SymRepairRequest对象中
        symRepairRequest1.setDormitoryRoomNumber(dormitoryRoomNumber);
        // 通过symRepairRequestMapper对象选择维修请求列表
        return symRepairRequestMapper.selectSymRepairRequestList(symRepairRequest1);


    }

    @Override
    public int insertRequest(SymRepairRequest symRepairRequest) {
        String studentNumber = currentUser.getStudentNumber();  // 获取当前用户的学号
        if (studentNumber == null) {  // 如果学号为空
            throw new RuntimeException("请先登录");  // 抛出运行时异常，提示用户先登录
        }
        symRepairRequest.setStudentId(Long.parseLong(studentNumber));  // 将学号转换为长整型并设置为报修请求的学生ID
        return symRepairRequestMapper.insertSymRepairRequest(symRepairRequest);  // 调用报修请求数据存储的插入方法，将报修请求插入数据库并返回结果

    }
}
