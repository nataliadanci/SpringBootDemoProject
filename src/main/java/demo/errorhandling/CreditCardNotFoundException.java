package demo.errorhandling;

public class CreditCardNotFoundException extends Exception{
    public CreditCardNotFoundException(String message){
        super(message);
    }
}
