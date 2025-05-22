package co.com.nequi.model.branch;

import co.com.nequi.model.product.Product;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Branch {
    private Integer id;
    private Integer idFranchise;
    private String name;
    private Product productLargestStock;
}
