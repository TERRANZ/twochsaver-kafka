package ru.terra.twochsaver.scheduler.db.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.terra.twochsaver.shared.entity.TwochFile;

import java.util.List;

@Repository
public interface TwochFileRepo extends CrudRepository<TwochFile, String> {
    List<TwochFile> findAllByThreadId(final Integer threadId);
}
