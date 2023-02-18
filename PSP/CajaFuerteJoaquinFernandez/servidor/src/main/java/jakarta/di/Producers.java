package jakarta.di;

import jakarta.enterprise.inject.Produces;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.modelmapper.ModelMapper;

public class Producers {
    @Produces
    public ModelMapper producesModelMapper()
    {
        return new ModelMapper();
    }
    
    @Produces
    public Jsonb producesJsonb()
    {
        return JsonbBuilder.create();
    }



}