package com.logging;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author avinash.a.mishra
 */
@Service
public class LoggingService {

    @Autowired
    private UuidGenerator uuidGenerator;

    public void info(String message, Logger... loggers) {
        try {
            for (Logger logger : loggers) {
                logger.info(message);
            }
        } catch (Exception e) {
           // CrimsonLogger.APPLICATION_LOGGER.error(e.getClass() + " " + e.getMessage());
        }
    }


    public void error(String message, Logger... loggers) {
        try {

            for (Logger logger : loggers) {
                logger.error(message);
            }
        } catch (Exception e) {
            //CrimsonLogger.APPLICATION_LOGGER.error(e.getClass() + " " + e.getMessage());
        }
    }

    public void debug(String message, Logger... loggers) {
        try {

            for (Logger logger : loggers) {
                logger.debug(message);
            }
        } catch (Exception e) {
           // CrimsonLogger.APPLICATION_LOGGER.error(e.getClass() + " " + e.getMessage());
        }
    }


    public void warn(String message, Logger... loggers) {
        try {

            for (Logger logger : loggers) {
                logger.warn(message);
            }
        } catch (Exception e) {
          //  CrimsonLogger.APPLICATION_LOGGER.error(e.getClass() + " " + e.getMessage());
        }
    }


    public String logException(Exception e, Logger... loggers) {
        e.printStackTrace();
        String exceptionId = uuidGenerator.generateExceptionId();
        StringBuilder sb = new StringBuilder();
        try {
            for (Logger logger : loggers) {
                logger.error("--------EXCEPTION OCCURED---------");
                logger.error("--------"+exceptionId+"---------");

                sb.append(getExceptionDetails(e));
                logger.error(sb.toString());
                String stackTrace = getExceptionStackTrace(e.getStackTrace());
                logger.error("---------STACK TRACE-------------");
                logger.error(stackTrace);
            }
        } catch (Exception e2) {
           // CrimsonLogger.APPLICATION_LOGGER.error(e2.getClass() + " " + e2.getMessage());
        }
        return exceptionId;
    }



    public String getExceptionDetails(Exception e) {
        StringBuilder sb=new StringBuilder(e.getClass().getName());
        sb.append(": ").append(e.getMessage());
        if (e.getCause() != null) {
            sb.append(": ");
            sb.append(e.getCause().getMessage());
            if (e.getCause().getCause() != null) {
                sb.append(": ");
                sb.append(e.getCause().getCause().getMessage());
                if(e.getCause().getCause().getCause()!=null){
                    sb.append(": ");
                    sb.append(e.getCause().getCause().getCause().getMessage());
                }
            }

        }
        return  sb.toString();
    }

    public String getExceptionStackTrace(StackTraceElement[] stactTraceArray) {
       if(stactTraceArray==null){
           return "";
       }
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stack : stactTraceArray) {
            sb = sb.append("at :" + stack.getClassName() + "." + stack.getMethodName() + "(" + stack.getFileName() + ":" + stack.getLineNumber() + ")");
            sb.append("\n");
        }
        return sb.toString();
    }
}
