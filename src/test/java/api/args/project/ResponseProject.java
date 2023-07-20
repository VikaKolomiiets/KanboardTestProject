package api.args.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProject {
    private Integer id;
    private String name;
    private Integer is_active;
    private String token;
    private Integer last_modified;
    private Integer is_public;
    private Integer is_private;
    private String description;
    private String identifier;
    private String start_date;
    private String end_date;
    private String owner_id;
    private Integer priority_default;
    private Integer priority_start;
    private Integer priority_end;
    private String email;
    private String predefined_email_subjects;
    private Integer per_swimlane_task_limits;
    private Integer task_limit;
    private Integer enable_global_tags;
    private ResponseProjectUrl url;

//    private String default_swimlanel;
//    private String show_default_swimlane;

}
