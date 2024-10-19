package com.learner.shoppinghub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learner.shoppinghub.models.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
