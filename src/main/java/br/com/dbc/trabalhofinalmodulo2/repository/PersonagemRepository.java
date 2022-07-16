package br.com.dbc.trabalhofinalmodulo2.repository;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Personagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class PersonagemRepository implements Repositorio<Integer, Personagem> {

    @Autowired
    private Connection connection;

    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        connection.createStatement();
            String sql = "SELECT SEQ_PERSONAGEM.nextval proximoIdPersonagem from DUAL";
        System.out.println(connection);
        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);
        if (res.next()) {
            return res.getInt("proximoIdPersonagem");
        }
        return null;
    }

    @Override
    public Personagem adicionar(Personagem object) throws BancoDeDadosException {
        return null;
    }

    public Personagem adicionar(Personagem object, int id) throws BancoDeDadosException {
        try {
            connection.createStatement();
            int idPersonagem = getProximoId(connection);

            String sql = "INSERT INTO PERSONAGEM\n" +
                    "(ID_PERSONAGEM, ID_JOGADOR, NOME_PERSONAGEM)\n" +
                    "VALUES(?, ?, ?)\n";

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, idPersonagem);
            stmt.setInt(2, id);
            stmt.setString(3, object.getNomePersonagem());

            int res = stmt.executeUpdate();
            object.setId(idPersonagem);
            object.setIdJogador(id);
            System.out.println("Adicionado com sucesso");
            return object;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean remover(Integer id) throws BancoDeDadosException {
        try {
            connection.createStatement();
            String sql = "DELETE FROM PERSONAGEM WHERE ID_PERSONAGEM = ?";

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("Removido Personagem com sucesso");

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
    public Personagem editar(Integer id, Personagem personagem) throws BancoDeDadosException {
        try {
            connection.createStatement();
            String sql = "UPDATE PERSONAGEM SET " +
                    " NOME_PERSONAGEM = ?" +
                    " WHERE ID_PERSONAGEM = ? ";

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, personagem.getNomePersonagem());
            stmt.setInt(2, id);
            // Executa-se a consulta
            stmt.executeUpdate();
            ResultSet res = stmt.executeQuery();
            return personagem;
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

    public Optional<Personagem> listarPorNome(String nome) throws BancoDeDadosException {
        try {
            connection.createStatement();
            String sql = "SELECT * FROM JOGADOR WHERE NOME_JOGADOR = ?";

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, nome);
            // Executa-se a consulta

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                Personagem personagem = new Personagem();
                personagem.setNomePersonagem(res.getString("NOME_JOGADOR"));
                personagem.setId(res.getInt("ID_PERSONAGEM"));
                personagem.setIdJogador(res.getInt("ID_JOGADOR"));
                return Optional.of(personagem);
            } else return Optional.empty();
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


    public Personagem listarPorId(Integer id) throws BancoDeDadosException {
        try {
            connection.createStatement();
            String sql = "SELECT * FROM PERSONAGEM WHERE ID_PERSONAGEM = ?"; //TODO: verificar se o id é o mesmo do personagem

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);
            // Executa-se a consulta

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                Personagem personagem = new Personagem();
                personagem.setNomePersonagem(res.getString("NOME_PERSONAGEM"));
                personagem.setId(res.getInt("ID_PERSONAGEM"));
                personagem.setIdJogador(res.getInt("ID_JOGADOR"));
                return Objects.equals(personagem.getId(), id) ? personagem : null;
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

    public Personagem retornaPersonagemPorJogador(Integer id) throws BancoDeDadosException {
        try {
            connection.createStatement();
            String sql = "SELECT * FROM PERSONAGEM WHERE ID_JOGADOR = ?";

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);
            // Executa-se a consulta

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                Personagem personagem = new Personagem();
                personagem.setNomePersonagem(res.getString("NOME_PERSONAGEM"));
                personagem.setId(res.getInt("ID_PERSONAGEM"));
                personagem.setIdJogador(res.getInt("ID_JOGADOR"));
                return Objects.equals(personagem.getId(), id) ? personagem : null;
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
    @Override
    public List<Personagem> listar() throws BancoDeDadosException {
        List<Personagem> personagemList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();

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
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return personagemList;
    }
}
