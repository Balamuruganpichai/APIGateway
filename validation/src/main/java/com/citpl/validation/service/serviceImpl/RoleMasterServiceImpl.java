package com.citpl.validation.service.serviceImpl;

import com.citpl.validation.dto.RoleMasterDto;
import com.citpl.validation.model.RoleMasterModel;
import com.citpl.validation.repository.RoleMasterRepository;
import com.citpl.validation.service.RoleMasterService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.bson.Document;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

import java.util.List;

@Service
public class RoleMasterServiceImpl implements RoleMasterService {
    @Autowired
    private RoleMasterRepository roleMasterRepository;
    private static final Logger log = LoggerFactory.getLogger(RoleMasterServiceImpl.class);

    @Override
    public Boolean getRoleMaster(String path, String method, List<String> roles) {
        try {
            // Validate if roles list is empty or null
            if (roles == null || roles.isEmpty()) {
                log.warn("Role list is empty or null.");
                return false;
            }

            // Fetch the roleMaster object from the repository
            RoleMasterModel roleMaster = roleMasterRepository.findRole(path, method);

            // Validate if roleMaster is null (to avoid NullPointerException)
            if (roleMaster == null) {
                log.warn("No role master entry found for path:{} and method:{}", path, method);
                return false;
            }

            // Debugging logs
            log.info("Path: {}", roleMaster.getRelativePath());
            log.info("Roles from request: {}", roles);
            log.info("Roles from DB: {}", roleMaster.getRoles());

            // Check if the user has the required role
            for (String userRole : roles) {
                if (roleMaster.getRoles().contains(userRole)) {
                    log.info("Access granted for role: {}", userRole);
                    return true;
                }
            }

            // If no matching role is found
            log.info("Access denied. No matching role found.");
            return false;
        } catch (ExpiredJwtException e) {
            log.error("JWT Token Expired: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized: Token Expired");

        } catch (JwtException e) {
            log.error("Invalid JWT Token: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized: Invalid Token");

        } catch (Exception e) {
            log.error("Exception in getRoleMaster: {}", e.getMessage(), e);
            return false;
        }

    }
}
