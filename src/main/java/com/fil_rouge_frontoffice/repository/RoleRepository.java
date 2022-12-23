package com.fil_rouge_frontoffice.repository;

import com.fil_rouge_frontoffice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByIntitule(String intitule);

}
