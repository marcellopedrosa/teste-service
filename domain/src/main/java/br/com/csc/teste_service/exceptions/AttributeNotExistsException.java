package br.com.csc.teste_service.exceptions;

public class AttributeNotExistsException extends BLLException {
    public AttributeNotExistsException(String message) {
        super(message);
    }
}
