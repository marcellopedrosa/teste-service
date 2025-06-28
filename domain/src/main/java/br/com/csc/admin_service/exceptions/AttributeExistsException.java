package br.com.csc.admin_service.exceptions;

public class AttributeExistsException extends BLLException {
    public AttributeExistsException(String message) {
        super(message);
    }
}
