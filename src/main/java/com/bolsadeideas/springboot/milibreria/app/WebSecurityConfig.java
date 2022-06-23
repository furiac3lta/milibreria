package com.bolsadeideas.springboot.milibreria.app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bolsadeideas.springboot.milibreria.app.util.LoginSuccessMessage;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	@Autowired
	private LoginSuccessMessage successMessage;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/","/index","/styles/**","/images/**","/prestamo/**").permitAll()
		.antMatchers("/listar_libro").hasAnyRole("USER")
		.antMatchers("/form_lib").hasAnyRole("ADMIN")
		.antMatchers("/form_lib/**").hasAnyRole("ADMIN")
		.antMatchers("/eliminar_lib/**").hasAnyRole("ADMIN")
		.antMatchers("/listar_autor").hasAnyRole("USER")
		.antMatchers("/form").hasAnyRole("ADMIN")
		.antMatchers("/eliminar/**").hasAnyRole("ADMIN")
		.antMatchers("/editoriales").hasAnyRole("USER")
		.antMatchers("/form_edit").hasAnyRole("ADMIN")
		.antMatchers("/form_edit/**").hasAnyRole("ADMIN")
		.antMatchers("/eliminar_ed/**").hasAnyRole("ADMIN")
		.antMatchers("/prestamo/**").hasAnyRole("ADMIN")
		.antMatchers("/prestamo/**").hasAnyRole("USER")
		
		.anyRequest().authenticated()
		.and()
		.formLogin()
			.successHandler(successMessage)
			.loginPage("/login")
		.permitAll()
		.and()
		.logout().permitAll()
		
		;
	
	}

	@Autowired
	public void configurerSecurityGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(passEncoder)
		.usersByUsernameQuery("select username, password, alta from users where username=?")
		.authoritiesByUsernameQuery("select u.username, r.rol from roles r inner join users u on r.id_user=u.id_user where u.username=?");
	}

}
