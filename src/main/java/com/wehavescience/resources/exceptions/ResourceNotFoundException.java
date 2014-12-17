package com.wehavescience.resources.exceptions;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class ResourceNotFoundException extends RestfulException {
    public ResourceNotFoundException() {
        super(404, "Resource not found");
    }
}
