package comfort.com.ua.security;

import comfort.com.ua.services.impl.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserDetailService userDeatilsService;

    @Autowired
    public SecurityConfig(UserDetailService userDeatilsService) {
        this.userDeatilsService = userDeatilsService;
    }


    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filter(HttpSecurity http) throws Exception
    {
        return http
                .authorizeHttpRequests()
                .requestMatchers("/admin", "/admin/*").hasAuthority("ADMIN")
                .requestMatchers("/*","/**").permitAll()
                .and().formLogin()
                .and()
                .build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        final DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDeatilsService);
        auth.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return auth;
    }
}
