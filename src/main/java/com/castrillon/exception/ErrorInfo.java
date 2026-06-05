package com.castrillon.exception;

import org.springframework.http.HttpStatus;

public record ErrorInfo(
String errorMessage,
String source,
Integer status

		) {

}
