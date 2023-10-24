package com.project.portnet_be.structure.exception;

import java.util.function.Supplier;

public class LoginException extends RuntimeException{

    private final String ERR_CODE;

    public LoginException(String errCode) {
        ERR_CODE = errCode;
    }

}
