package co.com.nequi.api.franchise.data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class FranchiseDTO {
    @Min(1)
    private Integer id;
    @NonNull
    @Size(min = 1)
    private String name;
}
