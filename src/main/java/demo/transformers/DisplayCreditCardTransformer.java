package demo.transformers;

import demo.dto.CreditCardDTO;
import demo.dto.DisplayCreditCardDTO;
import demo.entity.CreditCard;
import org.springframework.stereotype.Component;

@Component
public class DisplayCreditCardTransformer implements Transformer<CreditCard, DisplayCreditCardDTO>{

    @Override
    public DisplayCreditCardDTO fromEntity(CreditCard creditCard) {
        if(creditCard == null){
            return null;
        }
        DisplayCreditCardDTO displayCreditCardDTO = new DisplayCreditCardDTO();

        displayCreditCardDTO.setId(creditCard.getId());
        displayCreditCardDTO.setCardNumber(creditCard.getCardNumber());
        displayCreditCardDTO.setCardType(creditCard.getCardType());
        displayCreditCardDTO.setExpirationDate(creditCard.getExpirationDate());

        return displayCreditCardDTO;
    }

    @Override
    public CreditCard fromDTO(DisplayCreditCardDTO displayCreditCardDTO) {
        if(displayCreditCardDTO == null){
            return null;
        }
        CreditCard creditCardEntity = new CreditCard();

        creditCardEntity.setId(displayCreditCardDTO.getId());
        creditCardEntity.setCardNumber(displayCreditCardDTO.getCardNumber());
        creditCardEntity.setCardType(displayCreditCardDTO.getCardType());
        creditCardEntity.setExpirationDate(displayCreditCardDTO.getExpirationDate());

        return creditCardEntity;
    }
}
