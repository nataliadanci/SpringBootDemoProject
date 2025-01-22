package demo.transformers;

import demo.dto.CreditCardDTO;
import demo.dto.DisplayCreditCardDTO;
import org.springframework.stereotype.Component;

@Component
public class CreditCardDTOToDisplayDTOTransformer implements DTOTransformer<CreditCardDTO, DisplayCreditCardDTO>{

    @Override
    public DisplayCreditCardDTO transform(CreditCardDTO creditCardDTO) {

        if(creditCardDTO == null){
            return null;
        }

        DisplayCreditCardDTO displayCreditCardDTO = new DisplayCreditCardDTO();

        displayCreditCardDTO.setId(creditCardDTO.getId());
        displayCreditCardDTO.setCardNumber(creditCardDTO.getCardNumber());
        displayCreditCardDTO.setExpirationDate(creditCardDTO.getExpirationDate());
        displayCreditCardDTO.setCardType(creditCardDTO.getCardType());

        return displayCreditCardDTO;
    }

}
