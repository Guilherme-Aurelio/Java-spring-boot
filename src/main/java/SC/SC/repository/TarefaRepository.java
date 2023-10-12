package SC.SC.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import SC.SC.domain.tarefa.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
    
}
