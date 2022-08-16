package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.SysStudent;
import com.ruoyi.system.mapper.SysStudentMapper;
import com.ruoyi.system.service.ISysStudentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @param
 * @author Johnson Michael
 * @date 2022 8.15 14:25:08
 * @description TODO
 * @return
 */
@Service
public class SysStudentServiceImpl implements ISysStudentService {
    private SysStudentMapper studentMapper;

    @Override
    public List<SysStudent> selectAllStudents() {
        return studentMapper.selectAllStudents();
    }

    @Override
    public SysStudent selectStudentByStudentId(Long id) {
        return studentMapper.selectStudentByStudentId(id);
    }

    @Override
    public SysStudent selectStudentByStudentName(String studentName) {
        return studentMapper.selectStudentByStudentName(studentName);
    }

    @Override
    public SysStudent selectStudentByStudentNumber(Long studentNumber) {
        return studentMapper.selectStudentByStudentNumber(studentNumber);
    }

    @Override
    public List<SysStudent> selectStudentList(SysStudent student) {
        return studentMapper.selectStudentList(student);
    }

    @Override
    public Integer insertStudent(SysStudent student) {
        return studentMapper.insertStudent(student);
    }

    @Override
    public Integer updateStudent(SysStudent student) {
        return studentMapper.updateStudent(student);
    }

    @Override
    public Integer deleteStudentById(Long id) {
        return studentMapper.deleteStudentById(id);
    }

    @Override
    public Integer deleteStudentByIds(Long[] ids) {
        return studentMapper.deleteStudentByIds(ids);
    }
}
