package com.juan.app_inventario.config;

import com.juan.app_inventario.config.filters.JwtTokenValidator;
import com.juan.app_inventario.services.UserDetailsServiceImpl;
import com.juan.app_inventario.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationProvider authenticationProvider) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {

                    // EndPoints publicos
                    http.requestMatchers(HttpMethod.POST, "/auth/**").permitAll();

                    // EndPoints Privados
                    
                    //GETS
                    http.requestMatchers(HttpMethod.GET, "/api/ordenes-compra/estado/{id}").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.GET, "/api/ordenes-compra/getAll").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.GET, "/api/ordenes-compra/buscarPorFecha").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.GET, "/api/ordenes-compra/buscar").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.GET, "/api/ordenes-compra/getById/{id}").hasAuthority("READ");

                    http.requestMatchers(HttpMethod.GET, "/api/productos/getById/getAll").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.GET, "/api/productos/getById/get/{id}").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.GET, "/api/productos/getById/categoria/{id}").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.GET, "/api/productos/getById/rango-precio").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.GET, "/api/productos/getById/disponibles").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.GET, "/api/productos/getById/buscarPorNombre").hasAuthority("READ");

                    http.requestMatchers(HttpMethod.GET, "/api/proveedores/getAll").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.GET, "/api/proveedores/resumen").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.GET, "/api/proveedores/buscar").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.GET, "/api/proveedores/getById/{id}").hasAuthority("READ");

                    //POST
                    http.requestMatchers(HttpMethod.POST, "/api/ordenes-compra/create").hasAuthority("CREATE");

                    http.requestMatchers(HttpMethod.POST, "/api/productos/registrarMov").hasAuthority("CREATE");
                    http.requestMatchers(HttpMethod.POST, "/api/productos/movimientos").hasAuthority("CREATE");
                    http.requestMatchers(HttpMethod.POST, "/api/productos/crear").hasAuthority("CREATE");

                    http.requestMatchers(HttpMethod.POST, "/api/proveedores/create").hasAuthority("CREATE");

                    
                    //DELETE
                    http.requestMatchers(HttpMethod.DELETE, "/api/ordenes-compra/delete/{id}").hasAuthority("DELETE");

                    http.requestMatchers(HttpMethod.DELETE, "/api/productos/delete/{id}").hasAuthority("DELETE");

                    http.requestMatchers(HttpMethod.DELETE, "/api/proveedores/delete/{id}").hasAuthority("DELETE");

                    
                    //PUT
                    http.requestMatchers(HttpMethod.PUT, "/api/ordenes-compra/actualizar/{id}").hasAuthority("UPDATE");

                    http.requestMatchers(HttpMethod.PUT, "/api/productos/actualizar/{id}").hasAuthority("UPDATE");

                    http.requestMatchers(HttpMethod.PUT, "/api/proveedores/actualizar/{id}").hasAuthority("UPDATE");

                    
                    //PATCH
                    http.requestMatchers(HttpMethod.PUT, "/api/ordenes-compra/actualizar/estado/{id}").hasAuthority("PATCH");

                    http.requestMatchers(HttpMethod.PUT, "/api/productos/{id}/cantidad").hasAuthority("PATCH");

                    http.requestMatchers(HttpMethod.PUT, "/api/proveedores/actualizarPersonalizado/{id}").hasAuthority("PATCH");

                               
                    http.anyRequest().denyAll();
                    
                })
                .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
