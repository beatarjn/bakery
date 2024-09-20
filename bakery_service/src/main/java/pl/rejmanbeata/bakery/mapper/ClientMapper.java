package pl.rejmanbeata.bakery.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;
import pl.rejmanbeata.bakery.database.ClientEntity;
import pl.rejmanbeata.bakery.model.client.Client;

@Mapper(componentModel = "spring")
@Component
public interface ClientMapper {
    @Mappings({
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "address", target = "address")
    })
    Client clientEntityToClient(ClientEntity source);

    @Mappings({
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "address", target = "address")
    })
    ClientEntity clientToClientEntity(Client source);
}
