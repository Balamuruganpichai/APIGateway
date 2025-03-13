package com.citpl.validation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleMasterDto {
    private String id;
    private String method;
    private String relativePath;
    private String permission;
    private List<String> roles;
}
