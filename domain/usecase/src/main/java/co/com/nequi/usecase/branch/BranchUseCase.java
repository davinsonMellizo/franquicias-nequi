package co.com.nequi.usecase.branch;

import co.com.nequi.model.branch.Branch;
import co.com.nequi.model.branch.gateways.BranchRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BranchUseCase {
    private BranchRepository repository;

    public Mono<Branch> create(Branch branch){
        return repository.create(branch);
    }

    public Mono<Branch> update(Branch branch){
        return repository.update(branch);
    }
}
