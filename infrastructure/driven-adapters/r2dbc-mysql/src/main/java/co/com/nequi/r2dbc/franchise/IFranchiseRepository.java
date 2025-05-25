package co.com.nequi.r2dbc.franchise;

import co.com.nequi.model.franchise.Franchise;
import co.com.nequi.r2dbc.franchise.data.FranchiseDTO;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IFranchiseRepository extends ReactiveCrudRepository<FranchiseDTO, Integer> {

}
