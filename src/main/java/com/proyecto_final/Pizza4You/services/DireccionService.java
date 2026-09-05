package com.proyecto_final.Pizza4You.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_final.Pizza4You.dto.DireccionDTO;
import com.proyecto_final.Pizza4You.model.Cliente;
import com.proyecto_final.Pizza4You.model.Direccion;
import com.proyecto_final.Pizza4You.repositorio.ClienteRepository;
import com.proyecto_final.Pizza4You.repositorio.DireccionRepository;

import jakarta.transaction.Transactional;

@Service
public class DireccionService {
	@Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private ClienteRepository clienteRepository;
    
    /**
     * Recogida de las direcciones guardadas de un usuario
     * 
     * Verificación de que el cliente sea uno activo
     * Mapeo a una lista DireccionDTO, para no devolver todos los datos del objeto Direccion
     * 
     * @param idCliente
     * @return Array de direcciones
     */
    
    public List<DireccionDTO> buscarDirecciones(Integer idCliente) {
        return direccionRepository.findByClienteAndActivoTrue(
                clienteRepository.findById(idCliente).orElseThrow())
            .stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    /**
     * Transacción para añadir nuevas direcciones 
     * 
     * Verificación del usuario
     * Automáticamente la nueva dirección se vuelve activa
     * 
     * @param Dirección a guardar
     * @param email usuario
     * @return DTO de dirección
     */
    @Transactional
    public DireccionDTO anyadirDireccion(Direccion request, String emailUsuario) {
        Cliente cliente = clienteRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Direccion direccion = new Direccion();
        direccion.setNombreVia(request.getNombreVia());
        direccion.setNumero(request.getNumero());
        direccion.setBloque(request.getBloque());
        direccion.setPuerta(request.getPuerta());
        direccion.setPlanta(request.getPlanta());
        direccion.setPortal(request.getPortal());
        direccion.setActivo(true);
        direccion.setCliente(cliente);

        Direccion guardada = direccionRepository.save(direccion);
        return mapToDTO(guardada);
    }

    private DireccionDTO mapToDTO(Direccion dir) {
        return new DireccionDTO(
            dir.getIdDir(),
            dir.getNombreVia(),
            dir.getNumero(),
            dir.getBloque(),
            dir.getPuerta(),
            dir.getPlanta(),
            dir.getPortal()
        );
    }
}