package com.rivera.clientreferences.controller;

import com.rivera.clientreferences.dto.ClienteDto;
import com.rivera.clientreferences.dto.ReferenciaDto;
import com.rivera.clientreferences.model.Cliente;
import com.rivera.clientreferences.service.ClienteService;
import com.rivera.clientreferences.service.ReferenciaService;
import com.rivera.clientreferences.type.AccesibilidadCliente;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {


    private final ClienteService clienteService;
    private final ReferenciaService referenciaService;

    public ClienteController(ClienteService clienteService, ReferenciaService referenciaService) {
        this.clienteService = clienteService;
        this.referenciaService = referenciaService;
    }

    @PostMapping("/")
    public ResponseEntity<ClienteDto> addCliente(@Valid @RequestBody ClienteDto clienteDto) {
        ClienteDto nuevoCliente = clienteService.addCliente(clienteDto);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteDto> getCliente(@PathVariable Long clienteId) {
        return new ResponseEntity<>(clienteService.getCliente(clienteId), HttpStatus.OK);
    }

    @DeleteMapping("/{clienteId}/referencias/{referenciaId}")
    public ResponseEntity<Cliente> eliminarReferenciaPersonal(
            @PathVariable Long clienteId,
            @PathVariable Long referenciaId) {
        referenciaService.deleteReferenciaPersonal(clienteId, referenciaId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{clienteId}/referencias")
    public ResponseEntity<ReferenciaDto> agregarReferenciaPersonal(
            @PathVariable Long clienteId,
            @RequestBody ReferenciaDto referenciaDto) {
        ReferenciaDto added = referenciaService.addReferencia(referenciaDto, clienteId);
        return new ResponseEntity<>(added, HttpStatus.CREATED);
    }

    @GetMapping("/accesibilidad/{accesibilidad}")
    public ResponseEntity<List<ClienteDto>> listarClientesPorAccesibilidad(@PathVariable AccesibilidadCliente accesibilidad) {
        List<ClienteDto> clientes = clienteService.listarClientesPorAccesibilidad(accesibilidad);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
}