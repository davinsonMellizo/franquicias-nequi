package co.com.nequi.api.product.data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductDTO {
    @Min(0)
    private Integer id;
    @NonNull
    @Size(min = 1)
    private String name;
    @NonNull
    @Min(0)
    private Integer stock;
}
