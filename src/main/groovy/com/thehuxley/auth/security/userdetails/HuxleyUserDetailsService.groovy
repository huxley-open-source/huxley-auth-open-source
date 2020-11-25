package com.thehuxley.auth.security.userdetails

import com.thehuxley.auth.repository.UserRepository
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component


@Component
class HuxleyUserDetailsService implements UserDetailsService {

    static def logger = LogFactory.getLog(HuxleyUserDetailsService)

    @Autowired
    UserRepository userRepository

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(logger.debugEnabled)
            logger.debug "Called loadUserByUsername in HuxleyUserDetailsService, username $username"

        def users = userRepository.findAllByUsernameIgnoreCase(username)

        if (users.empty) {
           users = userRepository.findAllByEmailIgnoreCase(username)
        }

        if(logger.debugEnabled)
            logger.debug "Users found with username $username: ${users.collect { "#$it.id username: $it.username" }} "


        if (users.size() == 1) {
            return new HuxleyUserDetails(users.first())
        } else {
            throw new UsernameNotFoundException("username not found or multiple username found.");
        }
    }

}
