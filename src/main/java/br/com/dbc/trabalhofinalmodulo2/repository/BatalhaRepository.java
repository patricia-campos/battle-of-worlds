package br.com.dbc.trabalhofinalmodulo2.repository;

import br.com.dbc.trabalhofinalmodulo2.banco.DbConfiguration;
import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Batalha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BatalhaRepository implements Repositorio<Integer, Batalha> {

    @Autowired
    private Connection connection;
    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "Select SEQ_BATALHA.nextval mysequence FROM DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()){
            return res.getInt("mysequence");
        }
        return null;
    }

    //Adicionando uma batalhaController - CREATE
    @Override
    public Batalha adicionar (Batalha object) throws BancoDeDadosException {

        try {

            Integer proximoId = this.getProximoId(connection);

            String sql = """
                         INSERT INTO BATALHA
                         (ID_BATALHA, ID_CENARIO, ID_JOGADOR, ID_BOSS, ROUND_BATALHA, STATUS)
                         VALUES(SEQ_BATALHA.nextval, ?, ?, ?, ?, ?)
                        """;

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, object.getIdCenario());
            stmt.setInt(2, object.getIdJogador());
            stmt.setInt(3, object.getIdBoss());
            stmt.setInt(4, object.getRoundBatalha());
            stmt.setString(5, object.getStatus());


            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean remover(Integer id) throws BancoDeDadosException {
        return false;
    }

    @Override
    public Batalha editar(Integer id, Batalha batalha) throws BancoDeDadosException {
        return batalha;
    }

    @Override
    public List<Batalha> listar() throws BancoDeDadosException {
        List<Batalha> batalhas = new ArrayList<>();

        try {
            Statement  stmt = connection.createStatement();

            String sql = "SELECT * FROM BATALHA";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Batalha batalhaController = new Batalha();
                batalhaController.setIdBatalha(res.getInt("ID_BATALHA"));
                batalhaController.setIdCenario(res.getInt("ID_CENARIO"));
                batalhaController.setIdJogador(res.getInt("ID_JOGADOR"));
                batalhaController.setIdBoss(res.getInt("ID_BOSS"));
                batalhaController.setRoundBatalha(res.getInt("ROUND_BATALHA"));
                batalhaController.setStatus(res.getString("STATUS"));
            }

        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return batalhas;
    }

    public Batalha buscarBatalha(int id) throws BancoDeDadosException {
        Connection con = null;
        try {

            String sql = "SELECT * FROM BATALHA WHERE ID_BATALHA = ?";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet res = stmt.executeQuery();

            Batalha batalha = new Batalha();
            while (res.next()) {
                batalha.setIdBatalha(res.getInt("ID_BATALHA"));
                batalha.setIdBoss(res.getInt("ID_BOSS"));
                batalha.setIdCenario(res.getInt("ID_CENARIO"));
                batalha.setIdJogador(res.getInt("ID_JOGADOR"));
                batalha.setRoundBatalha(res.getInt("ROUND_BATALHA"));
                batalha.setStatus(res.getString("STATUS"));
                return batalha;
            }

            return batalha;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BancoDeDadosException(e.getCause());

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}






