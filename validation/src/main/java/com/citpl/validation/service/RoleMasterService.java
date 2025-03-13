package com.citpl.validation.service;

import com.citpl.validation.dto.RoleMasterDto;
import com.citpl.validation.model.RoleMasterModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleMasterService {
    Boolean getRoleMaster(String path, String method, List<String> roles);
}
