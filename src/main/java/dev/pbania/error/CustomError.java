package dev.pbania.error;

import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomError {
    public int status;
    public String message;
}
