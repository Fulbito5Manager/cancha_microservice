package com.api.cancha.controller;

import com.api.cancha.domain.Cancha;
import com.api.cancha.dto.CanchaUpdateRequestDto;
import com.api.cancha.exception.CanchaIsNotExistException;
import com.api.cancha.service.CanchaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/canchas")
@Tag(name = "Cancha Management System", description = "Operations pertaining to team in Equipo Management System")
public class CanchaController {
    @Autowired
    private CanchaService canchaService;
    @Operation(summary = "Devuelve una lista de canchas con todas las canchas")
    @GetMapping
    public List<Cancha> getAllCanchas(){
        return canchaService.getAllCanchas();
    }
    @Operation(summary = "Devuelve una cancha con un id en particular")
    @GetMapping("/{id}")
    public ResponseEntity<Cancha> getCanchaById(
            @Parameter(description = "ID de la cancha a devolver", required = true)
            @PathVariable Long id){
        Optional<Cancha> cancha = canchaService.getCanchaById(id);
        return cancha.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Crea una cancha")
    @PostMapping
    public Cancha createCancha(
            @Parameter(description = "Objeto cancha a crear", required = true)
            @RequestBody Cancha cancha){
        return canchaService.saveCancha(cancha);
    }

    @Operation(summary = "Devuelve una cancha con un atributo actualizado")
    @PatchMapping("/{id}")
    public ResponseEntity<Cancha> actualizarCanchaById(
            @Parameter(description = "ID de la cancha a parchear", required = true)
            @PathVariable Long id,
            @Parameter(description = "Objeto Request para parchear una cancha", required = true)
            @RequestBody CanchaUpdateRequestDto canchaUpdateRequestDto) {
        Optional<Cancha> cancha = canchaService.getCanchaById(id);
        if (cancha.isPresent()) {
            Cancha canchaEncontrada = cancha.get();
            if (canchaUpdateRequestDto.getNombre() != null) {
                canchaEncontrada.setNombre(canchaUpdateRequestDto.getNombre());
            }
            if (canchaUpdateRequestDto.getUbicacion() != null) {
                canchaEncontrada.setUbicacion(canchaUpdateRequestDto.getUbicacion());
            }

            Cancha updatedCancha = canchaService.saveCancha(canchaEncontrada);
            return ResponseEntity.ok(updatedCancha);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "borra una cancha con un id en particular")
    @DeleteMapping("/{id}")
    public void deleteCanchaById(
            @Parameter(description = "ID de la cancha a borrar", required = true)
            @PathVariable Long id){
        Optional<Cancha> cancha = canchaService.getCanchaById(id);
        if(cancha.isPresent())
            canchaService.deleteCanchaById(id);
        throw new CanchaIsNotExistException("La cancha que quieres borrar no existe");
    }
}
