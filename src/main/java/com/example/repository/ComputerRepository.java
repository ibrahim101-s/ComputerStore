package com.example.repository;

import com.example.entity.ComputerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerRepository extends JpaRepository<ComputerEntity, Long> {
    Page<ComputerEntity> findByBrandContainingIgnoreCaseOrModelContainingIgnoreCase(String brand, String model, Pageable pageable);
    List<ComputerEntity> findByUsernameIgnoreCase(String username);
}