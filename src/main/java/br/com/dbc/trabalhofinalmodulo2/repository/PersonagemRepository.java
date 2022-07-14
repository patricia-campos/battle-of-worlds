package br.com.dbc.trabalhofinalmodulo2.repository;

import br.com.dbc.trabalhofinalmodulo2.banco.DbConfiguration;
import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Personagem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonagemRepository implements Repositorio<Integer, Personagem> {

    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        return null;
    }

    @Override
    public Personagem adicionar(Personagem object) throws BancoDeDadosException {
        return null;
    }

    public Personagem adicionar(Personagem object, int id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = DbConfiguration.getConnection();

            String sql = "INSERT INTO PERSONAGEM\n" +
                    "(ID_PERSONAGEM, ID_JOGADOR, NOME_PERSONAGEM)\n" +
                    "VALUES(SEQ_PERSONAGEM.nextval, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, object.getNomePersonagem());

            int res = stmt.executeUpdate();
            System.out.println("Adicionado com sucesso");
            return object;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean remover(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = DbConfiguration.getConnection();

            String sql = "DELETE FROM PERSONAGEM WHERE ID_PERSONAGEM = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("Removido Personagem com sucesso");

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
    public boolean editar(Integer id, Personagem personagem) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = DbConfiguration.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PERSONAGEM SET ");
            sql.append(" NOME_PERSONAGEM = ?");
            sql.append(" WHERE ID_PERSONAGEM = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, personagem.getNomePersonagem());
            stmt.setInt(2, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("Personagem Editado com Sucesso");

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
    public List<Personagem> listar() throws BancoDeDadosException {
        List<Personagem> personagemList = new ArrayList<>();
        Connection con = null;
        try {
            con = DbConfiguration.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM PERSONAGEM";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Personagem personagem = new Personagem();
                personagem.setNomePersonagem(res.getString("NOME_PERSONAGEM"));
                personagem.setId(res.getInt("ID_PERSONAGEM"));
                personagem.setIdJogador(res.getInt("ID_JOGADOR"));
                personagemList.add(personagem);
            }
        } catch (SQLException e) {
            throw new BancoDeDadosException("Erro ao listar Jogador");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return personagemList;
    }
}
