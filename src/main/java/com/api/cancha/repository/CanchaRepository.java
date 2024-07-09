package com.api.cancha.repository;

import com.api.cancha.domain.Cancha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CanchaRepository extends JpaRepository<Cancha, Long> {
}
