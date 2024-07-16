package org.sparta.springlv3.lecture;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @PostMapping("/lecture")
    public ResponseEntity<LectureResponseDto> registerLecture(@RequestHeader("Authorization") String token, @RequestBody LectureRequestDto requestDto) {
        return lectureService.registerLecture(token, requestDto);
    }
}
