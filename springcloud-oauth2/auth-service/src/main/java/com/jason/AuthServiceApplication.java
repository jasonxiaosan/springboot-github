package com.jason;

import com.jason.Service.UserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.util.Assert;

import javax.sql.DataSource;

@EnableEurekaClient
@EnableResourceServer
@SpringBootApplication
public class AuthServiceApplication {
@Autowired
@Qualifier("dataSource")
private DataSource dataSource;
    @Bean
    public TokenStore jdbcTokenStore() {
        Assert.state(dataSource != null, "DataSource must be provided");
        return new JdbcTokenStore(dataSource);
    }
	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}
}
@Configuration
@EnableAuthorizationServer
class  OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter{
    //private TokenStore tokenStore = new InMemoryTokenStore();
    //JdbcTokenStore tokenStore = new JdbcTokenStore(dataSource);
  @Autowired
  @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
  @Autowired
    private UserServiceDetail userServiceDetail;
  @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
      clients.inMemory()
              .withClient("browser")
              .authorizedGrantTypes("refresh_token","password")
              .scopes("ui")
              .and()
              .withClient("service-hi")
              .secret("123456")
              .authorizedGrantTypes("client_credentials","refresh_token","password")
              .scopes("server");
  }
    @Autowired(required = false)
    private TokenStore jdbcTokenStore;
  @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
      endpoints
              .tokenStore(jdbcTokenStore)
              .authenticationManager(authenticationManager)
              .userDetailsService(userServiceDetail);
  }
  @Override
    public  void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception{
      oauthServer
              .tokenKeyAccess("permitAll()")
              .checkTokenAccess("isAuthenticated()");
  }

}