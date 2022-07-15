package br.com.dbc.trabalhofinalmodulo2.repository;

import br.com.dbc.trabalhofinalmodulo2.banco.DbConfiguration;
import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Cenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CenarioRepository implements Repositorio<Integer, Cenario> {
        Connection con = null;

        @Autowired
        private DbConfiguration dbConfiguration;

    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "Select SEQ_CENARIO.nextval mysequence FROM DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()){
            return res.getInt("mysequence");
        }
        return null;
    }

    @Override
    public Cenario adicionar(Cenario object) throws BancoDeDadosException {
        try {
            con = dbConfiguration.getConnection();

            String sql = "INSERT INTO CENARIO (ID_CENARIO, NOME_CENARIO, HORARIO, TIPO_REINO)\n" +
                    "\tVALUES (SEQ_CENARIO.nextval, ?, CURRENT_DATE, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, object.getNomeCenario());
            stmt.setString(2, object.getTipoCenario());

            stmt.executeUpdate();
            System.out.println("Cenário adicionado com sucesso!");

            return object;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean remover(Integer id) throws BancoDeDadosException {
        try {
            con = dbConfiguration.getConnection();

            String sql = "DELETE FROM CENARIO WHERE ID_CENARIO = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            int res = stmt.executeUpdate();
            System.out.println("Cenario removido");

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
    public Cenario editar(Integer id, Cenario cenario) throws BancoDeDadosException {
        try {
            con = dbConfiguration.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CENARIO SET ");
            sql.append(" NOME_CENARIO = ?,");
            sql.append(" HORARIO = ?,");
            sql.append(" TIPO_REINO = ?");
            sql.append("WHERE ID_CENARIO = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, cenario.getNomeCenario());
            stmt.setDate(2, (Date) cenario.getHorario());
//            stmt.set(3, object.getTipoCenario());

            int res = stmt.executeUpdate();
            System.out.println("Cenario editado com sucesso" + res);

            return cenario;
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
    public List<Cenario> listar() throws BancoDeDadosException {
        List<Cenario> cenarios = new ArrayList<>();
        try {
            con = dbConfiguration.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM CENARIO";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Cenario cenario = new Cenario();
                cenario.setIdCenario(res.getInt("ID_CENARIO"));
                cenario.setNomeCenario(res.getString("NOME_CENARIO"));
                cenario.setHorario(res.getDate("HORARIO"));
                cenario.setTipoCenario(res.getString("TIPO_REINO"));
                cenarios.add(cenario);
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
        return cenarios;
    }
}
