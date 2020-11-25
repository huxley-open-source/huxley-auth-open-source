package com.thehuxley.auth.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.thehuxley.auth.domain.User
import com.thehuxley.auth.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @Autowired
    UserRepository userRepository

    @Autowired
    ObjectMapper mapper

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/user")
    User user() {
        userRepository.findByUsername(SecurityContextHolder.getContext().authentication.name)
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/me")
    OAuth2Authentication me(OAuth2Authentication principal) {
        principal
    }

}
