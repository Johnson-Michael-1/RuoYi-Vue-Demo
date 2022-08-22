package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysStudent;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.service.ISysStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/system/socket")
public class SocketStudentController extends BaseController
{
    @Autowired
    private ISysStudentService studentService;

    /**
     * 获取学生列表
     */
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
     * 修改学生信息
     */
//    @PreAuthorize("@ss.hasPermi('system:post:edit')")
    @Log(title = "学生管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysStudent student)
    {
        return toAjax(studentService.updateStudent(student));
    }

    /**
     * 删除学生信息
     */
//    @PreAuthorize("@ss.hasPermi('system:post:remove')")
    @Log(title = "学生管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{studentIds}")
    public AjaxResult remove(@PathVariable Long[] studentIds)
    {
        return toAjax(studentService.deleteStudentByIds(studentIds));
    }

    /**
     * 获取学生选择框列表
     */
    @GetMapping("/optionselect")
    public AjaxResult optionselect()
    {
        List<SysStudent> posts = studentService.selectAllStudents();
        return AjaxResult.success(posts);
    }
}
