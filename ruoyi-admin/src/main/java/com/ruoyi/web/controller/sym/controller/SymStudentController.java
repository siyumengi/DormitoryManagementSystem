package com.ruoyi.web.controller.sym.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.web.controller.sym.domain.SymStudent;
import com.ruoyi.web.controller.sym.service.ISymStudentService;
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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * studentController
 * 
 * @author sym
 * @date 2023-12-18
 */
@RestController
@RequestMapping("/system/student")
public class SymStudentController extends BaseController
{
    @Autowired
    private ISymStudentService symStudentService;

    /**
     * 查询student列表
     */
    @PreAuthorize("@ss.hasPermi('system:student:list')")
    @GetMapping("/list")
    public TableDataInfo list(SymStudent symStudent)
    {
        startPage();
        List<SymStudent> list = symStudentService.selectSymStudentList(symStudent);
        return getDataTable(list);
    }

    /**
     * 导出student列表
     */
    @PreAuthorize("@ss.hasPermi('system:student:export')")
    @Log(title = "student", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SymStudent symStudent)
    {
        List<SymStudent> list = symStudentService.selectSymStudentList(symStudent);
        ExcelUtil<SymStudent> util = new ExcelUtil<SymStudent>(SymStudent.class);
        util.exportExcel(response, list, "student数据");
    }

    /**
     * 获取student详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:student:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(symStudentService.selectSymStudentById(id));
    }

    /**
     * 新增student
     */
    @PreAuthorize("@ss.hasPermi('system:student:add')")
    @Log(title = "student", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SymStudent symStudent)
    {

        return toAjax(symStudentService.insertSymStudent(symStudent));
    }

    /**
     * 修改student
     */
    @PreAuthorize("@ss.hasPermi('system:student:edit')")
    @Log(title = "student", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SymStudent symStudent)
    {
        return toAjax(symStudentService.updateSymStudent(symStudent));
    }

    /**
     * 删除student
     */
    @PreAuthorize("@ss.hasPermi('system:student:remove')")
    @Log(title = "student", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(symStudentService.deleteSymStudentByIds(ids));
    }
}
