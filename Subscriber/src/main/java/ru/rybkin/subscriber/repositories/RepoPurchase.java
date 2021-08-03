package ru.rybkin.subscriber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rybkin.subscriber.entity.Purchase;

public interface RepoPurchase extends JpaRepository<Purchase, Integer> {
}
