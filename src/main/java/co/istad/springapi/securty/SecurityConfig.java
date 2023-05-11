//package co.istad.springapi.securty;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    //TODO: Define in-memory user
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .username("admin")
//                .password("{noop}1234")
//                .roles("USER")
//                .build();
//        UserDetails goldUser = User.builder()
//                .username("gold")
//                .password("{noop}1234")
//                .roles("GOLD")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{noop}1234")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        //TODO: Disable CSRF
//        http.csrf(token -> token.disable()).httpBasic(); // we add this cuz we delete form login
//
//        //TODO: Authorize URL mapping
//
//
//
//
//        http.authorizeHttpRequests()
//                .requestMatchers("/**")
//                .hasRole("ADMIN")
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        return http.build();
//    }
//}
