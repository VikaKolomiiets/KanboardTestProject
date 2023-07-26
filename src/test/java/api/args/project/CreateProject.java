package api.args.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProject {
    private String name;
    private String description;
    private Integer owner_id;
    private String identifier;
    private String start_date;  //ISO8601 format (string, optional)
    private String end_date;    //ISO8601 format (string, optional)
}
