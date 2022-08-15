package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.SysStudent;

/**
 * @param
 * @author Johnson Michael
 * @date 2022.8.15 20.04.56
 * @description TODO
 * @return
 */
public interface SysStudentMapper {
    SysStudent selectAllStudents();

    SysStudent selectStudentByStudentName(String studentName);




}
