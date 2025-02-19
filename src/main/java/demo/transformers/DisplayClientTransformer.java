package demo.transformers;

import demo.dto.DisplayClientDTO;
import demo.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DisplayClientTransformer implements Transformer<Client, DisplayClientDTO> {

    @Autowired
    private DisplayCreditCardTransformer displayCreditCardTransformer;

    @Autowired
    private BookTransformer bookTransformer;

    @Override
    public DisplayClientDTO fromEntity(Client client) {

        if(client==null){
            return null;
        }

        DisplayClientDTO clientDTO = new DisplayClientDTO();

        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setAge(client.getAge());
        clientDTO.setClientAddress(client.getClientAddress());
        clientDTO.setUsername(client.getUsername());
        clientDTO.setDisplayCreditCardDTO(displayCreditCardTransformer.fromEntity(client.getCreditCard()));
        clientDTO.setBookBorrowed(bookTransformer.fromEntity(client.getBookBorrowed()));

        return clientDTO;
    }

    @Override
    public Client fromDTO(DisplayClientDTO displayClientDTO) {

        if(displayClientDTO==null){
            return null;
        }

        Client clientEntity = new Client();

        clientEntity.setId(displayClientDTO.getId());
        clientEntity.setName(displayClientDTO.getName());
        clientEntity.setAge(displayClientDTO.getAge());
        clientEntity.setClientAddress(displayClientDTO.getClientAddress());
        clientEntity.setUsername(displayClientDTO.getUsername());
        clientEntity.setCreditCard(displayCreditCardTransformer.fromDTO(displayClientDTO.getDisplayCreditCardDTO()));
        clientEntity.setBookBorrowed(bookTransformer.fromDTO(displayClientDTO.getBookBorrowed()));

        return clientEntity;
    }
}
