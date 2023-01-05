package comfort.com.ua.config;

import comfort.com.ua.services.impl.AdminDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    private final AdminDetailService adminDetailService;

    @Autowired
    public SecurityConfig( AdminDetailService adminDetailService) {

        this.adminDetailService = adminDetailService;
    }


    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated()

                .and()
                .formLogin()

                .permitAll()

                .and()
                .logout()
                .permitAll();


        return http.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        final DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(adminDetailService);
        auth.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return auth;
    }


}
