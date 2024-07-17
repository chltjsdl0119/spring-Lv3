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
    public ResponseEntity<TeacherResponseDto> registerTeacher(@RequestHeader("Authorization") String token, @RequestBody TeacherRequestDto requestDto) {
        return teacherService.registerTeacher(token, requestDto);
    }

    @PutMapping("/teacher/{id}")
    public ResponseEntity<TeacherResponseDto> updateTeacher(@RequestHeader("Authorization") String token, @RequestBody TeacherRequestDto requestDto, @PathVariable Long id) {
        return teacherService.updateTeacher(token, requestDto, id);
    }

    @GetMapping("/teacher/{id}")
    public ResponseEntity<TeacherResponseDto> getTeacher(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        return teacherService.getTeacher(token, id);
    }

}
