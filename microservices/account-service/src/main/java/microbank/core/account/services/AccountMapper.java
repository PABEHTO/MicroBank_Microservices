package microbank.core.account.services;

import microbank.core.account.Account;
import microbank.core.account.persistence.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

//import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mappings({
    })
    Account entityToApi(AccountEntity entity);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "version", ignore = true)
    })
    AccountEntity apiToEntity(Account api);

    //List<Account> entityToApi(List<AccountEntity> entity);

    //List<AccountEntity> apiToEntity(List<Account> api);
}
