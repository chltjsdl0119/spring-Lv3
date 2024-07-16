package org.sparta.springlv3.teacher;

import lombok.RequiredArgsConstructor;
import org.sparta.springlv3.admin.Admin;
import org.sparta.springlv3.admin.AdminRepository;
import org.sparta.springlv3.jwt.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final AdminRepository adminRepository;
    private final TeacherRepository teacherRepository;
    private final JwtUtil jwtUtil;

    public ResponseEntity<String> registerTeacher(String token, TeacherRequestDto requestDto) {
        String email = jwtUtil.extractEmail((token.substring(7)));

        Optional<Admin> admin = adminRepository.findByEmail(email);

        if (admin.isEmpty()) {
            return ResponseEntity.badRequest().body("Admin not found");
        }

        Teacher teacher = new Teacher(requestDto);
        teacherRepository.save(teacher);

        return ResponseEntity.ok("Teacher registered successfully");
    }
}
