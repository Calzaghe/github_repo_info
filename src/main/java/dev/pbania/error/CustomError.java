package dev.pbania.error;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.*;

@ToString
@Getter
@Setter
public class CustomError {
    public int status;
    public String message;

    public CustomError(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
