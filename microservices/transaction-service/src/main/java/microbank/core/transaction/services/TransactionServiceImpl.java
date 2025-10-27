package microbank.core.transaction.services;

import microbank.core.transaction.Transaction;
import microbank.core.transaction.TransactionService;
import microbank.core.transaction.persistence.TransactionEntity;
import microbank.core.transaction.persistence.TransactionRepository;
import microbank.exceptions.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public List<Transaction> getTransactions(int userId) {
        if (userId < 1) {
            throw new InvalidInputException(
                    "Invalid userId: "
                    + userId
            );
        }

        List<TransactionEntity> entityList = transactionRepository
                .findByUserId(userId);
        List<Transaction> list = transactionMapper
                .entityListToApiList(entityList);

        return list;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        try {
            TransactionEntity entity = transactionMapper.apiToEntity(transaction);
            TransactionEntity newEntity = transactionRepository.save(entity);
            return transactionMapper.entityToApi(newEntity);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidInputException(
                    "Duplicate key, "
                    + transaction.getUserId()
                    + " "
                    + transaction.getTransactionId()
            );
        }
    }

    @Override
    public void deleteTransactions(int userId) {
        transactionRepository.deleteAll(transactionRepository.findByUserId(userId));
    }
}
