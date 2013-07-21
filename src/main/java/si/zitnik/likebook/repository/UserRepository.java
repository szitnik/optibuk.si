package si.zitnik.likebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import si.zitnik.likebook.domain.User;

/**
 * Created with IntelliJ IDEA.
 * User: slavkoz
 * Date: 4/1/13
 * Time: 12:21 AM
 * To change this template use Campaign | Settings | Campaign Templates.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findById(Long id);
    User findByFbId(String fbId);
    User findByUsername(String username);
}
