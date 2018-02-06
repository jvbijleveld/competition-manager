package nl.vanbijleveld.cm.auth;

import java.util.ArrayList;
import java.util.List;

import nl.vanbijleveld.cm.users.User;
import nl.vanbijleveld.cm.users.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class UserAuthenticationManager implements AuthenticationManager {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String pw = authentication.getCredentials().toString();

        User user = userService.findByUsername(username);

        if (user == null) {
            throw new BadCredentialsException("Bad Credentials");
        }
        if (!user.isActivated()) {
            throw new DisabledException("User Disabled");
        }

        if (!BCrypt.checkpw(pw, user.getPassword())) {
            throw new BadCredentialsException("Bad Credentials");
        }

        List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
        grantedAuths.add(new SimpleGrantedAuthority(user.getRole().getRole()));
        return new UsernamePasswordAuthenticationToken(username, pw, grantedAuths);
    }
}
