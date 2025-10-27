package microbank.core.user.services;

import microbank.core.user.User;
import microbank.core.user.persistence.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User entityToApi(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    UserEntity apiToEntity(User user);
}
