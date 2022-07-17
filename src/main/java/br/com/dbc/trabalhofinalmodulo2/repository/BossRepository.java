package br.com.dbc.trabalhofinalmodulo2.repository;

import br.com.dbc.trabalhofinalmodulo2.banco.DbConfiguration;
import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.exceptions.BossNaoEncontradoException;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Boss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BossRepository implements Repositorio<Integer, Boss> {


    @Autowired
    private DbConfiguration dbConfiguration;

    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        String sql = "SELECT SEQ_BOSS.nextval proximoIdBoss from DUAL";

        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("proximoIdBoss");
        }
        return null;
    }

    @Override
    public Boss adicionar(Boss object) throws BancoDeDadosException, SQLException {
        Connection connection = dbConfiguration.getConnection();
        try {
            int id = getProximoId(connection);

            String sql = "INSERT INTO BOSS (ID_BOSS, NOME_BOSS, VIDA_BOSS, DEFESA_BOSS, ATAQUE_BOSS)\n" +
                    "VALUES(?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.setString(2, object.getNome());
            stmt.setDouble(3, object.getVida());
            stmt.setDouble(4, object.getDefesa());
            stmt.setDouble(5, object.getAtaque());

            stmt.executeUpdate();
            object.setIdBoss(id);

            return object;

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
    public boolean remover(Integer idBoss) throws BancoDeDadosException, SQLException {
        Connection connection = dbConfiguration.getConnection();
        try {

            String sql = "DELETE FROM BOSS WHERE ID_BOSS = ?";

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, idBoss);

            int res = stmt.executeUpdate();

            System.out.println("Boss removido com sucesso!");

            return res > 0;

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
    }

    @Override
    public Boss editar(Integer id, Boss boss) throws BancoDeDadosException, SQLException {
        Connection connection = dbConfiguration.getConnection();
        try {
            String sql = """
                    UPDATE BOSS
                    SET NOME_BOSS = ?, VIDA_BOSS = ?, DEFESA_BOSS = ?, ATAQUE_BOSS = ?
                    WHERE ID_BOSS = ?""";

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, boss.getNome());
            stmt.setDouble(2, boss.getVida());
            stmt.setDouble(3, boss.getDefesa());
            stmt.setDouble(4, boss.getAtaque());
            stmt.setDouble(5, id);

            stmt.executeUpdate();
            boss.setIdBoss(id);
            return boss;

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
    public List<Boss> listar() throws BancoDeDadosException, SQLException {

        List<Boss> bossList = new ArrayList<>();

        Connection connection = dbConfiguration.getConnection();

        try {

            Statement stmt = connection.createStatement();

            String sql = "SELECT * FROM BOSS";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Boss boss = new Boss();
                boss.setIdBoss(res.getInt("ID_BOSS"));
                boss.setNome(res.getString("NOME_BOSS"));
                boss.setVida(res.getDouble("VIDA_BOSS"));
                boss.setDefesa(res.getDouble("DEFESA_BOSS"));
                boss.setAtaque(res.getDouble("ATAQUE_BOSS"));
                bossList.add(boss);
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
        return bossList;
    }

    public Boss buscarBoss(int id) throws BossNaoEncontradoException, BancoDeDadosException, SQLException {
        Connection connection = dbConfiguration.getConnection();
        try {
            String sql = "SELECT * FROM BOSS WHERE ID_BOSS = ?";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet res = stmt.executeQuery();

            Boss boss = new Boss();
            while (res.next()) {
                boss.setIdBoss(res.getInt("ID_BOSS"));
                boss.setNome(res.getString("NOME_BOSS"));
                boss.setVida(res.getDouble("VIDA_BOSS"));
                boss.setDefesa(res.getDouble("DEFESA_BOSS"));
                boss.setAtaque(res.getDouble("ATAQUE_BOSS"));
                return boss;
            }

            return boss;
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
