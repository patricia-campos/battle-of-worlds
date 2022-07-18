package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.exceptions.NaoEncontradoException;
import br.com.dbc.trabalhofinalmodulo2.mapper.BossMapper;
import br.com.dbc.trabalhofinalmodulo2.dto.BossCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.dto.BossDTO;
import br.com.dbc.trabalhofinalmodulo2.entities.Boss;
import br.com.dbc.trabalhofinalmodulo2.repository.BossRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BossService {

    @Autowired
    BossRepository bossRepository;

    @Autowired
    BossMapper bossMapper;

    //Lista e exibe os Bosses cadastrados - READ
    public List<BossDTO> listar() throws BancoDeDadosException, SQLException {
        return bossRepository.listar().stream()
                .map(a -> bossMapper.toBossDTO(a))
                .collect(Collectors.toList());
    }

    public BossDTO adicionar(BossCreateDTO boss) throws BancoDeDadosException, SQLException {
        return bossMapper.toBossDTO(bossRepository.adicionar(bossMapper.toCreateFromBoss(boss)));
    }

    public void remover(int id) throws BancoDeDadosException, Exception {
        buscarBoss(id);
        bossRepository.remover(id);
    }

    public BossDTO editar(BossCreateDTO boss, int id) throws BancoDeDadosException, Exception {
        buscarBoss(id);
        return bossMapper.toBossDTO(bossRepository.editar(id, bossMapper.toCreateFromBoss(boss)));
    }

    public Boss buscarBoss(int id) throws BancoDeDadosException, Exception {
        Boss boss = bossRepository.buscarBoss(id);
        if (boss == null) {
            throw new NaoEncontradoException("Boss não encontrado");
        }
        return boss;
    }
}
