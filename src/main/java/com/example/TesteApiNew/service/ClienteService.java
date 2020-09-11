package com.example.TesteApiNew.service;

import com.example.TesteApiNew.model.Cliente;
import com.example.TesteApiNew.repository.ClienteRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ConfigService configService;

    public List<Cliente> consultaGeral() { return clienteRepository.findAll(); }

    public Optional<Cliente> consultaPorCodigo(String codigo) { return  clienteRepository.findById(codigo); }

    public Cliente salvar(@NotNull Cliente cliente) {

        cliente.setHistorico(configService.preencherHistorico());
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(String codigo, Cliente cliente) {
        Cliente clienteSalvo = buscaClientePeloCodigo(codigo);
        BeanUtils.copyProperties(cliente, clienteSalvo, "idCliente");
        return clienteRepository.save(clienteSalvo);
    }

    @NotNull
    private Cliente buscaClientePeloCodigo(String codigo) {
        return clienteRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public void delete(String codigo){
        clienteRepository.deleteById(codigo);
    }
}