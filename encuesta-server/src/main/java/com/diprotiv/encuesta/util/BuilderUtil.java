package com.diprotiv.encuesta.util;

import com.diprotiv.encuesta.constants.ApplicationConstants;

import java.util.UUID;

public class BuilderUtil {
    public static String getObjectId(final String prefix) {
        String uuid = UUID.randomUUID().toString();
        return prefix + ApplicationConstants.OBJECT_ID_DELIMETER+uuid;
    }
}

