package com.ogooogoo.server.pets.businesscard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetBusinessCardRepository extends JpaRepository<PetBusinessCardEntity, Long> {
}
