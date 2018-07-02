package com.jason.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config  extends AuthorizationServerConfigurerAdapter{

    @Override
    public  void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        clients.inMemory()
                .withClient("user-service")
                .secret("123456")
                .scopes("server")
                .authorizedGrantTypes("refresh_token","password")
                .accessTokenValiditySeconds(3600);
    }
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
     endpoints.tokenStore(tokenStore())
             .tokenEnhancer(jwtTokenEnhancer())
             .authenticationManager(authenticationManager);
    }
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
    @Bean
    public JwtTokenStore tokenStore(){
        return new JwtTokenStore(jwtTokenEnhancer());
    }
    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        org.springframework.security.rsa.crypto.KeyStoreKeyFactory  keyStoreKeyFactory= new org.springframework.security.rsa.crypto.KeyStoreKeyFactory(new ClassPathResource("jason-jwt.jks"),"jason123".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jason-jwt"));
        return converter;
    }
//    protected JwtAccessTokenConverter jwtTokenEnhancer(){
//        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jason-jwt.jks"),"jasonxiaosan".toCharArray());
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jason-jwt"));
//        return converter;
//    }

}
