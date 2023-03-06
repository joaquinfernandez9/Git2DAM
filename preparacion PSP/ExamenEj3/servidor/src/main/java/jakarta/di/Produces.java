package jakarta.di;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public class Produces {

    @jakarta.enterprise.inject.Produces
    public Jsonb producesJsonb()
    {
        return JsonbBuilder.create();
    }
}
