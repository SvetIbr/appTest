package com.example.app.storage;

import com.example.app.model.Wallet;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Интерфейс хранилища всех кошельков
 *
 * @author Светлана Ибраева
 * @version 1.0
 */
public interface WalletRepository extends JpaRepository<Wallet, String> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Wallet> findById (String id);

    @Query(nativeQuery = true,
            value = "Select w.id,w.balance " +
                    "from wallet as w " +
                    "where w.id = ?1")
    Optional<Wallet> getByIdforRead (String id);
}
