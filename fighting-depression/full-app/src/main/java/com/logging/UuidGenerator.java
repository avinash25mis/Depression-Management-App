package com.logging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class UuidGenerator {


    private int uuidLength=3;

    public String generateUid(String channel, String msn) {
        String uuid = UUID.randomUUID().toString().toUpperCase();
        return channel + "_" + uuid.substring(0, uuidLength) + "_" + msn;
    }
    public String generateUid(String msn) {
        String uuid = UUID.randomUUID().toString().toUpperCase();
        return uuid.substring(0, uuidLength) + "_" + msn;
    }

    public String generateExceptionId() {
        String uuid=UUID.randomUUID().toString().toUpperCase();
        String exceptionId = "EXCEPTION-"+uuid.substring(0, 6)+new Random().nextInt(1000);
        return exceptionId;
    }


}
