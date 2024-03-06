package com.rivera.clientreferences.service;

import com.rivera.clientreferences.converter.ClienteConverter;
import com.rivera.clientreferences.dto.ClienteDto;
import com.rivera.clientreferences.dto.ReferenciaDto;
import com.rivera.clientreferences.exception.BusinessException;
import com.rivera.clientreferences.exception.ErrorModel;
import com.rivera.clientreferences.model.Cliente;
import com.rivera.clientreferences.repository.ClientRepository;
import com.rivera.clientreferences.type.AccesibilidadCliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    private final ClienteConverter clienteConverter;
    private final ClientRepository clientRepository;

    public ClienteService(ClienteConverter clienteConverter, ClientRepository clientRepository) {
        this.clienteConverter = clienteConverter;
        this.clientRepository = clientRepository;
    }

    public ClienteDto addCliente(ClienteDto clienteDto) {
        Cliente cliente = clienteConverter.dtoToEntity(clienteDto);
        Cliente saved = clientRepository.save(cliente);
        return clienteConverter.entityToDto(saved);
    }

    public ClienteDto getCliente(Long id) {

        Optional<Cliente> clienteOptional = clientRepository.findById(id);
        if (clienteOptional.isEmpty()) {
            List<ErrorModel> errorModelList = List.of(new ErrorModel("400", "El cliente con el id proporcionado no ha sido encontrado"));
            throw new BusinessException(errorModelList);
        }
        logger.info("Saved Client = " + clienteOptional.get());
        return clienteConverter.entityToDto(clienteOptional.get());
    }

    public List<ClienteDto> listarClientesPorAccesibilidad(AccesibilidadCliente accesibilidad) {
        List<Cliente> clientes = clientRepository.findByAccesibilidad(accesibilidad);
        return clientes.stream()
                .map(cliente -> clienteConverter.entityToDto(cliente))
                .collect(Collectors.toList());
    }
}
