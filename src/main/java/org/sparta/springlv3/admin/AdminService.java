package org.sparta.springlv3.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

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
}
