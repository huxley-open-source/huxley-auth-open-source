package com.thehuxley.auth.security.clientdetails

import com.thehuxley.auth.domain.Client
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.provider.ClientDetails

class HuxleyClientDetails implements ClientDetails {

    String clientId
    Set<String> resourceIds
    boolean secretRequired = false
    String clientSecret
    boolean scoped
    Set<String> scope
    Set<String> authorizedGrantTypes
    Set<String> registeredRedirectUri
    Collection<GrantedAuthority> authorities
    Integer accessTokenValiditySeconds
    Integer refreshTokenValiditySeconds
    Map<String, Object> additionalInformation

    HuxleyClientDetails(Client client) {

        clientId = client.clientId
        clientSecret = client.clientSecret
        resourceIds = client.resourceIds
        secretRequired = clientSecret != null
        scoped = !client.scopes?.empty
        scope = client.scopes
        authorizedGrantTypes = client.authorizedGrantTypes
        registeredRedirectUri = client.redirectUris
        authorities = client.authorities.collect { String authority -> new SimpleGrantedAuthority(authority) }
        accessTokenValiditySeconds = client.accessTokenValiditySeconds
        refreshTokenValiditySeconds = client.refreshTokenValiditySeconds
        additionalInformation = client.additionalInformation

    }

    boolean isAutoApprove(String scope) {
        return true
    }

}