package account.services;

import jakarta.validation.ValidationException;

import java.lang.reflect.Field;

public class Validator {
    public void validateJson(Object object) throws ValidationException {
        try {
            Class<?> objectClass = object.getClass();
            Field[] fields = objectClass.getDeclaredFields();
            for (Field field : fields) {

                field.setAccessible(true);
                Object value = field.get(object);
                if (value == null || (((String) value).isEmpty())) {
                    throw new ValidationException("Json was the wrong format.");
                }
            }
        } catch (IllegalAccessException exception) {
            System.out.println(exception.getMessage());
        }

    }

    public void validateEmail(String email) throws ValidationException {
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (!email.matches(regex) || !email.contains("@acme.com")) {
            throw new ValidationException("The  email format was wrong given email was: " + email);
        }
    }
}
