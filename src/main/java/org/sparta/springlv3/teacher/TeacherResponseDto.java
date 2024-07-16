package org.sparta.springlv3.teacher;

import lombok.Getter;

@Getter
public class TeacherResponseDto {
    private String name;
    private String career;
    private String company;
    private String phoneNumber;
    private String introduction;

    public TeacherResponseDto(Teacher teacher) {
        this.name = teacher.getName();
        this.career = teacher.getCareer();
        this.company = teacher.getCompany();
        this.phoneNumber = teacher.getPhoneNumber();
        this.introduction = teacher.getIntroduction();
    }
}