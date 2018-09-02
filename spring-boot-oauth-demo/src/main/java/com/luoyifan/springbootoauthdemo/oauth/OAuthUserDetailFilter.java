package com.luoyifan.springbootoauthdemo.oauth;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author: EvanLuo
 * @Date: 2018/8/17 12:53
 */
public class OAuthUserDetailFilter implements Filter {

    private final TokenStore tokenStore;

    @Autowired
    public OAuthUserDetailFilter(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String tokenValue = (String) servletRequest.getAttribute("OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE");
        if (StringUtils.isNotBlank(tokenValue)) {
            OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(tokenValue);
            OAuthManager.OAUTH_USER_DETAIL.set((UserDetail)oAuth2Authentication.getPrincipal());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
