package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BodyArgs<T> {
    @Builder.Default
    private String jsonrpc = "2.0";
    @Builder.Default
    private int id = 987834805;
    public String method;
    public T params;

}
