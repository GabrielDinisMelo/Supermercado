package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class ConexaoMySQL {
    private Connection conexao;
    private static final String URL = "jdbc:mysql://localhost:3306/supermercado?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String SENHA = "Biel!67*";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    public boolean descontecar(){
        try{
            if(this.conexao.isClosed()==false){
                this.conexao.close();
            }
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
            return false;
        }
        return true;
    }

    public Statement criarStatement() {
        try{
            return this.conexao.createStatement();
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
            return null;
        }
    }

    public Connection getConnection(){
        return this.conexao;
    }

    public PreparedStatement  prepareStatement(String sql) {
        try{
            return this.conexao.prepareStatement(sql);
        }
        catch(SQLException err) {
            System.err.println(err.getMessage());
            return null;
        }
    }
}
