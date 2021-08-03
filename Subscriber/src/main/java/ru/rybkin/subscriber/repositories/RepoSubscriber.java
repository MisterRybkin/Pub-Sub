package ru.rybkin.subscriber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rybkin.subscriber.entity.Subscriber;

public interface RepoSubscriber extends JpaRepository<Subscriber, Integer> {
}
