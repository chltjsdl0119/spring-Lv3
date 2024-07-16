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

        return ResponseEntity.ok().build();
    }
}
