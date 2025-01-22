package demo.transformers;

import demo.dto.CreateClientDTO;
import demo.dto.DisplayClientDTO;
import demo.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateClientTransformer implements Transformer<Client, CreateClientDTO>{

    @Autowired
    private CreditCardTransformer creditCardTransformer;

    @Override
    public CreateClientDTO fromEntity(Client client) {
        CreateClientDTO clientDTO = new CreateClientDTO();

        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setAge(client.getAge());
        clientDTO.setClientAddress(client.getClientAddress());
        clientDTO.setUsername(client.getUsername());
        clientDTO.setPassword(client.getPassword());
        clientDTO.setCreditCardDTO(creditCardTransformer.fromEntity(client.getCreditCard()));

        return clientDTO;
    }

    @Override
    public Client fromDTO(CreateClientDTO createClientDTO) {
        Client clientEntity = new Client();

        clientEntity.setId(createClientDTO.getId());
        clientEntity.setName(createClientDTO.getName());
        clientEntity.setAge(createClientDTO.getAge());
        clientEntity.setClientAddress(createClientDTO.getClientAddress());
        clientEntity.setUsername(createClientDTO.getUsername());
        clientEntity.setPassword(createClientDTO.getPassword());
        clientEntity.setCreditCard(creditCardTransformer.fromDTO(createClientDTO.getCreditCardDTO()));

        return clientEntity;
    }
}
