package si.zitnik.likebook.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import si.zitnik.likebook.domain.User;
import si.zitnik.likebook.repository.UserRepository;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: slavkoz
 * Date: 3/31/13
 * Time: 9:58 PM
 * To change this template use Campaign | Settings | Campaign Templates.
 */
@Controller
public class PageController {

    @Inject
    private Facebook facebook;

    @Inject
    private UserRepository userRepository;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String home(Model model) {

        String fbId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findByFbId(fbId);
        model.addAttribute("user", user);

        return "home";
    }
}
