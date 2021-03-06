/*
 * Copyright 2020 Gabriel Keller
 * This work is licensed under a Creative Commons
 * Attribution-NonCommercial-ShareAlike 4.0 International License.
 * A copy of this license can be found at
 * https://creativecommons.org/licenses/by-nc-sa/4.0/legalcode.
 */

package com.okgabe.mastr2.exceptions;

public class MemberSearchException extends MastrException {
    public MemberSearchException(String message){
        super(message);
    }

    public MemberSearchException(String message, Throwable cause){
        super(message, cause);
    }
}
