package co.com.nequi.r2dbc.franchise;

import co.com.nequi.model.franchise.Franchise;
import co.com.nequi.model.franchise.gateways.FranchiseRepository;
import co.com.nequi.r2dbc.franchise.data.FranchiseDTO;
import co.com.nequi.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class FranchiseRepositoryImpl extends ReactiveAdapterOperations<Franchise, FranchiseDTO,
        Integer, IFranchiseRepository> implements FranchiseRepository {

    public FranchiseRepositoryImpl(IFranchiseRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Franchise.class));
    }

    @Override
    public Mono<Franchise> create(Franchise franchise) {
        return Mono.just(franchise)
                .map(this::toData)
                .flatMap(repository::save)
                .map(this::toEntity);
    }

    @Override
    public Mono<Franchise> update(Franchise franchise) {
        return Mono.just(franchise)
                .map(this::toData)
                .flatMap(repository::save)
                .map(this::toEntity);
    }

    @Override
    public Flux<Franchise> find() {
        return repository.findAll()
                .map(this::toEntity);
    }
}
