package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.SysStudent;

import java.util.List;

/**
 * @param
 * @author Johnson Michael
 * @date 2022.8.15 20.04.56
 * @description TODO
 * @return
 */
public interface SysStudentMapper {
    List<SysStudent> selectAllStudents();

    SysStudent selectStudentByStudentId(Long id);

    SysStudent selectStudentByStudentName(String studentName);

    SysStudent selectStudentByStudentNumber(Long studentNumber);

    List<SysStudent> selectStudentList(SysStudent student);

    Integer insertStudent(SysStudent student);

    Integer updateStudent(SysStudent student);

    Integer deleteStudentById(Long id);

    Integer deleteStudentByIds(Long[] ids);
}
