package com.proyecto_final.Pizza4You.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.proyecto_final.Pizza4You.dto.ErrorResponse;

@RestControllerAdvice
public class ErroresGlobales {

	@ExceptionHandler(ErrorNotFound.class)
    public ResponseEntity<ErrorResponse> handleRecursoNoEncontrado(ErrorNotFound ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "No Encontrado",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
	
	@ExceptionHandler(ErrorBadReq.class)
    public ResponseEntity<ErrorResponse> handleBadReq(ErrorBadReq ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Datos incorrectos",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
	
	@ExceptionHandler(ErrorStock.class)
    public ResponseEntity<ErrorResponse> handleStock(ErrorStock ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Stock insuficiente",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error Interno del Servidor",
                "ERROR FATAL!!!"
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
	

}