package org.sparta.springlv3.teacher;

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
public class TeacherService {

    private final AdminRepository adminRepository;
    private final TeacherRepository teacherRepository;
    private final JwtUtil jwtUtil;

    public ResponseEntity<TeacherResponseDto> registerTeacher(String token, TeacherRequestDto requestDto) {
        String email = jwtUtil.extractEmail((token.substring(7)));

        Optional<Admin> adminOpt = adminRepository.findByEmail(email);

        if (adminOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Admin admin = adminOpt.get();

        if (admin.getAuthority() != AdminAuthorityEnum.MANAGER) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Teacher teacher = new Teacher(requestDto);
        teacherRepository.save(teacher);

        return new ResponseEntity<>(new TeacherResponseDto(teacher),HttpStatus.OK);
    }

    public ResponseEntity<TeacherResponseDto> updateTeacher(String token, TeacherRequestDto requestDto, Long id) {

        String email = jwtUtil.extractEmail((token.substring(7)));

        Optional<Admin> adminOpt = adminRepository.findByEmail(email);

        if (adminOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Admin admin = adminOpt.get();

        if (admin.getAuthority() != AdminAuthorityEnum.MANAGER) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Teacher> teacherOpt = teacherRepository.findById(id);

        if (teacherOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Teacher teacher = teacherOpt.get();

        teacher.setCareer(requestDto.getCareer());
        teacher.setCompany(requestDto.getCompany());
        teacher.setPhoneNumber(requestDto.getPhoneNumber());
        teacher.setIntroduction(requestDto.getIntroduction());
        teacherRepository.save(teacher);

        return new ResponseEntity<>(new TeacherResponseDto(teacher),HttpStatus.OK);
    }

    public ResponseEntity<TeacherResponseDto> getTeacher(String token, Long id) {
        String email = jwtUtil.extractEmail((token.substring(7)));

        Optional<Admin> adminOpt = adminRepository.findByEmail(email);
        if (adminOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Teacher> teacherOpt = teacherRepository.findById(id);

        if (teacherOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Teacher teacher = teacherOpt.get();

        return new ResponseEntity<>(new TeacherResponseDto(teacher),HttpStatus.OK);
    }
}
