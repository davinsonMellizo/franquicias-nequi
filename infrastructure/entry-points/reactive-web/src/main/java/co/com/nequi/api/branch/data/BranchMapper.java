package co.com.nequi.api.branch.data;

import co.com.nequi.model.branch.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    @Mapping(target = "productLargestStock", ignore = true)
    Branch toEntity(BranchDTO branchDTO);
}
