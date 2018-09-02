package com.luoyifan.springbootoauthdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @Author: EvanLuo
 * @Date: 2018/8/16 12:38
 */
@Configuration
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userService;

    private final TokenStore tokenStore;

    @Autowired
    public OAuth2AuthorizationConfig(AuthenticationManager authenticationManager,
                                     TokenStore tokenStore,
                                     UserDetailsService userService) {
        super();
        this.authenticationManager = authenticationManager;
        this.tokenStore = tokenStore;
        this.userService = userService;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client_1")
                .secret("123456")
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("read", "write")
                .accessTokenValiditySeconds(10000) //token过期时间
                .refreshTokenValiditySeconds(10000); //refresh过期时间
        /*        authorization_code
                password
        client_credentials
                implicit
        refresh_token
        clients.withClientDetails(userService)
                .*/
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore)
//                .pathMapping()
//                /oauth/authorize：授权端点
//                /oauth/token：令牌端点
//                /oauth/confirm_access：用户确认授权提交端点
//                /oauth/error：授权服务错误信息端点
//                /oauth/check_token：用于资源服务访问的令牌解析端点
//                /oauth/token_key：提供公有密匙的端点，如果使用JWT令牌的话
                .userDetailsService(userService); //配置userService 这样每次认证的时候会去检验用户是否锁定，有效等
    }


}
