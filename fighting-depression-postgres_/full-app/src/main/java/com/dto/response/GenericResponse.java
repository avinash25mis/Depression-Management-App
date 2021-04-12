package com.dto.response;

public class GenericResponse<T> {
	String status;
	String statusCode;
	String message;
			T result;
			public GenericResponse(String status, String message, T result) {
				super();
				this.status = status;
				this.statusCode = statusCode;
				this.message = message;
				this.result = result;
			}
			public GenericResponse() {
				super();
			}
			public String getStatus() {
				return status;
			}
			public void setStatus(String status) {
				this.status = status;
			}

	   public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
				return message;
			}
			public void setMessage(String message) {
				this.message = message;
			}
			public T getResult() {
				return result;
			}
			public void setResult(T result) {
				this.result = result;
			}
			
			
	}
