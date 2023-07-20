package api.args.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProject {
    private Integer project_id;
    private String name;
    private String description;
    private Integer owner_id;
    private String identifier;
    private String start_date;
    private String end_date;

}
