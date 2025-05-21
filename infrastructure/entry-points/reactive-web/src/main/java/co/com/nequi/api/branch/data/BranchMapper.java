package co.com.nequi.api.branch.data;

import co.com.nequi.model.branch.Branch;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchMapper {
    Branch toEntity(BranchDTO branchDTO);
}
