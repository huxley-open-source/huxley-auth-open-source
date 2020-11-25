package com.thehuxley.auth.repository

import com.thehuxley.auth.domain.Client
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository extends CrudRepository<Client, Long> {

    List<Client> findAllByClientId(String clientId)

}