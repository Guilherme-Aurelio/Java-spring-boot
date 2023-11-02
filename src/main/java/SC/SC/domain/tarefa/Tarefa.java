package SC.SC.domain.tarefa;

import SC.SC.domain.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

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
    private Long id;
    @NotBlank(message = "Nome da tarefa é obrigatório")
    private String nome;
    private String descricao;
    
    @Schema(description = "Esse atributo é criado automaticamente, então não é preciso coloca-lo na requisição post junto com nome e descricao")
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
