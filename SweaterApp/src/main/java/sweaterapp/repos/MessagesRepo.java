package sweaterapp.repos;

import org.springframework.data.repository.CrudRepository;
import sweaterapp.entities.Message;

public interface MessagesRepo extends CrudRepository<Message, Long> {

}
