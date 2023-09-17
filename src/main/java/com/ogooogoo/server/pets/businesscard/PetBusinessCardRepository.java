package com.ogooogoo.server.pets.businesscard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetBusinessCardRepository extends JpaRepository<PetBusinessCardEntity, Long> {

    List<PetBusinessCardEntity> findAllByUserId(Long userId);
}
