package fr.mns.accountservice.controller;

import fr.mns.accountservice.application.exceptions.AccountNotFoundException;
import fr.mns.accountservice.application.exceptions.InvalidAmountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import tools.jackson.databind.exc.InvalidFormatException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Classe de formattage des réponses API en cas d'erreur.
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", ex.getStatusCode().value());
        body.put("error", ex.getReason());

        return new ResponseEntity<>(body, ex.getStatusCode());
    }

    /**
     * Interception des erreurs d'arguments invalides levées par le Domaine
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Requête invalide");
        body.put("message", ex.getMessage()); // Récupère "Le montant du retrait doit être strictement supérieur à zéro."

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Gère le compte inexistant
     */
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleAccountNotFound(AccountNotFoundException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", 404);
        body.put("error", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    /**
     * Gère un montant incorrect pour une transaction
     */
    @ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidAmount(InvalidAmountException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", 400);
        body.put("error", ex.getMessage());

        return ResponseEntity.badRequest().body(body);
    }

    /**
     *Gère les erreurs de formattage du JSON
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Format JSON invalide");

        String message = "Le corps de la requête est mal formé";

        if (ex.getCause() instanceof InvalidFormatException invalidFormatException) {
            String fieldName = invalidFormatException.getPath().isEmpty() ? "inconnu" : invalidFormatException.getPath().get(0).getPropertyName();
            String targetType = invalidFormatException.getTargetType().getSimpleName();
            String valueFournie = invalidFormatException.getValue().toString();

            message = String.format("Le champ '%s' doit être de type %s (valeur fournie : '%s')",
                    fieldName, targetType, valueFournie);
        }
        body.put("message", message);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Gère les erreurs internes
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllUncaughtExceptions(Exception ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Une erreur imprévue est survenue lors du traitement");
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
