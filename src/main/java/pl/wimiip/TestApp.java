package pl.wimiip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import pl.wimiip.security.CsrfHeaderFilter;

@SpringBootApplication
@ImportResource("classpath:META-INF/spring/testapp-context.xml")
public class TestApp {

	public static void main(String[] args) {
		SpringApplication.run(TestApp.class, args);
	}

	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	public static class SecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
					.httpBasic().and()
					.logout().and()
					.authorizeRequests()
					.antMatchers("/index.html", "/", "/app.js", "/app.route.js", "/assets/**", "/app/**").permitAll()
					.anyRequest().authenticated().and()
					.addFilterAfter( new CsrfHeaderFilter(), CsrfFilter.class)
					.csrf().csrfTokenRepository( csrfTokenRepository() );
		}

		private CsrfTokenRepository csrfTokenRepository() {
			HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
			repository.setHeaderName("X-XSRF-TOKEN"); // this is the name angular uses by default.
			return repository;
		}
	}
}
