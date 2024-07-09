package com.api.cancha.service;

import com.api.cancha.domain.Cancha;

import java.util.List;
import java.util.Optional;


public interface CanchaService {
    List<Cancha> getAllCanchas();
    Optional<Cancha> getCanchaById(Long id);

    Cancha saveCancha(Cancha cancha);
}
