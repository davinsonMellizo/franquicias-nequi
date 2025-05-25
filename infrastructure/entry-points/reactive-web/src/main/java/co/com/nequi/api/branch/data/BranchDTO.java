package co.com.nequi.api.branch.data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BranchDTO {
    @Min(1)
    private Integer id;
    @Min(1)
    @NonNull
    private Integer idFranchise;
    @NonNull
    @Size(min = 1)
    private String name;
}
