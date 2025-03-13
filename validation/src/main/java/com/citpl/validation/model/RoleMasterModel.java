package com.citpl.validation.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@Document(collection = "role_master")
public class RoleMasterModel {
    private String id;
    private String method;
    private String relativePath;
    private String permission;
    private List<String> roles;

    public void setId(String id) {
        this.id = id;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public String getMethod() {
        return method;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public String getPermission() {
        return permission;
    }

    public List<String> getRoles() {
        return roles;
    }


}
