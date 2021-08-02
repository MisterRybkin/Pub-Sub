package ru.rybkin.suscription.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rybkin.suscription.entity.Subscription;

public interface RepoSubscription extends JpaRepository<Subscription, Integer> {
}
