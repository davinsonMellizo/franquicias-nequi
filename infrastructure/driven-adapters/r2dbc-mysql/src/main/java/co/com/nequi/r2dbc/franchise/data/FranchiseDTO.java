package co.com.nequi.r2dbc.franchise.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table("franchise")
public class FranchiseDTO {
    @Id
    private Integer id;
    private String name;
}
