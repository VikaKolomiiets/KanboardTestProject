package api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Results<T> {
    private String jsonrpc;
    private T result;
    private String error;
    private int id;
}
