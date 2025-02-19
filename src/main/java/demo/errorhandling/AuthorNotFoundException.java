package demo.errorhandling;

public class AuthorNotFoundException extends Exception{
    public AuthorNotFoundException(String message){
        super(message);
    }
}
