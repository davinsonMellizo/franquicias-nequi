package co.com.nequi.r2dbc.branch;

import co.com.nequi.r2dbc.branch.data.BranchDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IBranchRepository extends ReactiveCrudRepository<BranchDTO, Integer> {

    @Query("""
            SELECT * FROM branch b where b.id_franchise = :id
            """)
    Flux<BranchDTO> findBy(@Param("id") Integer idFranchise);
}
