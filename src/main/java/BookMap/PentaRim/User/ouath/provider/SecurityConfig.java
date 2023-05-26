package BookMap.PentaRim.User.ouath.provider;



import BookMap.PentaRim.User.Auth.Filter.JwtAuthenFilter;
import BookMap.PentaRim.User.Auth.Filter.JwtAuthorFilter;
import BookMap.PentaRim.User.Repository.UserRepository;
import BookMap.PentaRim.User.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {





    @Autowired
    private CorsConfig corsConfig;

    private final AuthenticationConfiguration authenticationConfiguration;

    private final UserRepository userRepository;

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }




    @Bean
    public SecurityFilterChain filterchain(HttpSecurity http) throws Exception {
        http
                .addFilter(corsConfig.corsFilter())
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()

                .addFilter(new JwtAuthenFilter(authenticationManager(authenticationConfiguration)))
                .addFilter(new JwtAuthorFilter(authenticationManager(authenticationConfiguration), userRepository))
                .authorizeHttpRequests()
                .requestMatchers("/user/**").hasAuthority(Role.USER.name())
                .requestMatchers("/admin/**").hasAuthority(Role.ADMIN.name())
                .anyRequest().permitAll();

        return http.build();
    }




/*

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/security-login/info").authenticated()
                .requestMatchers("/security-login/admin/**").hasAuthority(Role.ADMIN.name())
                .requestMatchers("/token/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthFilter(tokenService), UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/security-login/login")
                .defaultSuccessUrl("/")
                .failureUrl("/security-login/login")
                .and()
                .logout()
                .logoutUrl("/security-login/logout")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .and()
                .oauth2Login()
                .loginPage("/security-login/login")
                .defaultSuccessUrl("/security-login")
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
        http
                .addFilterBefore(new JwtAuthFilter(tokenService), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new BasicAuthenticationEntryPoint())
                .accessDeniedHandler(new AccessDeniedHandlerImpl());

        return http.build();

    }

    */

    /*
     @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/security-login/info").authenticated()
                .requestMatchers("/security-login/admin/**").hasAuthority(Role.ADMIN.name())
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/security-login/login")
                .defaultSuccessUrl("/security-login")
                .failureUrl("/security-login/login")
                .and()
                .logout()
                .logoutUrl("/security-login/logout")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .and()
                .oauth2Login()
                .loginPage("/security-login/login")
                .defaultSuccessUrl("/security-login")
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
        http
                .exceptionHandling()
                .authenticationEntryPoint(new BasicAuthenticationEntryPoint())
                .accessDeniedHandler(new AccessDeniedHandlerImpl());

        return http.build();

     */

    }













