package com.example.praca_magisterska.repository;

import com.example.praca_magisterska.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, String> {


}