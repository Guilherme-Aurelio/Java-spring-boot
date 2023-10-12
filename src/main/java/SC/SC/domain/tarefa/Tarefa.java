package SC.SC.domain.tarefa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "tarefa") //Nome usado em consultas SQL
@Entity(name = "tarefa") //Nome usado nas consultas JPQL (solução JPA)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Tarefa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //No caso de PostgreSQL, seria GenerationType.SEQUENCE
    private Long id;
    @NotBlank(message = "Nome da tarefa é obrigatório")
    private String nome;
    private String descricao;
}
