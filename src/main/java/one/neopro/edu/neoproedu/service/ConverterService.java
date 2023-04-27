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
    private final String regex = "^[a-zA-Zа-яА-Я- 'А-ЩЬЮЯҐЄІЇа-щьюяґєії]*$";

    public ClientEntity convertToEntity(ClientDTO dto) {
        ClientEntity clientEntity = modelMapper.map(dto, ClientEntity.class);
        clientEntity.setId(dto.getId());
        return clientEntity;
    }

    public ClientDTO convertToDTO(ClientEntity clientEntity) {
        ClientDTO dto = modelMapper.map(clientEntity, ClientDTO.class);
        dto.setId(clientEntity.getId());
        return dto;
    }

    public ClientDTO convertAddDTOtoDTO(@Valid ClientAddDTO addDTO) throws MethodArgumentNotValidException, ArgumentNotValidException {
        ClientDTO dto = modelMapper.map(addDTO, ClientDTO.class);
        return dto;
    }

}
