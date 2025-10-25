package microbank.core.account.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends
        CrudRepository<AccountEntity, String> {
    List<AccountEntity> findByUserId(int userId);
}
