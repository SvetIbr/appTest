package com.example.app.storage;

import com.example.app.model.WalletOperation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс хранилища всех операций по кошелькам
 *
 * @author Светлана Ибраева
 * @version 1.0
 */
public interface WalletOperationRepository extends JpaRepository<WalletOperation, Long> {
}
