package ru.terra.twochsaver.scheduler.db.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.terra.twochsaver.scheduler.db.entity.TwochThread;
import ru.terra.twochsaver.shared.ThreadState;

import java.util.List;

@Repository
public interface TwochThreadRepo extends CrudRepository<TwochThread, Integer> {
    List<TwochThread> findAllByThreadState(final ThreadState threadState);
}
