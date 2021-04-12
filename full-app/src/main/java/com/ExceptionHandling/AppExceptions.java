package com.ExceptionHandling;



public class AppExceptions extends RuntimeException {

    private String errorMsg;
    private String detailMessage;

    public AppExceptions(String errorMsg) {
        super(errorMsg);
        this.errorMsg=errorMsg;
    }


    public AppExceptions(final String errorMsg, final String detailMessage) {
        super(errorMsg);
        this.errorMsg=errorMsg;
        this.detailMessage = detailMessage;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }
}


