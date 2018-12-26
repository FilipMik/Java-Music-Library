package cz.muni.fi.pa165.music_library.springsecurity;

import cz.muni.fi.pa165.music_library.data.entities.User;
import cz.muni.fi.pa165.music_library.data.entities.UserLevel;
import cz.muni.fi.pa165.music_library.dto.UserAuthenticateDto;
import cz.muni.fi.pa165.music_library.dto.UserDto;
import cz.muni.fi.pa165.music_library.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author Filip Mik on 23. 12. 2018
 */

@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    UserFacade userFacade;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String mail = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        UserAuthenticateDto userAuthenticateDto = new UserAuthenticateDto();
        userAuthenticateDto.setEmail(mail);
        userAuthenticateDto.setPassword(pwd);

        if (userFacade.authenticate(userAuthenticateDto)) {
            UserDto user = userFacade.findUserByEmail(mail);
            if (userFacade.isAdmin(user)) {
                return new UsernamePasswordAuthenticationToken(
                        mail, pwd, Collections.singletonList(new SimpleGrantedAuthority(UserLevel.Admin.getLevel())));

            } else {
                return new UsernamePasswordAuthenticationToken(
                        mail, pwd, Collections.singletonList(new SimpleGrantedAuthority(UserLevel.BasicUser.getLevel())));
            }
        } else {
            throw new BadCredentialsException("Wrong username or password");
        }

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
