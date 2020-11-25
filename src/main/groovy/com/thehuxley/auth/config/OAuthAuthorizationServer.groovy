package com.thehuxley.auth.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.ClientDetailsService

@Configuration
@EnableAuthorizationServer
class OAuthAuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager

    @Autowired
    ClientDetailsService huxleyClientDetailsService

    @Autowired
    UserDetailsService huxleyUserDetailsService

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("ui")
                    .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit", "client_credentials")
                    .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                    .scopes("read", "write", "trust")
                    .accessTokenValiditySeconds(2592000).and()
                .withClient("dev")
                    .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit", "client_credentials")
                    .authorities("ROLE_CLIENT")
                    .scopes("read", "write", "trust")
                    .accessTokenValiditySeconds(3600)
                    .redirectUris("http://doc.thehuxley.com/o2c.html", "https://www.thehuxley.com/o2c.html", "https://www.getpostman.com/oauth2/callback")
                    .secret("dev_secret").and()
                .withClient("auto")
                    .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit", "client_credentials")
                    .authorities("ROLE_CLIENT")
                    .scopes("read", "write", "trust")
                    .accessTokenValiditySeconds(3600)
                    .redirectUris(
                        "http://doc.thehuxley.com/o2c.html",
                        "https://www.thehuxley.com/o2c.html",
                        "https://www.getpostman.com/oauth2/callback",
                        "http://localhost:5000/o2c.html",
                        "http://localhost:9000/o2c.html"
                    )
                    .autoApprove(true)
                    .secret("auto_secret").and()
                .withClient("a4s9duf324has2")
                    .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit", "client_credentials")
                    .authorities("ROLE_CLIENT")
                    .scopes("read", "write", "trust")
                    .accessTokenValiditySeconds(3600)
                    .redirectUris(
                        "http://doc.thehuxley.com/o2c.html",
                        "https://www.thehuxley.com/o2c.html",
                        "https://www.getpostman.com/oauth2/callback",
                        "http://localhost:5000/o2c.html",
                        "http://localhost:9000/o2c.html"
                    )
                    .secret("a9sg61sd74fyuy").and()
                .withClient("24rgf3as3v")
                    .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit", "client_credentials")
                    .authorities("ROLE_CLIENT")
                    .scopes("read", "write", "trust")
                    .accessTokenValiditySeconds(3600)
                    .redirectUris(
                    "http://doc.thehuxley.com/o2c.html",
                    "https://www.thehuxley.com/o2c.html",
                    "https://www.getpostman.com/oauth2/callback",
                    "http://localhost:5000/o2c.html",
                    "http://localhost:9000/o2c.html"
            )
                    .autoApprove(true)
                    .secret("7tj3ug3yh5").and()

        //clients.withClientDetails(huxleyClientDetailsService)
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(huxleyUserDetailsService)
    }

}
