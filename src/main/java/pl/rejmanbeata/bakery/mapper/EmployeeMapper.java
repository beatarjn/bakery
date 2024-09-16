package pl.rejmanbeata.bakery.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;
import pl.rejmanbeata.bakery.database.EmployeeEntity;
import pl.rejmanbeata.bakery.model.employee.Employee;

@Mapper(componentModel = "spring")
@Component
public interface EmployeeMapper {
    @Mappings({
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "role", target = "role")
    })
    Employee employeeEntityToEmployee(EmployeeEntity source);

    @Mappings({
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "role", target = "role")
    })
    EmployeeEntity employeeToEmployeeEntity(Employee source);
}
