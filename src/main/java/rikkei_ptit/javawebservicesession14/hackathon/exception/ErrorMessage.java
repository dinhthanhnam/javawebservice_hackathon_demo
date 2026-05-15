package rikkei_ptit.javawebservicesession14.hackathon.exception;

import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage<T> {
    private String message;
    private HttpStatusCode statusCode;
    private long timestamp;
    private T details;
}
