package br.com.dbc.trabalhofinalmodulo2.repository;

import br.com.dbc.trabalhofinalmodulo2.banco.DbConfiguration;
import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.exceptions.BossNaoEncontradoException;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Boss;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BossRepository implements Repositorio<Integer, Boss> {

    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_BOSS.nextval proximoIdBoss from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("proximoIdBoss");
        }

        return null;

    }

    @Override
    public Boss adicionar(Boss object) throws BancoDeDadosException {

        Connection con = null;

        try {
            con = DbConfiguration.getConnection();
            int id = getProximoId(con);

            String sql = "INSERT INTO BOSS (ID_BOSS, NOME_BOSS, VIDA_BOSS, DEFESA_BOSS, ATAQUE_BOSS)\n" +
                    "VALUES(?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.setString(2, object.getNome());
            stmt.setInt(3, object.getVida());
            stmt.setInt(4, object.getDefesa());
            stmt.setInt(5, object.getAtaque());

            stmt.executeUpdate();
            object.setIdBoss(id);

            return object;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new BancoDeDadosException(e.getCause());

        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean remover(Integer idBoss) throws BancoDeDadosException {

        Connection con = null;

        try {
            con = DbConfiguration.getConnection();

            String sql = "DELETE FROM BOSS WHERE ID_BOSS = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, idBoss);

            int res = stmt.executeUpdate();

            System.out.println("Boss removido com sucesso!");

            return res > 0;

        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());

        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Boss editar(Integer id, Boss boss) throws BancoDeDadosException {

        Connection con = null;

        try {
            con = DbConfiguration.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE BOSS SET ");
            sql.append("NOME_BOSS = ?,");
            sql.append("VIDA_BOSS = ?,");
            sql.append("DEFESA_BOSS = ? ");
            sql.append("ATAQUE_BOSS = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, boss.getNome());
            stmt.setInt(2, boss.getVida());
            stmt.setInt(3, boss.getDefesa());
            stmt.setInt(4, boss.getAtaque());

            int res = stmt.executeUpdate();

            return boss;

        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());

        } finally {

            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Boss> listar() throws BancoDeDadosException {

        List<Boss> bossList = new ArrayList<>();

        Connection con = null;

        try {
            con = DbConfiguration.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM BOSS";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Boss boss = new Boss();
                boss.setIdBoss(res.getInt("ID_BOSS"));
                boss.setNome(res.getString("NOME_BOSS"));
                boss.setVida(res.getInt("VIDA_BOSS"));
                boss.setDefesa(res.getInt("DEFESA_BOSS"));
                boss.setAtaque(res.getInt("ATAQUE_BOSS"));
                bossList.add(boss);
            }

        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());

        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bossList;
    }

    public Boss buscarBoss(int id) throws BossNaoEncontradoException, BancoDeDadosException {
        Connection con = null;
        try {
            con = DbConfiguration.getConnection();

            String sql = "SELECT * FROM BOSS WHERE ID_BOSS = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet res = stmt.executeQuery();

            Boss boss = new Boss();
            while (res.next()) {
                boss.setIdBoss(res.getInt("ID_BOSS"));
                boss.setNome(res.getString("NOME_BOSS"));
                boss.setVida(res.getInt("VIDA_BOSS"));
                boss.setDefesa(res.getInt("DEFESA_BOSS"));
                boss.setAtaque(res.getInt("ATAQUE_BOSS"));
                return boss;
            }

            return boss;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BancoDeDadosException(e.getCause());

        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
