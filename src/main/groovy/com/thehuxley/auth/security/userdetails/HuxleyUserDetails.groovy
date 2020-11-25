package com.thehuxley.auth.security.userdetails

import com.fasterxml.jackson.annotation.JsonIgnore
import com.thehuxley.auth.domain.Role
import com.thehuxley.auth.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class HuxleyUserDetails implements UserDetails {

    String username
    @JsonIgnore
    String password
    boolean credentialsNonExpired
    boolean accountNonLocked
    boolean accountNonExpired
    boolean enabled
    Collection<? extends GrantedAuthority> authorities

    HuxleyUserDetails(User user) {
        this.username = user.username
        this.password = user.password
        this.credentialsNonExpired = !user.passwordExpired
        this.accountNonExpired = !user.accountExpired
        this.accountNonLocked = !user.accountLocked
        this.enabled = user.enabled
        this.authorities =  user.authorities.collect { Role role -> new SimpleGrantedAuthority(role.authority) }
    }

}
