package demo.transformers;

import demo.dto.DisplayClientDTO;
import demo.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DisplayClientTransformer implements Transformer<Client, DisplayClientDTO> {

    @Autowired
    private DisplayCreditCardTransformer displayCreditCardTransformer;

    @Override
    public DisplayClientDTO fromEntity(Client client) {

        DisplayClientDTO clientDTO = new DisplayClientDTO();

        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setAge(client.getAge());
        clientDTO.setClientAddress(client.getClientAddress());
        clientDTO.setUsername(client.getUsername());
        clientDTO.setDisplayCreditCardDTO(displayCreditCardTransformer.fromEntity(client.getCreditCard()));

        return clientDTO;
    }

    @Override
    public Client fromDTO(DisplayClientDTO displayClientDTO) {

        Client clientEntity = new Client();

        clientEntity.setId(displayClientDTO.getId());
        clientEntity.setName(displayClientDTO.getName());
        clientEntity.setAge(displayClientDTO.getAge());
        clientEntity.setClientAddress(displayClientDTO.getClientAddress());
        clientEntity.setUsername(displayClientDTO.getUsername());
        clientEntity.setCreditCard(displayCreditCardTransformer.fromDTO(displayClientDTO.getDisplayCreditCardDTO()));

        return clientEntity;
    }
}
