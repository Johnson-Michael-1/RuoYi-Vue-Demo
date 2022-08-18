package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.SysStudent;

import java.util.List;

/**
 * @param
 * @author Johnson Michael
 * @date 2022 8-16  14:22:43
 * @description TODO
 * @return
 */
public interface ISysStudentService {
    List<SysStudent> selectAllStudents();

    SysStudent selectStudentByStudentId(Long id);

    SysStudent selectStudentByStudentName(String studentName);

    SysStudent selectStudentByStudentNumber(Long studentNumber);

    List<SysStudent> selectStudentList(SysStudent student);

    Integer insertStudent(SysStudent student);

    Integer updateStudent(SysStudent student);

    Integer deleteStudentById(Long studentId);

    Integer deleteStudentByIds(Long[] studentIds);
}
