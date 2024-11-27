package com.example.product_service_12052024.advices;


import com.example.product_service_12052024.dtos.ErrorDto;
import com.example.product_service_12052024.exception.CategoryNotFoundException;
import com.example.product_service_12052024.exception.ProductNotFoundException;
import com.example.product_service_12052024.exception.clientexceptions.UnauthorisedClientErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice

public class ControllerAdvice {


   @ExceptionHandler(ProductNotFoundException.class)
   public ResponseEntity<ErrorDto> handleProductNotFoundException (ProductNotFoundException productNotFoundException) {

	  ErrorDto errorDto = new ErrorDto ();
	  errorDto.setErrorMessage (productNotFoundException.getMessage ());

	  return new ResponseEntity<> (errorDto, HttpStatus.NOT_FOUND);
   }

   @ExceptionHandler
   public ErrorDto unauthorisedClientErrorExceptionHandler (UnauthorisedClientErrorException exception) {
	  ErrorDto errorDto = new ErrorDto ();
	  errorDto.setErrorMessage (exception.getMessage ());
	  return errorDto;
   }

   @ExceptionHandler
   public ResponseEntity<ErrorDto> categoryNotFoundExceptionHandler(CategoryNotFoundException e){
	  ErrorDto errorDto = new ErrorDto ();
	  errorDto.setErrorMessage (e.getMessage ());
	  return new ResponseEntity<> (errorDto, HttpStatus.NOT_FOUND);
   }
}
