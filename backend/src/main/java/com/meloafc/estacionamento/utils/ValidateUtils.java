package com.meloafc.estacionamento.utils;

import com.meloafc.estacionamento.exception.InvalidValueException;
import com.meloafc.estacionamento.exception.NotFoundException;

public class ValidateUtils {

    public static void checkBiggerThanZero(Long number, String message, String... msgArgs) {
        if (!isBiggerThanZero(number)) {
            throw new InvalidValueException(message, msgArgs);
        }
    }

    public static void checkMustBeNullOrZero(Long number, String message, String... msgArgs) {
        if (number != null && number > 0) {
            throw new InvalidValueException(message, msgArgs);
        }
    }

    public static void checkFound(Object object, String message, String... msgArgs) {
        if (object == null) {
            throw new NotFoundException(message, msgArgs);
        }
    }

    public static void checkNotNullOrEmpty(String str, String message, String... msgArgs) {
        checkNotNull(str, message, msgArgs);
        if (str.trim().isEmpty()) {
            throw new InvalidValueException(message, msgArgs);
        }
    }

    public static void checkNotNull(Object obj, String message, String... msgArgs) {
        if (obj == null) {
            throw new InvalidValueException(message, msgArgs);
        }
    }

    private static boolean isBiggerThanZero(Long number) {
        return number != null && number > 0;
    }

}
