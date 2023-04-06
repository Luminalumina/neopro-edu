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

    public ClientDTO convertAddDTOtoDTO (ClientAddDTO addDTO) {
        ClientDTO dto = new ClientDTO();
        dto.setName(addDTO.getName());
        return dto;
    }

}
