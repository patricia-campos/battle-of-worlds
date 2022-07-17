package br.com.dbc.trabalhofinalmodulo2.repository;

import br.com.dbc.trabalhofinalmodulo2.banco.DbConfiguration;
import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.model.entities.ClassePersonagem;
import br.com.dbc.trabalhofinalmodulo2.model.entities.TipoClassePersonagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClassePersonagemRepository implements Repositorio<Integer, ClassePersonagem> {


    @Autowired
    private DbConfiguration dbConfiguration;

    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "Select SEQ_CLASSE_PERSONAGEM.nextval mysequence from dual";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }
        return null;
    }

    @Override
    public ClassePersonagem adicionar(ClassePersonagem object) throws BancoDeDadosException {
        return null;
    }

    public ClassePersonagem adicionar(ClassePersonagem objeto, int idPersonagem) throws BancoDeDadosException, SQLException {
        Connection connection = dbConfiguration.getConnection();
        try {
            int idClasse = getProximoId(connection);
            String sql = "INSERT INTO CLASSE_PERSONAGEM (ID_CLASSE_PERSONAGEM, ID_PERSONAGEM, NOME_CLASSE_PERSONAGEM, VIDA_PERSONAGEM, DEFESA_PERSONAGEM, ATAQUE_PERSONAGEM)\n" +
                    "\tVALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idClasse);
            stmt.setDouble(2, idPersonagem);
            stmt.setString(3, objeto.getTipoPersonagem().toString());
            stmt.setDouble(4, objeto.getVidaClasse());
            stmt.setDouble(5, objeto.getDefesaClasse());
            stmt.setDouble(6, objeto.getAtaqueClasse());



            int res = stmt.executeUpdate();
            objeto.setIdClassePersonagem(idClasse);
            System.out.println("Classe adicionada com sucesso");
            return objeto;

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
    public boolean remover(Integer id) throws BancoDeDadosException {
        return false;
    }

    @Override
    public ClassePersonagem editar(Integer id, ClassePersonagem object) throws BancoDeDadosException, SQLException {
        Connection connection = dbConfiguration.getConnection();

        try {

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CLASSE_PERSONAGEM SET ");
            sql.append("ID_PERSONAGEM = ?,");
            sql.append("NOME_CLASSE_PERSONAGEM = ?,");
            sql.append("VIDA_PERSONAGEM = ?,");
            sql.append("DEFESA_PERSONAGEM = ?,");
            sql.append("ATAQUE_PERSONAGEM");
            sql.append("WHERE ID_CLASSE_PERSONAGEM = ?");

            PreparedStatement stmt = connection.prepareStatement(sql.toString());

            stmt.setDouble(1, id);
            stmt.setString(2, object.getTipoPersonagem().toString());
            stmt.setDouble(3, object.getVidaClasse());
            stmt.setDouble(4, object.getDefesaClasse());
            stmt.setDouble(5, object.getAtaqueClasse());
            stmt.setDouble(6, object.getIdClassePersonagem());

            int res = stmt.executeUpdate();
            System.out.println("Editado com sucesso");
            return object;
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
    public List<ClassePersonagem> listar() throws BancoDeDadosException, SQLException {
        List<ClassePersonagem> classes = new ArrayList<>();
        Connection connection = dbConfiguration.getConnection();

        try {

            Statement stmt = connection.createStatement();

            String sql = "SELECT * FROM CLASSE_PERSONAGEM";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                ClassePersonagem classePersonagem = new ClassePersonagem();
                classePersonagem.setIdClassePersonagem(res.getInt("ID_CLASSE_PERSONAGEM"));

                String nomeClassePersonagem = res.getString("NOME_CLASSE_PERSONAGEM");
                TipoClassePersonagem tipoClassePersonagem = TipoClassePersonagem.valueOf(nomeClassePersonagem);

                classePersonagem.setTipoPersonagem(tipoClassePersonagem);

                classePersonagem.setVidaClasse(res.getDouble("VIDA_PERSONAGEM"));
                classePersonagem.setDefesaClasse(res.getDouble("DEFESA_PERSONAGEM"));
                classePersonagem.setAtaqueClasse(res.getDouble("ATAQUE_PERSONAGEM"));
                classePersonagem.setIdPersonagem(res.getInt("ID_PERSONAGEM"));
                classes.add(classePersonagem);
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
        return classes;
    }

    public ClassePersonagem listarClassePorPersonagemID(Integer id) throws BancoDeDadosException, SQLException {
        Connection connection = dbConfiguration.getConnection();
        try {

            String sql = "SELECT * FROM CLASSE_PERSONAGEM WHERE ID_PERSONAGEM = ?";

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);
            // Executa-se a consulta

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                ClassePersonagem classePersonagem = new ClassePersonagem();
                classePersonagem.setIdClassePersonagem(res.getInt("ID_CLASSE_PERSONAGEM"));
                classePersonagem.setIdPersonagem(res.getInt("ID_PERSONAGEM"));


                String nomeClassePersonagem = res.getString("NOME_CLASSE_PERSONAGEM");
                TipoClassePersonagem tipoClassePersonagem = TipoClassePersonagem.valueOf(nomeClassePersonagem);

                classePersonagem.setTipoPersonagem(tipoClassePersonagem);
                classePersonagem.setAtaqueClasse(res.getDouble("ATAQUE_PERSONAGEM"));
                classePersonagem.setVidaClasse(res.getDouble("VIDA_PERSONAGEM"));
                classePersonagem.setDefesaClasse(res.getDouble("DEFESA_PERSONAGEM"));
                return classePersonagem;
            } else return null;
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
}

