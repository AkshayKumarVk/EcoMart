package com.example.product_service_12052024.exception.clientexceptions;

public class UnauthorisedClientErrorException extends Exception{
   public UnauthorisedClientErrorException (String message) {
	  super(message);
   }
}
