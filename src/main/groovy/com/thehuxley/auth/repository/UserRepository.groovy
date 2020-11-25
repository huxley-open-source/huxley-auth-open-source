package com.thehuxley.auth.repository

import com.thehuxley.auth.domain.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAllByUsernameIgnoreCase(String username)

    User findByUsernameIgnoreCase(String username)

    List<User> findAllByEmailIgnoreCase(String email)

}