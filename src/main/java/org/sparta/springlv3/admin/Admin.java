package org.sparta.springlv3.admin;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "admin")
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AdminDepartmentEnum department;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AdminAuthorityEnum authority;

    public Admin(AdminRequestDto requestDto, String password) {
        this.email = requestDto.getEmail();
        this.password = password;
        this.department = requestDto.getDepartment();
        this.authority = requestDto.getAuthority();
    }
}
