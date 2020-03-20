package sweaterapp.repos;

import org.springframework.data.repository.CrudRepository;
import sweaterapp.entities.Message;

import java.util.List;

public interface MessagesRepo extends CrudRepository<Message, Long> {
    List<Message>findByTag(String tag);
}
