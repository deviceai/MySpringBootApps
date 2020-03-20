package sweaterapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import sweaterapp.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
