package com.example.app.storage;

import com.example.app.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс хранилища всех кошельков
 *
 * @author Светлана Ибраева
 * @version 1.0
 */
public interface WalletRepository extends JpaRepository<Wallet, String> {
}
