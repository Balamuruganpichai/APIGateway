package com.citpl.validation.repository;

import com.citpl.validation.model.RoleMasterModel;
//import com.citpl.validation.dto.RoleMasterDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.bson.Document;

import java.util.List;
import java.util.Optional;

public interface RoleMasterRepository extends MongoRepository<RoleMasterModel, String> {
    @Query(value = "{'relativePath': ?0, 'method': ?1}")
    RoleMasterModel findRole(String relativePath, String method);
}
