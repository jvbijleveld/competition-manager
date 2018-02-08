package nl.vanbijleveld.cm.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import nl.vanbijleveld.cm.users.UserServiceImpl;
import nl.vanbijleveld.cm.users.User;
import java.util.List;
import java.util.ArrayList; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;

@EnableWebSecurity
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        //return new UsernamePasswordAuthenticationToken("email", "pw");
        
          String username = authentication.getName(); String pw = authentication.getCredentials().toString();
          
          User user = userService.findByUsername(username);
          
          if (user == null) { 
              throw new BadCredentialsException("Bad Credentials"); 
          } 
          if (!user.isActivated()) { 
              throw new DisabledException("User Disabled"); 
          }
          
          if (!BCrypt.checkpw(pw, user.getPassword())) { throw new BadCredentialsException("Bad Credentials"); }
          
          List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>(); 
          grantedAuths.add(new SimpleGrantedAuthority(user.getRole().getRole())); 
          return new UsernamePasswordAuthenticationToken(username, pw, grantedAuths);
         
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
