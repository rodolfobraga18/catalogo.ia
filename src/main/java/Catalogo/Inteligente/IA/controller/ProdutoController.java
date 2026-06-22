package Catalogo.Inteligente.IA.controller;
import java.util.List;
import Catalogo.Inteligente.IA.entity.Produto;
import Catalogo.Inteligente.IA.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto) {
        Produto produtoSalvo = service.cadastrarProduto(produto);
        
        return ResponseEntity.ok(produtoSalvo);
    }
    
    @GetMapping("/buscar")
public ResponseEntity<List<Produto>> buscar(
        @RequestParam String query,
        @RequestParam(defaultValue = "5") int limite) {
    
    List<Produto> resultados = service.buscarProdutosPorSimilaridade(query, limite);
    return ResponseEntity.ok(resultados);
}
}