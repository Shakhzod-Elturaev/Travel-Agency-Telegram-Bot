package uz.travelAgency.exceptions;

public class DuplicateDataException extends RuntimeException{
    public DuplicateDataException(String message){
        super(message);
    }
}
