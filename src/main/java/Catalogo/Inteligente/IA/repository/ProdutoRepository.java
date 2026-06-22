package Catalogo.Inteligente.IA.repository;

import Catalogo.Inteligente.IA.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

   
    @Query(value = "SELECT * FROM produtos_v2 ORDER BY descricao_vector <=> cast(:vetorString as vector) LIMIT :limite", nativeQuery = true)
    List<Produto> buscaSemantica(@Param("vetorString") String vetorString, @Param("limite") int limite);
}