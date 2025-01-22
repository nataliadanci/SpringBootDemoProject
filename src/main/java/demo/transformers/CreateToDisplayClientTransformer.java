package demo.transformers;

import demo.dto.CreateClientDTO;
import demo.dto.DisplayClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateToDisplayClientTransformer implements DTOTransformer<CreateClientDTO, DisplayClientDTO>{

    @Autowired
    private CreditCardDTOToDisplayDTOTransformer creditCardDTOToDisplayDTOTransformer;
    @Override
    public DisplayClientDTO transform(CreateClientDTO createClientDTO) {

        DisplayClientDTO displayClientDTO = new DisplayClientDTO();

        displayClientDTO.setId(createClientDTO.getId());
        displayClientDTO.setName(createClientDTO.getName());
        displayClientDTO.setAge(createClientDTO.getAge());
        displayClientDTO.setClientAddress(createClientDTO.getClientAddress());
        displayClientDTO.setUsername(createClientDTO.getUsername());
        displayClientDTO.setDisplayCreditCardDTO(creditCardDTOToDisplayDTOTransformer.transform(createClientDTO.getCreditCardDTO()));

        return displayClientDTO;
    }
}
