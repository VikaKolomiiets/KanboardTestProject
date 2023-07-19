package api.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Results<T> {
    private String jsonrpc;
    private T result;
    private String error;
    private int id;
}
