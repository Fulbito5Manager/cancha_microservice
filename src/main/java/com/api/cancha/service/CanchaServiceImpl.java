package com.api.cancha.service;

import com.api.cancha.domain.Cancha;
import com.api.cancha.repository.CanchaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CanchaServiceImpl implements CanchaService{
    @Autowired
    private CanchaRepository canchaRepository;
    @Override
    public List<Cancha> getAllCanchas() { return canchaRepository.findAll(); }

    @Override
    public Optional<Cancha> getCanchaById(Long id) { return canchaRepository.findById(id); }

    @Override
    public Cancha saveCancha(Cancha cancha) { return canchaRepository.save(cancha);}

    @Override
    public void deleteCanchaById(Long id) {
        canchaRepository.deleteById(id);
    }
}
