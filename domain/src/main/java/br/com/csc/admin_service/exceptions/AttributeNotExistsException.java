package br.com.csc.admin_service.exceptions;

public class AttributeNotExistsException extends BLLException {
    public AttributeNotExistsException(String message) {
        super(message);
    }
}
