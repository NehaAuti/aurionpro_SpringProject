package com.aurionpro.mappings.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

import com.aurionpro.mappings.security.JwtAuthenticationEntryPoint;
import com.aurionpro.mappings.security.JwtAuthenticationFilter;
import com.aurionpro.mappings.security.CustomUserDetailService;


import jakarta.persistence.EntityManagerFactory;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import javax.sql.DataSource;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
@EnableJpaRepositories(basePackages = "com.aurionpro.mappings.repository")
@EntityScan(basePackages = "com.aurionpro.mappings.entity")
public class SecurityConfig {

	 @Autowired
	    private CustomUserDetailService customerUserDetailsService;

	    @Autowired
	    private JwtAuthenticationFilter jwtAuthenticationFilter;

	    @Autowired
	    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	    @Bean
	    public static PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	        return configuration.getAuthenticationManager();
	    }
	    

	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.csrf(csrf -> csrf.disable())
	            .cors(cors -> cors.disable())
	            .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
	            .authorizeHttpRequests(authz -> authz
	                .requestMatchers("/bankapp/register/admin").permitAll()
	                .requestMatchers("/bankapp/generate").permitAll()
	                .requestMatchers("/bankapp/register/customer").permitAll()
	                .requestMatchers("/bankapp/login").permitAll()
	                .requestMatchers("/bankapp/bankaccount/create").permitAll()
	                .requestMatchers("/bankapp/customerId").permitAll()
	                .requestMatchers("/bankapp/bankaccount").permitAll()
	                .requestMatchers("/bankapp/bankaccount/{bankId}").permitAll()
	                .requestMatchers("/bankapp/accountnumber/{accountNumber}").permitAll()
	                .requestMatchers("/bankapp/customer/{customerId}").permitAll()
	                .requestMatchers("/bankapp/customer").permitAll()
	                .requestMatchers("/bankapp/editCustomer").permitAll()
	                .requestMatchers("/bankapp/customers").permitAll()
	                .requestMatchers("/bankapp/customers/{customerId}").permitAll()
	                .requestMatchers("/bankapp/passbooks/{senderAccountNumber}").permitAll()
	                .requestMatchers("/bankapp/newtransaction/{customerId}").permitAll()
	                .requestMatchers("/bankapp/accountnumber").permitAll()
	                .requestMatchers("/bankapp/transactions").permitAll()
	                .requestMatchers("/bankapp/captcha").permitAll()
	                .requestMatchers("/bankapp/captcha/generate").permitAll()
	                .requestMatchers("/bankapp/captcha/validate").permitAll()
	                .requestMatchers(HttpMethod.GET, "/bankapp/**").authenticated()
	                .requestMatchers(HttpMethod.POST, "/bankapp/**").authenticated()
	                .requestMatchers(HttpMethod.PUT, "/bankapp/**").authenticated()
	                .requestMatchers(HttpMethod.DELETE, "/bankapp/**").authenticated()
	                .anyRequest().authenticated()
	            )
	            .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
	            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }
}
