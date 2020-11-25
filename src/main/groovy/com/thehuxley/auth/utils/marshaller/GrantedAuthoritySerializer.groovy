package com.thehuxley.auth.utils.marshaller

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.springframework.security.core.GrantedAuthority

class GrantedAuthoritySerializer extends JsonSerializer<GrantedAuthority> {

    @Override
    void serialize(GrantedAuthority grantedAuthority, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeString(grantedAuthority.authority)
    }

}
