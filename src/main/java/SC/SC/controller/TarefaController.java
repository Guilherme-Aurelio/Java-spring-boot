package SC.SC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import SC.SC.domain.tarefa.Tarefa;
import SC.SC.repository.TarefaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Tarefas", description = "Operações relacionadas as tarefas")
@RestController
@RequestMapping("tarefas")
public class TarefaController {
    
    @Autowired
    private TarefaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid Tarefa tarefa, UriComponentsBuilder uriBuilder){
        Tarefa tarefaLocal = repository.save(tarefa);
        var uri = uriBuilder.path("/tarefas/{id}").buildAndExpand(tarefaLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var tarefa = repository.getReferenceById(id);
        return ResponseEntity.ok(tarefa);
    }

    @GetMapping
    public ResponseEntity<Page<Tarefa>> listar(@PageableDefault(size=4, sort={"nome"}) Pageable paginacao){
        var tarefas = repository.findAll(paginacao);
        return ResponseEntity.ok(tarefas);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var tarefa = repository.getReferenceById(id);
        repository.delete(tarefa);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody @Valid Tarefa tarefa){
        Tarefa tarefaLocal = repository.findById(id).orElse(null);
        if (tarefaLocal == null) {
            return ResponseEntity.notFound().build();
        }
        tarefaLocal.setNome(tarefa.getNome());
        tarefaLocal.setDescricao(tarefa.getDescricao());
        repository.save(tarefaLocal);
        return ResponseEntity.ok(tarefaLocal);
    }
}
