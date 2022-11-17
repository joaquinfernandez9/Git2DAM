package jakarta.errores;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LogError {
    private String message;
    private LocalDateTime fecha;

    public LogError(String message, LocalDateTime fecha) {
        this.message = message;
        this.fecha = fecha;
    }
}
