package Catalogo.Inteligente.IA.service;
import Catalogo.Inteligente.IA.entity.Produto;
import Catalogo.Inteligente.IA.repository.ProdutoRepository;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final EmbeddingModel embeddingModel;

    
    public ProdutoService(ProdutoRepository repository, EmbeddingModel embeddingModel) {
        this.repository = repository;
        this.embeddingModel = embeddingModel;
    }

    public Produto cadastrarProduto(Produto produto) {
        
       float[] vetor = embeddingModel.embed(produto.getDescricao());

produto.setDescricaoVector(Arrays.toString(vetor));
        
        boolean temEstoque = produto.getQuantidadeEstoque() != null && produto.getQuantidadeEstoque() > 0;
        produto.setDisponivel(temEstoque);

        return repository.save(produto);
    }
    public List<Produto> buscarProdutosPorSimilaridade(String textoBusca, int limite) {
    
    float[] vetorBusca = embeddingModel.embed(textoBusca);
    
    
    String vetorString = Arrays.toString(vetorBusca);


    return repository.buscaSemantica(vetorString, limite);
}
}