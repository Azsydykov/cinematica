package kg.mega.cinematica.exceptions;

public class SeatScheduleNotFoundException extends RuntimeException{
    public SeatScheduleNotFoundException(String message) {
        super(message);
    }
}
