package org.sparta.springlv3.lecture;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @PostMapping("/lecture")
    public ResponseEntity<LectureResponseDto> registerLecture(@RequestHeader("Authorization") String token, @RequestBody LectureRequestDto requestDto) {
        return lectureService.registerLecture(token, requestDto);
    }

    @PutMapping("/lecture/{id}")
    public ResponseEntity<LectureResponseDto> updateLecture (@RequestHeader("Authorization") String token, @RequestBody LectureRequestDto requestDto, @PathVariable Long id) {
        return lectureService.updateLecture(token, requestDto, id);
    }

    @GetMapping("/lecture/{id}")
    public ResponseEntity<LectureResponseDto> getLecture( @RequestHeader("Authorization") String token,@PathVariable Long id) {
        return lectureService.getLecture(token, id);
    }

    @GetMapping("/lectures/{teacherId}")
    public ResponseEntity<List<LectureResponseDto>> getLectures(@RequestHeader("Authorization") String token, @PathVariable Long teacherId) {
        return lectureService.getLectures(token, teacherId);
    }
}
