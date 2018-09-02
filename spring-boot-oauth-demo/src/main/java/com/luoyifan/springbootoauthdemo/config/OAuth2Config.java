package com.luoyifan.springbootoauthdemo.config;

import com.luoyifan.springbootoauthdemo.oauth.OAuthUserDetailFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @Author: EvanLuo
 * @Date: 2018/8/20 17:24
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2Config {
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Bean
    public OAuthUserDetailFilter oAuthUserDetailFilter(TokenStore tokenStore) {
        return new OAuthUserDetailFilter(tokenStore);
    }

    @Bean
    public OAuth2AuthorizationConfig oAuth2AuthorizationConfig(AuthenticationManager authenticationManager,
                                                               TokenStore tokenStore,
                                                               UserDetailsService userDetailsService) {
        return new OAuth2AuthorizationConfig(authenticationManager, tokenStore, userDetailsService);
    }
}
