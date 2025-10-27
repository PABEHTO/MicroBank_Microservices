package microbank.core.transaction.services;

import microbank.core.transaction.Transaction;
import microbank.core.transaction.persistence.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mappings({
    })
    Transaction entityToApi(TransactionEntity transactionEntity);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "version", ignore = true)
    })
    TransactionEntity apiToEntity(Transaction transaction);

    List<Transaction> entityListToApiList(
            List<TransactionEntity> transactionEntities);

    List<TransactionEntity> apiListToEntityList(
            List<Transaction> transactionEntities
    );
}
