package co.com.nequi.r2dbc.branch;

import co.com.nequi.model.branch.Branch;
import co.com.nequi.model.branch.gateways.BranchRepository;
import co.com.nequi.r2dbc.branch.data.BranchDTO;
import co.com.nequi.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class BranchRepositoryImpl extends ReactiveAdapterOperations<Branch, BranchDTO, Integer, IBranchRepository>
        implements BranchRepository {

    public BranchRepositoryImpl(IBranchRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Branch.class));
    }

    @Override
    public Mono<Branch> create(Branch branch) {
        return Mono.just(branch)
                .map(this::toData)
                .flatMap(repository::save)
                .map(this::toEntity);
    }

    @Override
    public Mono<Branch> update(Branch branch) {
        return Mono.just(branch)
                .map(this::toData)
                .flatMap(repository::save)
                .map(this::toEntity);
    }

    @Override
    public Flux<Branch> findBy(Integer idFranchise) {
        System.out.println("si llega");
        return repository.findBy(idFranchise)
                .doOnNext(branchDTO -> System.out.println("no llega"))
                .map(this::toEntity);
    }
}
