package one.neopro.edu.neoproedu.service;

import jakarta.validation.Valid;
import one.neopro.edu.neoproedu.DTO.ClientAddDTO;
import one.neopro.edu.neoproedu.DTO.ClientDTO;
import one.neopro.edu.neoproedu.DTO.ErrorDTO;
import one.neopro.edu.neoproedu.exception.ArgumentNotValidException;
import one.neopro.edu.neoproedu.model.ClientEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
public class ConverterService {
    @Autowired
    private ModelMapper modelMapper;
    private ErrorDTO errorDTO;
    private final String  regex = "^[a-zA-Zа-яА-Я- 'А-ЩЬЮЯҐЄІЇа-щьюяґєії]*$";

    public ClientEntity convertToEntity(ClientDTO dto) {
        ClientEntity clientEntity = modelMapper.map(dto, ClientEntity.class);
        clientEntity.setId(dto.getId());

//        ClientEntity clientEntity = new ClientEntity();
//        clientEntity.setId(dto.getId());
//        clientEntity.setName(dto.getName());
        return clientEntity;
    }

    public ClientDTO convertToDTO(ClientEntity clientEntity) {
        ClientDTO dto = modelMapper.map(clientEntity, ClientDTO.class);
        dto.setId(clientEntity.getId());

//        ClientDTO dto = new ClientDTO();
//        dto.setId(clientEntity.getId());
//        dto.setName(clientEntity.getName());
        return dto;
    }

    public ClientDTO convertAddDTOtoDTO (@Valid ClientAddDTO addDTO) throws MethodArgumentNotValidException, ArgumentNotValidException{
        ClientDTO dto = new ClientDTO();
//        try {
//            if (!addDTO.getName().matches(regex))
//                throw new ArgumentNotValidException();
//
//        } catch (ArgumentNotValidException e) {
//            errorDTO.setSystemErrorMessage(e.getMessage());
//        }
        dto.setName(addDTO.getName());
        return dto;
    }

}
