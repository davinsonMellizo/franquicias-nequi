package co.com.nequi.model.franchise.gateways;

import co.com.nequi.model.franchise.Franchise;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FranchiseRepository {
    Mono<Franchise> create(Franchise franchise);
    Mono<Franchise> update(Franchise franchise);
    Flux<Franchise> find();
}
