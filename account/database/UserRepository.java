package account.database;

import account.models.UserModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.sql.*;

public class UserRepository {
    static final String jdbc_url =  "jdbc:h2:file:~/service_db";
    static final String username = "sa";
    static final String password = "";
    static Connection connection;

    public static void initialize() {
        try {
            connection = DriverManager.getConnection(jdbc_url, username, password);
//            connection.createStatement().executeUpdate("DROP ALL OBJECTS");

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public int addUser(String name, String lastname, String email, String password) throws KeyAlreadyExistsException {
        try {
            email = email.toLowerCase();
            if (checkIfEmailExists(email)) {
                throw new KeyAlreadyExistsException();
            }


            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (name, lastname, email, password) values (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, new BCryptPasswordEncoder().encode(password));
            preparedStatement.executeUpdate();
            ResultSet set = preparedStatement.getGeneratedKeys();
            set.next();
            return set.getInt("id");

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return -1;
    }

    public void ChangeUserPassword(String password, String username) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(password);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET password = ? WHERE email = ?");
            preparedStatement.setString(1, encryptedPassword);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public UserModel getUserByEmail(String email) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM users WHERE email = ?");
            preparedStatement.setString(1, email.toLowerCase());
            ResultSet set = preparedStatement.executeQuery();
            while(set.next()) {
                return new UserModel(
                        set.getInt("id"),
                        set.getString("name"), set.getString("lastname"),
                        set.getString("email"), set.getString("password")
                );
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    public UserModel getUserByName(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM users WHERE name = ?");
            preparedStatement.setString(1, name);
            ResultSet set = preparedStatement.executeQuery();
            while(set.next()) {
                return new UserModel(
                        set.getInt("id"),
                        set.getString("name"), set.getString("lastname"),
                        set.getString("email"), set.getString("password")
                );
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    public boolean checkIfEmailExists(String email) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM users WHERE email = ?");
            preparedStatement.setString(1, email);
            ResultSet set = preparedStatement.executeQuery();
            return set.next();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }

    public void dropAll() {
        try {
            connection.createStatement().executeUpdate("DROP ALL OBJECTS");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
