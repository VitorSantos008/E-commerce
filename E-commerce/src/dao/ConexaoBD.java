package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/e_commerce";

    private static final String USUARIO =
            "postgres";

    private static final String SENHA =
            "vitim";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}