package org.sparta.springlv3.lecture;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class LectureResponseDto {
    private String name;
    private Long price;
    private String introduction;
    private LectureCategoryEnum category;
    private String teacher;
    private LocalDate registrationDate;
}
