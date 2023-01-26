package kg.mega.cinematica.exceptions;

public class OrderDetailNotFoundException extends RuntimeException{
    public OrderDetailNotFoundException(String message) {
        super(message);
    }
}
