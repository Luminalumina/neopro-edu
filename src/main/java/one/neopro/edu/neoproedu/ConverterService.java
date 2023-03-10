package one.neopro.edu.neoproedu;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConverterService {
    @Autowired
    private ModelMapper modelMapper;

    public ClientEntity convertToEntity(ClientDTO dto) {
        ClientEntity clientEntity = modelMapper.map(dto, ClientEntity.class);
//        clientEntity.setId(dto.getId());
        return clientEntity;
    }

    public ClientDTO convertToDTO(ClientEntity clientEntity) {
        ClientDTO dto = modelMapper.map(clientEntity, ClientDTO.class);
        dto.setId(clientEntity.getId());
        return dto;
    }


}
