package si.zitnik.likebook.service;

import si.zitnik.likebook.domain.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: slavkoz
 * Date: 5/10/13
 * Time: 6:48 PM
 * To change this template use Campaign | Settings | Campaign Templates.
 */
public interface UserService {
    public void save(User user);
    public User getById(Long id);
    public User getByFbId(String id);
    public User getByUsername(String username);
    public void remove(User user);
}
