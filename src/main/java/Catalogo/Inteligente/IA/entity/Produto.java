package Catalogo.Inteligente.IA.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnTransformer;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "produtos_v2")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Double preco;

    private Integer quantidadeEstoque;

    private Boolean disponivel;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @JsonIgnore 
    @Column(name = "descricao_vector", columnDefinition = "vector(768)")
    @ColumnTransformer(write = "cast(? as vector)") 
    private String descricaoVector;
}