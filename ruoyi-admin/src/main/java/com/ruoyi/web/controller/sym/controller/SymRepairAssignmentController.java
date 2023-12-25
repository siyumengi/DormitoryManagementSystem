package com.ruoyi.web.controller.sym.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.web.controller.sym.domain.SymRepairAssignment;
import com.ruoyi.web.controller.sym.service.ISymRepairAssignmentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * assignmentController
 * 
 * @author sym
 * @date 2023-12-25
 */
@RestController
@RequestMapping("/system/assignment")
public class SymRepairAssignmentController extends BaseController
{
    @Autowired
    private ISymRepairAssignmentService symRepairAssignmentService;

    /**
     * 查询assignment列表
     */
    @PreAuthorize("@ss.hasPermi('system:assignment:list')")
    @GetMapping("/list")
    public TableDataInfo list(SymRepairAssignment symRepairAssignment)
    {
        startPage();
        List<SymRepairAssignment> list = symRepairAssignmentService.selectSymRepairAssignmentList(symRepairAssignment);
        return getDataTable(list);
    }

    /**
     * 导出assignment列表
     */
    @PreAuthorize("@ss.hasPermi('system:assignment:export')")
    @Log(title = "assignment", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SymRepairAssignment symRepairAssignment)
    {
        List<SymRepairAssignment> list = symRepairAssignmentService.selectSymRepairAssignmentList(symRepairAssignment);
        ExcelUtil<SymRepairAssignment> util = new ExcelUtil<SymRepairAssignment>(SymRepairAssignment.class);
        util.exportExcel(response, list, "assignment数据");
    }

    /**
     * 获取assignment详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:assignment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(symRepairAssignmentService.selectSymRepairAssignmentById(id));
    }

    /**
     * 新增assignment
     */
    @PreAuthorize("@ss.hasPermi('system:assignment:add')")
    @Log(title = "assignment", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SymRepairAssignment symRepairAssignment)
    {
        return toAjax(symRepairAssignmentService.insertSymRepairAssignment(symRepairAssignment));
    }

    /**
     * 修改assignment
     */
    @PreAuthorize("@ss.hasPermi('system:assignment:edit')")
    @Log(title = "assignment", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SymRepairAssignment symRepairAssignment)
    {
        return toAjax(symRepairAssignmentService.updateSymRepairAssignment(symRepairAssignment));
    }

    /**
     * 删除assignment
     */
    @PreAuthorize("@ss.hasPermi('system:assignment:remove')")
    @Log(title = "assignment", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(symRepairAssignmentService.deleteSymRepairAssignmentByIds(ids));
    }
}
