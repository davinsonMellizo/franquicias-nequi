package co.com.nequi.r2dbc.branch.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table("branch")
public class BranchDTO {
    @Id
    private Integer id;
    private Integer idFranchise;
    private String name;
}
