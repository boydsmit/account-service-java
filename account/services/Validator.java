package account.services;

import jakarta.validation.ValidationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

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

    public void validatePassword(String password) throws ValidationException {
        if (password.length() < 12) {
            throw new ValidationException("Password length must be 12 chars minimum!");
        }
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(this.getClass().getResourceAsStream("/breachedPasswords.txt"))
        )) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();

            List<String> breachedPasses = Arrays.asList(everything.split(","));
            if (breachedPasses.contains(password)) {
                throw new ValidationException("The password is in the hacker's database!");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
