package com.rivera.clientreferences.service;

import com.rivera.clientreferences.converter.ReferenciaConverter;
import com.rivera.clientreferences.dto.ReferenciaDto;
import com.rivera.clientreferences.exception.BusinessException;
import com.rivera.clientreferences.exception.ErrorModel;
import com.rivera.clientreferences.model.Cliente;
import com.rivera.clientreferences.model.Persona;
import com.rivera.clientreferences.model.Referencia;
import com.rivera.clientreferences.repository.ClientRepository;
import com.rivera.clientreferences.repository.PersonRepository;
import com.rivera.clientreferences.repository.ReferenceRepository;

import com.rivera.clientreferences.type.AccesibilidadCliente;
import com.rivera.clientreferences.type.EstadoCliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReferenciaService {

    private static final Logger logger = LoggerFactory.getLogger(ReferenciaService.class);

    private final ReferenciaConverter referenciaConverter;
    private final ReferenceRepository referenceRepository;
    private final ClientRepository clientRepository;

    public ReferenciaService(ReferenciaConverter referenciaConverter, ReferenceRepository referenceRepository, PersonRepository personRepository, ClientRepository clientRepository) {
        this.referenciaConverter = referenciaConverter;
        this.referenceRepository = referenceRepository;
        this.clientRepository = clientRepository;
    }

    public ReferenciaDto addReferencia(ReferenciaDto referenciaDto, Long clienteId) {

        Optional<Cliente> clienteOptional = clientRepository.findById(clienteId);
        if (clienteOptional.isEmpty()) {
            List<ErrorModel> errorModelList = List.of(new ErrorModel("400", "El cliente con el id proporcionado no ha sido encontrado"));
            throw new BusinessException(errorModelList);
        }

        Referencia referencia = referenciaConverter.dtoToEntity(referenciaDto);

        Referencia referenciaSaved = referenceRepository.save(referencia);

        Cliente cliente = clienteOptional.get();
        referenciaSaved.getClientes().add(cliente);
        cliente.getReferencias().add(referenciaSaved);

        if (!cliente.getReferencias().isEmpty()) {
            cliente.setEstado(EstadoCliente.ACTIVO);
        }

        cliente.setAccesibilidad(calcularAccesibilidad(cliente));

        clientRepository.save(cliente);
        referenceRepository.save(referenciaSaved);

        logger.info("Saved Reference = " + referenciaSaved);
        return referenciaConverter.entityToDto(referenciaSaved);
    }

    public void deleteReferenciaPersonal(Long clienteId, Long referenciaId) {
        Cliente cliente = clientRepository.findById(clienteId)
                .orElseThrow(() -> {
                    List<ErrorModel> errorModelList = List.of(new ErrorModel("400", "El cliente con el id proporcionado no ha sido encontrado"));
                    return new BusinessException(errorModelList);
                });

        Referencia referencia = referenceRepository.findById(referenciaId)
                .orElseThrow(() -> {
                    List<ErrorModel> errorModelList = List.of(new ErrorModel("400", "La referencia personal con el id proporcionado no ha sido encontrado"));
                    return new BusinessException(errorModelList);
                });

        cliente.getReferencias().remove(referencia);
        referencia.getClientes().remove(cliente);

        if (cliente.getReferencias().isEmpty()) {
            cliente.setEstado(EstadoCliente.BLOQUEADO);
        }

        cliente.setAccesibilidad(calcularAccesibilidad(cliente));

        clientRepository.save(cliente);
        referenceRepository.save(referencia);
    }

    private AccesibilidadCliente calcularAccesibilidad(Cliente cliente) {

        int totalReferencias = cliente.getReferencias().size();

        List<Long> personaIds = cliente.getReferencias().stream()
                .map(reference -> reference.getPersona().id())
                .toList();

        int referenciasTipoCliente = clientRepository.findByPersonaIds(personaIds).size();

        if ((totalReferencias >= 2 && referenciasTipoCliente >= 2) ||
            (totalReferencias >= 3 && referenciasTipoCliente >= 1)) {
            return AccesibilidadCliente.BUENA;
        }
        if ((totalReferencias >= 2 && referenciasTipoCliente == 0) ||
            (totalReferencias == 1 && referenciasTipoCliente == 1)) {
            return AccesibilidadCliente.REGULAR;
        }
        if (totalReferencias == 1 && referenciasTipoCliente == 0) {
            return AccesibilidadCliente.MALA;
        }
        if (totalReferencias == 0) {
            return AccesibilidadCliente.NULA;
        }
        return AccesibilidadCliente.NULA;
    }

}
