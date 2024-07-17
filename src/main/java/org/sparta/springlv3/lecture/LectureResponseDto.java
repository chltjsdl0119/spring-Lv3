package org.sparta.springlv3.lecture;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class LectureResponseDto {
    private String name;
    private Long price;
    private String description;
    private LectureCategoryEnum category;
    private String teacher;
    private LocalDate registrationDate;

    public LectureResponseDto(Lecture lecture) {
        this.name = lecture.getName();
        this.price = lecture.getPrice();
        this.description = lecture.getDescription();
        this.category = lecture.getCategory();
        this.teacher = lecture.getTeacher();
        this.registrationDate = lecture.getRegistrationDate();
    }
}
