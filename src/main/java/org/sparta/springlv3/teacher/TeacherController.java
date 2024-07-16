package org.sparta.springlv3.teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/teacher")
    public ResponseEntity<String> teacher(@RequestHeader("Authorization") String token, @RequestBody TeacherRequestDto requestDto) {
        return teacherService.registerTeacher(token, requestDto);
    }
}
