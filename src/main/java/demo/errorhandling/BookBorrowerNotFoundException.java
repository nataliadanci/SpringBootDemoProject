package demo.errorhandling;

public class BookBorrowerNotFoundException extends Exception{
    public BookBorrowerNotFoundException(String message){
        super(message);
    }
}
