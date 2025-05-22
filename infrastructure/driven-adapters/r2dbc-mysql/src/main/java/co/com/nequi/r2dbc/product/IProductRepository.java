package co.com.nequi.r2dbc.product;

import co.com.nequi.r2dbc.product.data.ProductDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface IProductRepository extends ReactiveCrudRepository<ProductDTO, Integer> {

    @Query("""
            SELECT p.*
            FROM product p inner join branch b on p.id_branch = b.id
            where b.id = :id
            ORDER  by p.stock DESC
            LIMIT 1
            """)
    Mono<ProductDTO> getProductWithLargestStock(@Param("id") Integer idBranch);
}
