package com.rivera.clientreferences.converter;

import com.rivera.clientreferences.dto.ReferenciaDto;
import com.rivera.clientreferences.exception.BusinessException;
import com.rivera.clientreferences.exception.ErrorModel;
import com.rivera.clientreferences.model.Cliente;
import com.rivera.clientreferences.model.Persona;
import com.rivera.clientreferences.model.Referencia;
import com.rivera.clientreferences.repository.ClientRepository;
import com.rivera.clientreferences.repository.PersonRepository;
import com.rivera.clientreferences.repository.ReferenceRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ReferenciaConverter {

    private final ReferenceRepository referenceRepository;
    private final PersonRepository personRepository;

    private final ClientRepository clientRepository;

    public ReferenciaConverter(PersonRepository personRepository, ReferenceRepository referenceRepository, ClientRepository clientRepository) {
        this.referenceRepository = referenceRepository;
        this.personRepository = personRepository;
        this.clientRepository = clientRepository;
    }

    public ReferenciaDto entityToDto(Referencia referencia) {
        return new ReferenciaDto(
                referencia.getId(),
                referencia.getPersona().id(),
                referencia.getMotivo()
        );
    }

    public Referencia dtoToEntity(ReferenciaDto referenciaDto) {

//        Optional<Cliente> clienteOptional = clientRepository.findById(referenciaDto.clienteId());
//        if(clienteOptional.isEmpty()){
//            List<ErrorModel> errorModelList = List.of(new ErrorModel("400", "El cliente con el id proporcionado no ha sido encontrado"));
//            throw new BusinessException(errorModelList);
//        }

        Optional<Persona> personaOptional = personRepository.findById(referenciaDto.personaId());

        if (personaOptional.isEmpty()) {
            List<ErrorModel> errorModelList = List.of(new ErrorModel("400", "La persona con el id proporcionado no ha sido encontrada"));
            throw new BusinessException(errorModelList);
        }
//        Cliente cliente = clienteOptional.get();
        Persona persona = personaOptional.get();


        return new Referencia(
                persona,
                referenciaDto.motivo()
        );
    }
}
