package co.istad.springapi.securty;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private  final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;
//    private final PasswordEncoder encoder;
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(encoder.encode("123")) // we also can use {noop}123 to BCrypt password
//                .roles("ADMIN")
//                .build();
//        UserDetails goldUser = User.builder()
//                .username("gold")
//                .password(encoder.encode("123"))
//                .roles("ADMIN","ACCOUNT")
//                .build();
//        UserDetails user = User.builder()
//                .username("user")
//                .password(encoder.encode("123"))
//                .roles("USER")
//                .build();
//        userDetailsManager.createUser(admin);
//        userDetailsManager.createUser(goldUser);
//        userDetailsManager.createUser(user);
//        return userDetailsManager;
//    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider (){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }
    // create bean security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //TODO: 2. disable CSRF (Cross-side Request Forgery) bcoz we delete form login
        http.csrf(token -> token.disable());

        //TODO: authorize URL mapping
        http.authorizeHttpRequests(auth->{
            auth.requestMatchers("/api/v1/auth/**").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/api/v1/users/**").hasAnyRole("ADMIN", "SYSTEM");
            auth.requestMatchers(HttpMethod.POST,"/api/v1/users/**").hasAnyRole("SYSTEM");
            auth.requestMatchers("/api/v1/account-types/**").authenticated();

        });
        //TODO: 3. Security mechanism
        http.httpBasic();

        //TODO: 1. Make security http STATELESS
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

}
