package com.royal.repo;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.royal.entity.Role;

/**
 *
 *@author Isaachome
 */
public interface RoleRepo extends JpaRepository<Role, Long>{

	Optional<Role> findByName(String role);
}
