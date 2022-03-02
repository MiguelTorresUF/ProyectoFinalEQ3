package com.example.demo.exception;

import com.example.demo.dto.ErrorDTO;
import com.example.demo.dto.ErrorsListDTO;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vanessa Rocha
 */
@ControllerAdvice
public class AgenciaRestControllerException {
/** Metodo handler para cuando el formato de la fecha es incorrecto*/
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDTO> badDateFormat()
    {
        ErrorDTO error =new ErrorDTO();
        error.setError("ERROR");
        error.setDescription("El formato de alguna fecha, es incorrecto.");
        return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> duplicateKey()
    {
        ErrorDTO error =new ErrorDTO();
        error.setError("ERROR");
        error.setDescription("Se esta duplicando un campo único");
        return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
    }

/** Metodo handler para cuando el formato de la fecha es incorrecto*/
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorDTO> notValueNumber()
    {
        ErrorDTO error =new ErrorDTO();
        error.setError("ERROR");
        error.setDescription("El formato es incorrecto dd/MM/yyyy.");
        return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
    }

/** Metodo handler para cuando el argumento no es valido*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErrorsListDTO> handlerValidationExcepcions(MethodArgumentNotValidException ex){
        List<String> errorList = new ArrayList<>();
        ex.getFieldErrors().forEach(error -> errorList.add(error.getDefaultMessage()));

        ErrorsListDTO errorDTO = new ErrorsListDTO(errorList);
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CashInvalidException.class)
    private ResponseEntity<ErrorDTO> handlerCashInvalidExcepcions(CashInvalidException ex){
        ErrorDTO error =new ErrorDTO();
        error.setError("ERROR");
        error.setDescription(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InterestNotValidException.class)
    private ResponseEntity<ErrorDTO> handlerInterestNotValidExcepcions(InterestNotValidException ex){
        ErrorDTO error =new ErrorDTO();
        error.setError("ERROR");
        error.setDescription(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PaymentMethodException.class)
    private ResponseEntity<ErrorDTO> handlerPaymentMethodExcepcions(PaymentMethodException ex){
        ErrorDTO error =new ErrorDTO();
        error.setError("ERROR");
        error.setDescription(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ListEmptyException.class)
    private ResponseEntity<ErrorDTO> handlerNoFilterExcepcions(ListEmptyException ex){
        ErrorDTO error =new ErrorDTO();
        error.setError("ERROR");
        error.setDescription(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotDestinationException.class)
    private ResponseEntity<ErrorDTO> handlerNotDestinationExcepcions(NotDestinationException ex){
        ErrorDTO error =new ErrorDTO();
        error.setError("ERROR");
        error.setDescription(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadTypeRoomException.class)
    private ResponseEntity<ErrorDTO> handlerBadRoomTypeExcepcions(BadTypeRoomException ex){
        ErrorDTO error =new ErrorDTO();
        error.setError("ERROR");
        error.setDescription(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadDateException.class)
    private ResponseEntity<ErrorDTO> handlerBadDateExcepcions(BadDateException ex){
        ErrorDTO error =new ErrorDTO();
        error.setError("ERROR");
        error.setDescription(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotOriginException.class)
    private ResponseEntity<ErrorDTO> handleNotOriginExcepcions(NotOriginException ex){
        ErrorDTO error =new ErrorDTO();
        error.setError("ERROR");
        error.setDescription(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicatedColumnsException.class)
    private ResponseEntity<ErrorDTO> handlerDuplicatedColumnsExcepcions(DuplicatedColumnsException ex){
        ErrorDTO error =new ErrorDTO();
        error.setError("ERROR");
        error.setDescription(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
