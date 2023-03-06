package domain.model;

import jakarta.inject.Singleton;
import lombok.Data;

@Data
@Singleton
public class CacheAuth {
    private String user;
    private String pass;
    private String jwt;
}
