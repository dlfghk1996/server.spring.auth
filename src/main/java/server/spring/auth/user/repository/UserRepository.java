package server.spring.auth.user.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import server.spring.auth.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String UserId);

}
