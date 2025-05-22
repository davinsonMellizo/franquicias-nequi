package co.com.nequi.r2dbc.product;

import co.com.nequi.r2dbc.product.data.ProductDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface IProductRepository extends ReactiveCrudRepository<ProductDTO, Integer> {

    @Query("""
            SELECT p.*
            FROM product p inner join branch b on p.id_branch = b.id
            where b.id = $1
            ORDER  by p.stock DESC
            LIMIT 1
            """)
    Mono<ProductDTO> getProductWithLargestStock(Integer idBranch);
}
