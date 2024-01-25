package server.spring.auth.common.config.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import server.spring.auth.common.config.filter.JwtAuthenticationFilter;
import server.spring.auth.common.config.security.handler.CustomAccessDeniedHandler;
import server.spring.auth.common.config.security.handler.CustomAuthenticationEntryPoint;
import server.spring.auth.common.config.security.service.CustomUserDetailsService;

@Configuration
@EnableMethodSecurity // 어노테이션 기반 보안 (default true (생략가능))
@EnableWebSecurity    // 시큐리티 활성화 -> 기본 스프링 필터체인에 등록
//특정 주소로 접근을 하면 권한 및 인증을 미리 체크
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*"); // (Arrays.asList("GET","POST"));
        //config.setAllowedOrigins(Arrays.asList("https://example.com"));

        // package가 추가 되거나 변경 되면 변경
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // security 설정
        // Rest API 형태로 개발하기 때문에 비활성 (  http.httpBasic().disable();)
        http.httpBasic(HttpBasicConfigurer::disable);

        // csrf(의도하지않은 POST, PUT 등으로 사이트 공격) 비활성화
        http.csrf(CsrfConfigurer::disable)
            .cors(httpSecurityCorsConfigurer ->
                httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()));

        // 세션설정 STATELESS
        http.sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 보호해야 할 URL 경로와 보호하지 말아야 할 URL 경로 정의
        http
            .authorizeHttpRequests((requests) -> {
                requests.requestMatchers("/token/**").permitAll();
                requests.requestMatchers("/**").hasAnyAuthority("ROLE_USER", "ROLE_GUEST");
                requests.anyRequest().authenticated();  // 모든 리퀘스트는 인증과정을 거처야 함
            });

        // Exception
        http.exceptionHandling((exceptionHandlingConfigurer)->{
                exceptionHandlingConfigurer.accessDeniedHandler(customAccessDeniedHandler); // 인가 실패
                exceptionHandlingConfigurer.authenticationEntryPoint(customAuthenticationEntryPoint);  // 인증 실패
            });

        // jwt 토큰 필터 적용 (UsernamePasswordAuthenticationFilter 전에 실행)
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.authenticationManager(authenticationManager());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
