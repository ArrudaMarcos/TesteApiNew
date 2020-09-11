package com.example.TesteApiNew.resource;

import com.example.TesteApiNew.model.Cliente;
import com.example.TesteApiNew.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<?> listar() {

        List<Cliente> clientes = clienteService.consultaGeral();
        return !clientes.isEmpty() ? ResponseEntity.ok(clientes) : ResponseEntity.noContent().build();

    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPorId(@PathVariable String codigo) {

        Optional<Cliente> cliente = clienteService.consultaPorCodigo(codigo);
        return cliente.isPresent() ? ResponseEntity.ok(cliente.get()) : ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody @Validated Cliente cliente, HttpServletResponse response){

            Cliente clienteSalvo = clienteService.salvar(cliente);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                    .buildAndExpand(clienteSalvo
                            .getIdCliente()).toUri();
            return ResponseEntity.created(uri).body(clienteSalvo);
    }

    @PutMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Cliente> atualizar(@PathVariable String codigo, @RequestBody Cliente cliente){
        return ResponseEntity.ok().body(clienteService.atualizar(codigo, cliente));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable String codigo) {
        clienteService.delete(codigo);
    }

}
