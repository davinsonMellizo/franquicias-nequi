package co.com.nequi.r2dbc.product.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table("product")
public class ProductDTO {
    @Id
    private Integer id;
    private String name;
    private Integer stock;
}
