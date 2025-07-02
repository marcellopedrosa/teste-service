package br.com.csc.teste_service.exceptions;

public class AttributeExistsException extends BLLException {
    public AttributeExistsException(String message) {
        super(message);
    }
}
