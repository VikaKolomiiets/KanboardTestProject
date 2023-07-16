package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private String jsonrpc;
    private T result;
    private String error;
    private int id;
}
