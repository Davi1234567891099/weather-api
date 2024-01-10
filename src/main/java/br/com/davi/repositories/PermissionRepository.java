package br.com.davi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.davi.models.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID>{

	Permission findByDescription(String description);
}
