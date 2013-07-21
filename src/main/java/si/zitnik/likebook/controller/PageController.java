package si.zitnik.likebook.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import si.zitnik.likebook.util.SignInUtils;

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


    @RequestMapping(value="/", method= RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("isAuthenticated", SignInUtils.isAuthenticated());

        //TODO add user to the model

        return "home";
    }
}
