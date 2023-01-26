package kg.mega.cinematica.exceptions;

public class CinemaNotFoundException extends RuntimeException{
    public CinemaNotFoundException(String message) {
        super(message);
    }
}
