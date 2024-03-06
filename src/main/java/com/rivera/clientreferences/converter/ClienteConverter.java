package com.rivera.clientreferences.converter;

import com.rivera.clientreferences.dto.ClienteDto;
import com.rivera.clientreferences.exception.BusinessException;
import com.rivera.clientreferences.exception.ErrorModel;
import com.rivera.clientreferences.model.Cliente;
import com.rivera.clientreferences.model.Persona;
import com.rivera.clientreferences.repository.PersonRepository;
import com.rivera.clientreferences.type.AccesibilidadCliente;
import com.rivera.clientreferences.type.EstadoCliente;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Component
public class ClienteConverter {

    private static final int MINIMUM_AGE = 20;

    private final PersonRepository personRepository;

    public ClienteConverter(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public ClienteDto entityToDto(Cliente cliente) {
        return new ClienteDto(
                cliente.getId(),
                cliente.getPersona().id(),
                cliente.getEmail(),
                cliente.getPhoneNumber(),
                cliente.getOcupacion()
        );
    }

    public Cliente dtoToEntity(ClienteDto clienteDto) {

        Optional<Persona> personaOptional = personRepository.findById(clienteDto.personaId());

        if (personaOptional.isEmpty()) {
            List<ErrorModel> errorModelList = List.of(new ErrorModel("400", "La persona con el id proporcionado no ha sido encontrada"));
            throw new BusinessException(errorModelList);
        }
        Persona persona = personaOptional.get();

        LocalDate currentDay = LocalDate.now();
        LocalDate minimumDate = currentDay.minusYears(MINIMUM_AGE);

        int age = Period.between(persona.fechaNacimiento(), currentDay).getYears();

        if (persona.fechaNacimiento().isAfter(minimumDate)) {
            List<ErrorModel> errorModelList = List.of(new ErrorModel("400", "La edad minima de la persona con el id proporcionado debe ser mayor a 20 años"));
            throw new BusinessException(errorModelList);
        }

        return new Cliente(
                clienteDto.id(),
                persona,
                clienteDto.email(),
                clienteDto.telefono(),
                clienteDto.ocupacion(),
                age,
                EstadoCliente.CREADO,
                AccesibilidadCliente.NULA,
                null
        );
    }
}
