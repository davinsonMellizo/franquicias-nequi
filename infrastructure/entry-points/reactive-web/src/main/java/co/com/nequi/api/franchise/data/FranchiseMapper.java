package co.com.nequi.api.franchise.data;

import co.com.nequi.model.franchise.Franchise;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FranchiseMapper {
    Franchise toEntity(FranchiseDTO franchiseDTO);
}
