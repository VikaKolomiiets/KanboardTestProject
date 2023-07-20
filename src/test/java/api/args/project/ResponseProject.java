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
    private String id;
    private String name;
    private String is_active;
    private String token;
    private String last_modified;
    private String is_public;
    private String is_private;
    private String default_swimlanel;
    private String show_default_swimlane;
    private String description;
    private String identifier;
    private ResponseProjectUrl url;

}
