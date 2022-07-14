package br.com.dbc.trabalhofinalmodulo2.repository;

import br.com.dbc.trabalhofinalmodulo2.banco.DbConfiguration;
import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.model.entities.ClassePersonagem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassePersonagemRepository implements Repositorio<Integer, ClassePersonagem> {
    Connection con = null;

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

    public ClassePersonagem adicionar(ClassePersonagem objeto, int idPersonagem) throws BancoDeDadosException {

        try {
            con = DbConfiguration.getConnection();

            String sql = "INSERT INTO CLASSE_PERSONAGEM (ID_CLASSE_PERSONAGEM, ID_PERSONAGEM, NOME_CLASSE_PERSONAGEM, VIDA_PERSONAGEM, DEFESA_PERSONAGEM, ATAQUE_PERSONAGEM)\n" +
                    "\tVALUES (SEQ_CLASSE_PERSONAGEM.nextval, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idPersonagem);
            stmt.setString(2, objeto.getNomeClassePersonagem());
            stmt.setInt(3, objeto.getVidaClasse());
            stmt.setInt(4, objeto.getDefesaClasse());
            stmt.setInt(5, objeto.getAtaqueClasse());

            int res = stmt.executeUpdate();
            System.out.println("Classe adicionada com sucesso");
            return objeto;

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
    public boolean remover(Integer id) throws BancoDeDadosException {
        return false;
    }

    @Override
    public boolean editar(Integer id, ClassePersonagem object) throws BancoDeDadosException {
        try {
            con = DbConfiguration.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CLASSE_PERSONAGEM SET ");
            sql.append("ID_PERSONAGEM = ?,");
            sql.append("NOME_CLASSE_PERSONAGEM = ?,");
            sql.append("VIDA_PERSONAGEM = ?,");
            sql.append("DEFESA_PERSONAGEM = ?,");
            sql.append("ATAQUE_PERSONAGEM");
            sql.append("WHERE ID_CLASSE_PERSONAGEM = ?");


            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setInt(1, id);
            stmt.setString(2, object.getNomeClassePersonagem());
            stmt.setInt(3, object.getVidaClasse());
            stmt.setInt(4, object.getDefesaClasse());
            stmt.setInt(5, object.getAtaqueClasse());
            stmt.setInt(6, object.getIdClassePersonagem());

            int res = stmt.executeUpdate();
            System.out.println("Editado com sucesso");
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
    public List<ClassePersonagem> listar() throws BancoDeDadosException {
        List<ClassePersonagem> classes = new ArrayList<>();
        try {
            con = DbConfiguration.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM CLASSE_PERSONAGEM";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                ClassePersonagem classePersonagem = new ClassePersonagem();
                classePersonagem.setIdClassePersonagem(res.getInt("ID_CLASSE_PERSONAGEM"));
                classePersonagem.setNomeClassePersonagem(res.getString("NOME_CLASSE_PERSONAGEM"));
                classePersonagem.setVidaClasse(res.getInt("VIDA_PERSONAGEM"));
                classePersonagem.setDefesaClasse(res.getInt("DEFESA_PERSONAGEM"));
                classePersonagem.setAtaqueClasse(res.getInt("ATAQUE_PERSONAGEM"));
                classePersonagem.setIdPersonagem(res.getInt("ID_PERSONAGEM"));
                classes.add(classePersonagem);
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
        return classes;
    }
}

