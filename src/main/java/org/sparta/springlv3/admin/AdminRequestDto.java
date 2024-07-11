package org.sparta.springlv3.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminRequestDto {
    private String email;
    private String password;
    private AdminDepartmentEnum department;
    private AdminAuthorityEnum authority;
}
