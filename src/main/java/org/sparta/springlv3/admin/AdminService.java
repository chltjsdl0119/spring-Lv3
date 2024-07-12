package org.sparta.springlv3.admin;

import lombok.RequiredArgsConstructor;
import org.sparta.springlv3.jwt.JwtUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public ResponseEntity<String> signup(AdminRequestDto requestDto) {
        String email = requestDto.getEmail();
        String password = passwordEncoder.encode(requestDto.getPassword());

        if (adminRepository.existsByEmail(email)) {
            return ResponseEntity.badRequest().body("중복된 Email 입니다.");
        }

        if (requestDto.getDepartment() == AdminDepartmentEnum.DEVELOPMENT || requestDto.getDepartment() == AdminDepartmentEnum.CURRICULUM) {
            requestDto.setAuthority(AdminAuthorityEnum.MANAGER);
        } else {
            requestDto.setAuthority(AdminAuthorityEnum.STAFF);
        }

        adminRepository.save(new Admin(requestDto, password));

        return ResponseEntity.ok().body("관리자 회원가입 성공");
    }

    public ResponseEntity<String> login(AdminRequestDto requestDto) {
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();

        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Email 입니다."));

        if (!passwordEncoder.matches(password, admin.getPassword())) {
            return ResponseEntity.badRequest().body("잘못된 비밀번호입니다.");
        }

        String token = jwtUtil.generateToken(email);

        // 응답 헤더에 JWT 토큰 추가
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);

        return ResponseEntity.ok().headers(headers).body("관리자 로그인 성공");
    }
}
