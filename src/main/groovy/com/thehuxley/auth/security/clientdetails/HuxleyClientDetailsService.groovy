package com.thehuxley.auth.security.clientdetails

import com.thehuxley.auth.repository.ClientRepository
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.provider.ClientDetails
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.ClientRegistrationException
import org.springframework.security.oauth2.provider.NoSuchClientException
import org.springframework.stereotype.Component

@Component
class HuxleyClientDetailsService implements ClientDetailsService {

    static def logger = LogFactory.getLog(HuxleyClientDetailsService)

    @Autowired
    ClientRepository clientRepository

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        if(logger.debugEnabled)
            logger.debug "Called loadClientByClientId in HuxleyClientDetailsService, clientId $clientId"

        def clients = clientRepository.findAllByClientId(clientId)

        if(logger.debugEnabled)
            logger.debug "Clients found with clientId $clientId: ${ clients.collect { "#$it.id username: $it.clientId" } } "


        if (clients.size() == 1) {
            return new HuxleyClientDetails(clients.first())
        } else {
            throw new NoSuchClientException("clientId not found or multiple clientId found.");
        }
    }

}
