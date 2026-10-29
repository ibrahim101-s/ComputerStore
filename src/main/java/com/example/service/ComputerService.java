package com.example.service;

import com.example.dto.request.ComputerAddRequestDTO;
import com.example.dto.response.ComputerResponseDTO;
import com.example.entity.ComputerEntity;
import com.example.entity.UserEntity;
import com.example.exception.NotFoundException;
import com.example.repository.ComputerRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComputerService {

    private final ComputerRepository computerRepository;
    private final UserRepository userRepository;

    @Transactional
    public ComputerResponseDTO addComputer(ComputerAddRequestDTO request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("İstifadəçi tapılmadı: " + username));

        ComputerEntity entity = ComputerEntity.builder()
                .brand(request.getBrand())
                .model(request.getModel())
                .price(request.getPrice())
                .imageUrl(request.getImageUrl())
                .storage(request.getStorage())
                .os(request.getOs())
                .gpu(request.getGpu())
                .ram(request.getRam())
                .cpu(request.getCpu())
                .username(username)
                .phone(user.getPhone())
                .isNew(request.getIsNew())
                .build();

        return mapToDTO(computerRepository.save(entity));
    }

    @Transactional(readOnly = true)
    public Page<ComputerResponseDTO> getAllComputers(Pageable pageable) {
        return computerRepository.findAll(pageable).map(this::mapToDTO);
    }

    @Transactional(readOnly = true)
    public List<ComputerResponseDTO> getMyComputers() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return computerRepository.findByUsernameIgnoreCase(username).stream().map(this::mapToDTO).toList();
    }

    @Transactional(readOnly = true)
    public ComputerResponseDTO getById(Long id) {
        ComputerEntity entity = computerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Kompüter tapılmadı id: " + id));
        return mapToDTO(entity);
    }

    @Transactional
    public ComputerResponseDTO updateComputer(Long id, ComputerAddRequestDTO request) {
        ComputerEntity entity = computerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Kompüter tapılmadı id: " + id));

        validateOwnership(entity, "redaktə etməyə");

        entity.setBrand(request.getBrand());
        entity.setModel(request.getModel());
        entity.setPrice(request.getPrice());
        entity.setImageUrl(request.getImageUrl());
        entity.setStorage(request.getStorage());
        entity.setOs(request.getOs());
        entity.setGpu(request.getGpu());
        entity.setRam(request.getRam());
        entity.setCpu(request.getCpu());
        entity.setIsNew(request.getIsNew());

        return mapToDTO(computerRepository.save(entity));
    }

    @Transactional
    public void deleteComputer(Long id) {
        ComputerEntity entity = computerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Kompüter tapılmadı id: " + id));

        validateOwnership(entity, "silməyə");

        computerRepository.delete(entity);
    }

    private void validateOwnership(ComputerEntity entity, String actionName) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        if (entity.getUsername() != null && !entity.getUsername().equalsIgnoreCase(currentUsername)) {
            throw new RuntimeException("Siz yalnız öz əlavə etdiyiniz kompüteri " + actionName + " hüququna maliksiniz!");
        }
    }

    private ComputerResponseDTO mapToDTO(ComputerEntity entity) {
        ComputerResponseDTO dto = new ComputerResponseDTO();
        dto.setId(entity.getId());
        dto.setBrand(entity.getBrand());
        dto.setModel(entity.getModel());
        dto.setPrice(entity.getPrice());
        dto.setImageUrl(entity.getImageUrl());
        dto.setStorage(entity.getStorage());
        dto.setOs(entity.getOs());
        dto.setGpu(entity.getGpu());
        dto.setRam(entity.getRam());
        dto.setCpu(entity.getCpu());
        dto.setUsername(entity.getUsername());
        dto.setPhone(entity.getPhone());
        dto.setIsNew(entity.getIsNew());
        return dto;
    }
}