package br.com.alldirect.dao;

import br.com.alldirect.connection.ConnectionFactory;
import br.com.alldirect.model.IngressoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IngressoDao {

    //Importações Para a conexão 
    private PreparedStatement stmt;
    private ResultSet rs;
    private Connection conecta;

    //Método construtor
    public IngressoDao() {
        this.conecta = new ConnectionFactory().getConnection();
    }

    //Método adicionar ingresso
    public void adicionarIngresso(IngressoModel ingresso) throws SQLException {
        String sql = "INSERT INTO INGRESSO(JOGO,SETOR,VALOR) VALUES (?,?,?)";
        try {
            stmt = conecta.prepareStatement(sql);
            stmt.setInt(1, ingresso.getJogoIngresso());
            stmt.setInt(2, ingresso.getSetorIngresso());
            stmt.setDouble(3, ingresso.getValorIngresso());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(" Erro ao adicionar o ingresso" + e);
        }
    }

    public int recuperaIdIngresso() {
        int idIngresso = 0;
        String sql = ("SELECT * from ingresso ORDER BY ID_ING DESC LIMIT 1");
        try {
            stmt = conecta.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                idIngresso = (rs.getInt("id_ING"));
            }
            stmt.close();
            //return idIngresso;
        } catch (SQLException e) {
            System.out.println(" Erro ao obter o id do ingresso \n" + e);
        }
        return idIngresso;
    }

    //Método remover venda
    public void removerVenda(int id) {
        String sql = "DELETE FROM VENDA WHERE ID_VENDA = ?";

        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir venda!\n" + e);
}
    }
}