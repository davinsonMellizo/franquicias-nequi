package co.com.nequi.usecase.franchise;

import co.com.nequi.model.franchise.Franchise;
import co.com.nequi.model.franchise.gateways.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class FranchiseUseCase {
    private final FranchiseRepository repository;

    public Mono<Franchise> create(Franchise franchise){
        return repository.create(franchise);
    }

    public Mono<Franchise> update(Franchise franchise){
        return repository.update(franchise);
    }

    public Mono<List<Franchise>> find(){
        return repository.find()
                .collectList();
    }
}
