/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package si.zitnik.likebook.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import si.zitnik.likebook.domain.User;
import si.zitnik.likebook.repository.UserRepository;

import javax.inject.Inject;

@Controller
public class SignupController {
    @Inject
    private UserRepository userRepository;

    @Inject
    private Facebook facebook;

    @Transactional
	@RequestMapping(value="/signup", method= RequestMethod.GET)
	public String signupForm(WebRequest request) {
		Connection<Facebook> connection = (Connection<Facebook>) ProviderSignInUtils.getConnection(request);

        String fbId = connection.createData().getProviderUserId();

        User user = userRepository.findByFbId(fbId);
        if (user == null) {
            //do signup
            //Programmatically signs in the user with the given the user ID.
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(fbId, null, null));
            ProviderSignInUtils.handlePostSignUp(fbId, request);
            return "redirect:/filldata";
        } else {
            //do signin
            //Programmatically signs in the user with the given the user ID.
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(fbId, null, null));
        }

        return "redirect:/";
	}



}
