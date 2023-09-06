package com.basic.initializer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.basic.initializer.entity.MavenDependencies;

@Repository
public interface DependencyRepo extends JpaRepository<MavenDependencies, Integer>{

}
