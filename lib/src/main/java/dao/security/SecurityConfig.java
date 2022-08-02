package dao.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		 http.cors()
//         .and()
//           .authorizeRequests()
//             .antMatchers(HttpMethod.GET, "/products")
//               .hasAuthority("SCOPE_read")
//             .antMatchers(HttpMethod.POST, "/products")
//               .hasAuthority("SCOPE_write")
//             .anyRequest()
//               .authenticated()
//         .and()
//           .oauth2ResourceServer()
//             .jwt();
//	}
	
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 http.cors()
      .and()
        .authorizeRequests()
          .antMatchers(HttpMethod.GET, "/products")
            .hasAuthority("SCOPE_read")
          .antMatchers(HttpMethod.POST, "/products")
            .hasAuthority("SCOPE_write")
          .anyRequest()
            .authenticated()
      .and()
        .oauth2ResourceServer()
          .jwt();
        return http.build();
    }

}
