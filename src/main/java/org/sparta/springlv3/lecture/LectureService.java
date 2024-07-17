package org.sparta.springlv3.lecture;

import lombok.RequiredArgsConstructor;
import org.sparta.springlv3.admin.Admin;
import org.sparta.springlv3.admin.AdminAuthorityEnum;
import org.sparta.springlv3.admin.AdminRepository;
import org.sparta.springlv3.jwt.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final AdminRepository adminRepository;
    private final JwtUtil jwtUtil;

    public ResponseEntity<LectureResponseDto> registerLecture(String token, LectureRequestDto requestDto) {
        String email = jwtUtil.extractEmail((token.substring(7)));

        Optional<Admin> adminOpt = adminRepository.findByEmail(email);

        if (adminOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Admin admin = adminOpt.get();

        if (admin.getAuthority() != AdminAuthorityEnum.MANAGER) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Lecture lecture = new Lecture(requestDto);
        lectureRepository.save(lecture);

        return new ResponseEntity<>(new LectureResponseDto(lecture), HttpStatus.OK);
    }

    public ResponseEntity<LectureResponseDto> updateLecture(String token, LectureRequestDto requestDto, Long id) {
        String email = jwtUtil.extractEmail((token.substring(7)));

        Optional<Admin> adminOpt = adminRepository.findByEmail(email);

        if (adminOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Admin admin = adminOpt.get();

        if (admin.getAuthority() != AdminAuthorityEnum.MANAGER) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Lecture> lectureOpt = lectureRepository.findById(id);

        if (lectureOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Lecture lecture = lectureOpt.get();

        lecture.setName(requestDto.getName());
        lecture.setPrice(requestDto.getPrice());
        lecture.setDescription(requestDto.getDescription());
        lecture.setCategory(requestDto.getCategory());
        lectureRepository.save(lecture);

        return new ResponseEntity<>(new LectureResponseDto(lecture), HttpStatus.OK);
    }
}
