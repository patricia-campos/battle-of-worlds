package br.com.dbc.trabalhofinalmodulo2.repository;

import br.com.dbc.trabalhofinalmodulo2.banco.DbConfiguration;
import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Jogador;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class JogadorRepository implements Repositorio<Integer, Jogador> {
    Connection con = null;

    @Override
    public Integer getProximoId(Connection connection) {
        return null;
    }

    @Override
    public Jogador adicionar(Jogador object) throws BancoDeDadosException {
        try {
            con = DbConfiguration.getConnection();

            String sql = """
                    INSERT INTO JOGADOR
                    (ID_JOGADOR, NOME_JOGADOR, SENHA, EMAIL)
                    VALUES(SEQ_JOGADOR.nextval, ?, ?, ?)""";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, object.getNomeJogador());
            stmt.setString(2, object.getSenha());
            stmt.setString(3, object.getEmail());

            stmt.executeUpdate();
            ResultSet rs = con.prepareStatement("SELECT SEQ_JOGADOR.currval FROM dual").executeQuery();
            if (rs.next()) {
               object.setId(rs.getInt(1));
            }
            System.out.println("Jogador adicionado com sucesso");

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
    public boolean remover(Integer id) throws BancoDeDadosException {
        try {
            con = DbConfiguration.getConnection();

            String sql = "DELETE FROM JOGADOR WHERE ID_JOGADOR = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("Jogador removido com sucesso");

            return res > 0;
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
    public Jogador editar(Integer id, Jogador jogador) throws BancoDeDadosException {
        try {
            con = DbConfiguration.getConnection();

            String sql = """
                    UPDATE JOGADOR
                    SET NOME_JOGADOR = ?, SENHA = ?, EMAIL = ?
                    WHERE ID_JOGADOR = ?""";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, jogador.getNomeJogador());
            stmt.setString(2, jogador.getSenha());
            stmt.setString(3, jogador.getEmail());
            stmt.setInt(4, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("Jogador editado com sucesso!");

            return jogador;
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
    public List<Jogador> listar() throws BancoDeDadosException {
        List<Jogador> jogadorList = new ArrayList<>();
        try {
            con = DbConfiguration.getConnection();
            Statement stmt = con.createStatement();


            String sql = "SELECT * FROM JOGADOR";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Jogador jogador = new Jogador();
                jogador.setNomeJogador(res.getString("NOME_JOGADOR"));
                jogador.setSenha(res.getString("SENHA"));
                jogador.setEmail(res.getString("EMAIL"));
                jogador.setId(res.getInt("ID_JOGADOR"));
                jogadorList.add(jogador);
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
        return jogadorList;
    }

    public Optional<Jogador> listarPorNome(String nome) throws BancoDeDadosException {
        try {
            con = DbConfiguration.getConnection();

            String sql = "SELECT * FROM JOGADOR WHERE NOME_JOGADOR = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, nome);
            // Executa-se a consulta

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                Jogador jogador = new Jogador();
                jogador.setNomeJogador(res.getString("NOME_JOGADOR"));
                jogador.setSenha(res.getString("SENHA"));
                jogador.setEmail(res.getString("EMAIL"));
                jogador.setId(res.getInt("ID_JOGADOR"));
                return Optional.of(jogador);
            } else return Optional.empty();
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


    public Jogador listarPorId(Integer id) throws BancoDeDadosException {
        try {
            con = DbConfiguration.getConnection();

            String sql = "SELECT * FROM JOGADOR WHERE ID_JOGADOR = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);
            // Executa-se a consulta

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                Jogador jogador = new Jogador();
                jogador.setNomeJogador(res.getString("NOME_JOGADOR"));
                jogador.setSenha(res.getString("SENHA"));
                jogador.setEmail(res.getString("EMAIL"));
                jogador.setId(res.getInt("ID_JOGADOR"));
                return Objects.equals(jogador.getId(), id) ? jogador : null;
            } else return null;
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
}

