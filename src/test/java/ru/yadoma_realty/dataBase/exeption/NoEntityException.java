package ru.yadoma_realty.dataBase.exeption;
import java.text.MessageFormat;
import java.util.function.Supplier;

public class NoEntityException extends RuntimeException {

    public NoEntityException(String message) {
        super(message);
    }

    public NoEntityException(String message, Object... args) {
        super(MessageFormat.format(message, args));
    }

    public static Supplier<NoEntityException> noEntityException(String message, Object... args) {
        return () -> new NoEntityException(message, args);
    }

    public static Supplier<NoEntityException> noEntityException(String message) {
        return () -> new NoEntityException(message);
    }

}
