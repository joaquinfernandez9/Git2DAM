package modelo;

import java.util.List;

public record RespuestaAPI (boolean status, List<DataItem> data) {
}
