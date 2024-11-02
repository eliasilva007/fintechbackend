package br.com.fiap.fintech.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private String url;
    private String user;
    private String password;

    public ConnectionFactory(String dbType) {
        switch (dbType.toLowerCase()) {
            case "mysql":
                this.url = "jdbc:mysql://localhost:3306/db_fintech";
                this.user = "your_mysql_user";
                this.password = "your_mysql_password";
                break;
            case "oracle":
                this.url = "jdbc:oracle:thin:@localhost:1521:db_fintech";
                this.user = "your_oracle_user";
                this.password = "your_oracle_password";
                break;
            default:
                throw new IllegalArgumentException("Tipo de banco de dados não suportado: " + dbType);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}

