package com.basic.initializer.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.basic.initializer.entity.ProjectMetadata;

@Repository
public interface MetaDataRepo extends JpaRepository<ProjectMetadata, UUID> {

}
