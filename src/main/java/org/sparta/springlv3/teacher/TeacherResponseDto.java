package org.sparta.springlv3.teacher;

import lombok.Getter;

@Getter
public class TeacherResponseDto {
    private String name;
    private int experience;
    private String company;
    private String phoneNumber;
    private String introduction;

    public TeacherResponseDto(String name, int experience, String company, String phoneNumber, String introduction) {
        this.name = name;
        this.experience = experience;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;
    }
}