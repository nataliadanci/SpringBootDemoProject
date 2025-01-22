package demo.transformers;


import demo.dto.CreditCardDTO;
import demo.entity.CreditCard;
import org.springframework.stereotype.Component;

@Component
public class CreditCardTransformer implements Transformer<CreditCard, CreditCardDTO>{

    @Override
    public CreditCardDTO fromEntity(CreditCard creditCard) {
        if(creditCard == null){
            return null;
        }

        CreditCardDTO creditCardDTO = new CreditCardDTO();

        creditCardDTO.setId(creditCard.getId());
        creditCardDTO.setCardNumber(creditCard.getCardNumber());
        creditCardDTO.setCardType(creditCard.getCardType());
        creditCardDTO.setExpirationDate(creditCard.getExpirationDate());
        creditCardDTO.setCvvCode(creditCard.getCvvCode());

        return creditCardDTO;
    }

    @Override
    public CreditCard fromDTO(CreditCardDTO creditCardDTO) {

        if(creditCardDTO == null){
            return null;
        }

        CreditCard creditCardEntity = new CreditCard();

        creditCardEntity.setId(creditCardDTO.getId());
        creditCardEntity.setCardNumber(creditCardDTO.getCardNumber());
        creditCardEntity.setCardType(creditCardDTO.getCardType());
        creditCardEntity.setExpirationDate(creditCardDTO.getExpirationDate());
        creditCardEntity.setCvvCode(creditCardDTO.getCvvCode());

        return creditCardEntity;
    }

}
