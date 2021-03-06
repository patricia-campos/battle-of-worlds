package br.com.dbc.trabalhofinalmodulo2.repository;

import br.com.dbc.trabalhofinalmodulo2.banco.DbConfiguration;
import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.entities.Cenario;
import br.com.dbc.trabalhofinalmodulo2.entities.TipoReino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CenarioRepository implements Repositorio<Integer, Cenario> {

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
    public Cenario adicionar(Cenario object) throws BancoDeDadosException, SQLException {
        Connection connection = dbConfiguration.getConnection();
        try {

            int id = getProximoId(connection);
            String sql = "INSERT INTO CENARIO (ID_CENARIO, NOME_CENARIO, HORARIO, TIPO_REINO)\n" +
                    "\tVALUES (?, ?, CURRENT_DATE, ?)";

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);
            stmt.setString(2, object.getNomeCenario());
            stmt.setString(3, object.getTipoReino().toString());

            stmt.executeUpdate();
            System.out.println("Cenário adicionado com sucesso!");
            object.setIdCenario(id);
            return object;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean remover(Integer id) throws BancoDeDadosException, SQLException {
        Connection connection = dbConfiguration.getConnection();
        try {

            String sql = "DELETE FROM CENARIO WHERE ID_CENARIO = ?";

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);

            int res = stmt.executeUpdate();
            System.out.println("Cenario removido");

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
    public Cenario editar(Integer id, Cenario cenario) throws BancoDeDadosException, SQLException {
        Connection connection = dbConfiguration.getConnection();
        try {
            String sql = "UPDATE CENARIO SET " +
                    " NOME_CENARIO = ?," +
                    " HORARIO = CURRENT_DATE," +
                    " TIPO_REINO = ?" +
                    "WHERE ID_CENARIO = ? ";

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, cenario.getNomeCenario());
            stmt.setString(2, cenario.getTipoReino().toString());
            stmt.setInt(3, id);

            stmt.executeUpdate();
            cenario.setIdCenario(id);

            System.out.println("Cenario editado com sucesso");

            return cenario;
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
    public List<Cenario> listar() throws BancoDeDadosException, SQLException {
        List<Cenario> cenarios = new ArrayList<>();
        Connection connection = dbConfiguration.getConnection();
        try {
            Statement stmt = connection.createStatement();

            String sql = "SELECT * FROM CENARIO";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Cenario cenario = new Cenario();
                cenario.setIdCenario(res.getInt("ID_CENARIO"));
                cenario.setNomeCenario(res.getString("NOME_CENARIO"));
                cenario.setHorario(res.getDate("HORARIO"));

                String tipoReino = res.getString("TIPO_REINO"); //jogando o resultado do sql em uma variável
                TipoReino tipoReino1 = TipoReino.ofTipo(tipoReino); //convertendo conteúdo do enum trazendo a desc
                cenario.setTipoReino(tipoReino1); //set

                cenarios.add(cenario);
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
        return cenarios;
    }

    //BUSCAR CENÁRIO POR ID
    public Cenario findCenarioById(int id) throws BancoDeDadosException, SQLException {
        Connection connection = dbConfiguration.getConnection();
        try {

            String sql = "SELECT * FROM CENARIO WHERE ID_CENARIO = ?";

            connection.createStatement();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet res = stmt.executeQuery();

            Cenario cenario = new Cenario();
            while (res.next()) {
                cenario.setIdCenario(res.getInt("ID_CENARIO"));
                cenario.setNomeCenario(res.getString("NOME_CENARIO"));

                String tipoReino = res.getString("TIPO_REINO"); //jogando o resultado do sql em uma variável
                TipoReino tipoReino1 = TipoReino.ofTipo(tipoReino); //convertendo conteúdo do enum trazendo a desc
                cenario.setTipoReino(tipoReino1); //set

                cenario.setHorario(res.getDate("HORARIO"));
                return cenario;
            }

            return null;

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
