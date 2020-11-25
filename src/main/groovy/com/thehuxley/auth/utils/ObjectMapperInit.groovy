package com.thehuxley.auth.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.thehuxley.auth.utils.marshaller.GrantedAuthoritySerializer
import com.thehuxley.auth.utils.marshaller.OAuth2AuthenticationSerializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

@Component
class ObjectMapperInit {

    @Autowired
    ObjectMapper mapper

    @PostConstruct
    def init() {
        SimpleModule module = new SimpleModule()
        module.addSerializer(OAuth2Authentication, new OAuth2AuthenticationSerializer())
        module.addSerializer(GrantedAuthority, new GrantedAuthoritySerializer())
        mapper.registerModule(module)
    }

}
