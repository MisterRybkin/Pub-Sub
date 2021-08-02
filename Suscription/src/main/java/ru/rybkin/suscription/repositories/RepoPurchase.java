package ru.rybkin.suscription.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rybkin.suscription.entity.Purchase;

public interface RepoPurchase extends JpaRepository<Purchase, Integer> {
}
