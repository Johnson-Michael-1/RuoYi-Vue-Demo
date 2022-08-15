package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * @param
 * @author Johnson Michael
 * @date
 * @description TODO
 * @return
 */
public class SysStudent extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long studentId;

    private String studentName;

    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private char studentSex;

    private int studentAge;

    private String studentClassroom;

    private String studentNumber;

    private String studentCollege;

    private String studentMajor;

    @Excel(name = "入学时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    private Date enrollDate;

    private Byte studentPhoto;

    private String studentHobby;

    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private char studentStatus;


    @Override
    public String toString() {
        return "SysStudent{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentSex=" + studentSex +
                ", studentAge=" + studentAge +
                ", studentClassroom='" + studentClassroom + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", studentCollege='" + studentCollege + '\'' +
                ", studentMajor='" + studentMajor + '\'' +
                ", enrollDate=" + enrollDate +
                ", studentPhoto=" + studentPhoto +
                ", studentHobby='" + studentHobby + '\'' +
                ", studentStatus=" + studentStatus +
                '}';
    }

    public static long getSerialVesionUID() {
        return serialVersionUID;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudnetName() {
        return studentName;
    }

    public void setStudnetName(String studnetName) {
        this.studentName = studnetName;
    }

    public char getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(char studentSex) {
        this.studentSex = studentSex;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    public String getStudentClassroom() {
        return studentClassroom;
    }

    public void setStudentClassroom(String studentClassroom) {
        this.studentClassroom = studentClassroom;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentCollege() {
        return studentCollege;
    }

    public void setStudentCollege(String studentCollege) {
        this.studentCollege = studentCollege;
    }

    public String getStudentMajor() {
        return studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public Byte getStudentPhoto() {
        return studentPhoto;
    }

    public void setStudentPhoto(Byte studentPhoto) {
        this.studentPhoto = studentPhoto;
    }

    public String getStudentHobby() {
        return studentHobby;
    }

    public void setStudentHobby(String studentHobby) {
        this.studentHobby = studentHobby;
    }

    public char getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(char studentStatus) {
        this.studentStatus = studentStatus;
    }
}
