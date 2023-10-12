
package BookMap.PentaRim.User.ouath.provider;



import BookMap.PentaRim.User.Auth.Filter.JwtAuthenFilter;
import BookMap.PentaRim.User.Auth.Filter.JwtAuthorFilter;
import BookMap.PentaRim.User.CustomUserDetailsService;
import BookMap.PentaRim.User.Repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.session.config.SessionRepositoryCustomizer;
import org.springframework.session.jdbc.JdbcIndexedSessionRepository;
import org.springframework.session.jdbc.OracleJdbcIndexedSessionRepositoryCustomizer;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Getter

public class SecurityConfig {


    @Autowired
    private CorsConfig corsConfig;

    private CustomUserDetailsService customUserDetailsService;

    private final AuthenticationConfiguration authenticationConfiguration;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain filterchain(HttpSecurity http) throws Exception {
        http
                .addFilter(corsConfig.corsFilter())
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                .formLogin().disable()
                .httpBasic().disable()


                .authorizeHttpRequests()
                .anyRequest().permitAll();

        return http.build();
    }



}

/*


    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Autowired
    @Qualifier("customUserDetailsService")
    public void setUserDetailsService(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }




    @Bean
    public SecurityFilterChain filterchain(HttpSecurity http) throws Exception {
       return http
               .authorizeHttpRequests()
               .requestMatchers("/login").permitAll()
               .anyRequest().authenticated()
               .and()
               .formLogin()
               .loginPage("/login")
               .permitAll()
               .defaultSuccessUrl("/main")
               .permitAll()
               .and()
               .logout()
               .logoutSuccessUrl("/login")
               .invalidateHttpSession(true)
               .and()
               .csrf().disable()
               .build();
    }



    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new
                DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        return daoAuthenticationProvider;
    }
}







 */





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

        .addFilter(new JwtAuthenFilter(authenticationManager(authenticationConfiguration)))
                .addFilter(new JwtAuthorFilter(authenticationManager(authenticationConfiguration), userRepository))







          @Bean
    public SecurityFilterChain filterchain(HttpSecurity http) throws Exception {
        http
                .addFilter(corsConfig.corsFilter())
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()


                .authorizeHttpRequests()
                .requestMatchers("/user/**").hasAuthority("ROLE_USER")
                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().permitAll();

        return http.build();
    }

     */
















