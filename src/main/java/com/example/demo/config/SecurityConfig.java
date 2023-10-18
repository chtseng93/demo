package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.security.CustomAuthenticationSuccessHandler;
import com.example.demo.security.CustomerAuthenticationProvider;
//若要自訂登入邏輯(畫面..登入方式)則要繼承WebSecurityConfigurerAdapter
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new CustomAuthenticationSuccessHandler();
	}

//	@Autowired
//	private UserDetailsService customUserDetailsService;

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(customUserDetailsService);
//	}
	
	@Autowired
	private CustomerAuthenticationProvider customerAuthenticationProvider;
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customerAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// login這個API可以不用登入就可以用 對應到controller的URL
		http.authorizeHttpRequests().antMatchers("/login","/register","/users/**").permitAll()//註冊跟登入不用
									.antMatchers("/**/*.css","/**/*.js","/imgs/**").permitAll()
				// 剩下的所有請求都要登入才可以用~
				.anyRequest().authenticated().and()
				.formLogin()
				.usernameParameter("userName")
//				.passwordParameter("pwd")
				.passwordParameter("passWord")
				.loginPage("/login")
				.loginProcessingUrl("/userLogin")
				.successHandler(successHandler())
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login");
        
	}

	@Override
	public void configure(WebSecurity web) {
		//靜態檔(css等等不做身分驗證)
		web.ignoring().antMatchers("/resources/**", "/static/**");
	}

}
