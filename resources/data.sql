CREATE TABLE IF NOT EXISTS users (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(50) NOT NULL,
                                     lastname VARCHAR(50) NOT NULL,
                                     password VARCHAR(50) NOT NULL,
                                     email VARCHAR(100) NOT NULL
);