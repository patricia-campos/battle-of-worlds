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
    private DbConfiguration dbConfiguration;

    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        String sql = "Select SEQ_BATALHA.nextval mysequence FROM DUAL";

        ResultSet res = stmt.executeQuery(sql);

        if (res.next()){
            return res.getInt("mysequence");
        }
        return null;
    }

    //Adicionando uma batalhaController - CREATE
    @Override
    public Batalha adicionar (Batalha object) throws BancoDeDadosException, SQLException {
        Connection connection = dbConfiguration.getConnection();
        try {

            Integer proximoId = this.getProximoId(connection);

            String sql = """
                         INSERT INTO BATALHA
                         (ID_BATALHA, ID_CENARIO, ID_JOGADOR, ID_BOSS, ROUND_BATALHA, STATUS)
                         VALUES(?, ?, ?, ?, ?, ?)
                        """;

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, proximoId);
            stmt.setInt(2, object.getIdCenario());
            stmt.setInt(3, object.getIdJogador());
            stmt.setInt(4, object.getIdBoss());
            stmt.setInt(5, object.getRoundBatalha());
            stmt.setString(6, object.getStatus());

            object.setIdBatalha(proximoId);
            stmt.executeUpdate();
            return object;
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
    public Batalha editar(Integer id, Batalha batalha) throws BancoDeDadosException, SQLException {
        Connection connection = dbConfiguration.getConnection();
        try {
            String sql = """
                    UPDATE BATALHA
                    SET ID_CENARIO = ?, ID_JOGADOR = ?, ID_BOSS = ?, ROUND_BATALHA = ?, STATUS = ?
                    WHERE ID_BATALHA = ?""";

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, batalha.getIdCenario());
            stmt.setInt(2, batalha.getIdJogador());
            stmt.setInt(3, batalha.getIdBoss());
            stmt.setInt(4, batalha.getRoundBatalha());
            stmt.setString(5, batalha.getStatus());
            stmt.setInt(6, batalha.getIdBatalha());


            stmt.executeUpdate();
            batalha.setIdBatalha(id);
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

    @Override
    public List<Batalha> listar() throws BancoDeDadosException, SQLException {
        List<Batalha> batalhas = new ArrayList<>();
        Connection connection = dbConfiguration.getConnection();
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

    public Batalha buscarBatalha(int id) throws BancoDeDadosException, SQLException {
        Connection connection = dbConfiguration.getConnection();
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






