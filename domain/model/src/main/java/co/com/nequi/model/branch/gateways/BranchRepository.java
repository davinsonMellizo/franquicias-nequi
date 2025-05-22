package co.com.nequi.model.branch.gateways;

import co.com.nequi.model.branch.Branch;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BranchRepository {
    Mono<Branch> create(Branch branch);
    Mono<Branch> update(Branch branch);
    Flux<Branch> findBy(Integer idFranchise);
}
