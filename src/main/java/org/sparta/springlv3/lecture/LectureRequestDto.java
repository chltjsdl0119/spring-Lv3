package org.sparta.springlv3.lecture;

import lombok.Getter;

@Getter
public class LectureRequestDto {
    private String name;
    private Long price;
    private String description;
    private LectureCategoryEnum category;
    private String teacher;
}
