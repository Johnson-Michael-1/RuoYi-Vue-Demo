package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysStudent;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.service.ISysStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/system/student")
public class SysStudentController extends BaseController
{
    @Autowired
    private ISysStudentService studentService;

    /**
     * 获取岗位列表
     */
//    @PreAuthorize("@ss.hasPermi('system:student:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysStudent student)
    {
        startPage();
        System.out.println("11111111111");
        List<SysStudent> list = studentService.selectStudentList(student);
        for (SysStudent sysStudent : list) {
            System.out.println("sysStudent = " + sysStudent);
        }
        return getDataTable(list);
    }
    
    @Log(title = "学生信息管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:student:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysStudent student)
    {
        List<SysStudent> list = studentService.selectStudentList(student);
        ExcelUtil<SysStudent> util = new ExcelUtil<SysStudent>(SysStudent.class);
        util.exportExcel(response, list, "学生数据");
    }

    /**
     * 根据学生编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:student:query')")
    @GetMapping(value = "/{studentId}")
    public AjaxResult getInfo(@PathVariable Long studentId)
    {
        return AjaxResult.success(studentService.selectStudentByStudentId(studentId));
    }

    /**
     * 新增学生信息
     */
//    @PreAuthorize("@ss.hasPermi('system:student:add')")
    @Log(title = "学生管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysStudent student)
    {
//        if (UserConstants.NOT_UNIQUE.equals(studentService.checkPostNameUnique(post)))
//        {
//            return AjaxResult.error("新增岗位'" + student.getStudentName() + "'失败，岗位名称已存在");
//        }
//        else if (UserConstants.NOT_UNIQUE.equals(studenttService.checkPostCodeUnique(post)))
//        {
//            return AjaxResult.error("新增岗位'" + student.getPostName() + "'失败，岗位编码已存在");
//        }
//        post.setCreateBy(getUsername());
        return toAjax(studentService.insertStudent(student));

    }

    /**
     * 修改岗位
     */
//    @PreAuthorize("@ss.hasPermi('system:post:edit')")
    @Log(title = "学生管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysStudent student)
    {
//        if (UserConstants.NOT_UNIQUE.equals(postService.checkPostNameUnique(post)))
//        {
//            return AjaxResult.error("修改岗位'" + post.getPostName() + "'失败，岗位名称已存在");
//        }
//        else if (UserConstants.NOT_UNIQUE.equals(postService.checkPostCodeUnique(post)))
//        {
//            return AjaxResult.error("修改岗位'" + post.getPostName() + "'失败，岗位编码已存在");
//        }
//        post.setUpdateBy(getUsername());
        return toAjax(studentService.updateStudent(student));
    }

    /**
     * 删除岗位
     */
//    @PreAuthorize("@ss.hasPermi('system:post:remove')")
    @Log(title = "学生管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(studentService.deleteStudentByIds(ids));
    }

    /**
     * 获取岗位选择框列表
     */
    @GetMapping("/optionselect")
    public AjaxResult optionselect()
    {
        List<SysStudent> posts = studentService.selectAllStudents();
        return AjaxResult.success(posts);
    }
}
