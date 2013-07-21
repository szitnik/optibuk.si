package si.zitnik.likebook.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import si.zitnik.likebook.domain.User;
import si.zitnik.likebook.repository.UserRepository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: slavkoz
 * Date: 5/11/13
 * Time: 10:35 AM
 * To change this template use Campaign | Settings | Campaign Templates.
 */
@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private UserRepository userRepository;


    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getByFbId(String fbId) {
        return userRepository.findByFbId(fbId);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void remove(User user) {
        entityManager.remove(user);
    }
}
