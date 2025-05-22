package co.com.nequi.usecase.branch;

import co.com.nequi.model.branch.Branch;
import co.com.nequi.model.branch.gateways.BranchRepository;
import co.com.nequi.usecase.product.ProductUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class BranchUseCase {
    private final BranchRepository repository;
    private final ProductUseCase productUseCase;

    private Mono<Branch> getProduct(Branch branch){
        return productUseCase.getProductWithLargestStock(branch.getId())
                .map(product -> branch.toBuilder().productLargestStock(product).build());
    }

    public Mono<Branch> create(Branch branch){
        return repository.create(branch);
    }

    public Mono<Branch> update(Branch branch){
        return repository.update(branch);
    }

    public Mono<List<Branch>> findBy(Integer idFranchise){
        return repository.findBy(idFranchise)
                .flatMap(this::getProduct)
                .collectList();
    }

    public Mono<List<Branch>> findAll(Integer idFranchise){
        return repository.findBy(idFranchise)
                .collectList();
    }
}
