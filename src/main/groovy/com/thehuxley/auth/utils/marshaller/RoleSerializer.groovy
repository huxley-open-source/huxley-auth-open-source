package com.thehuxley.auth.utils.marshaller

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.thehuxley.auth.domain.Role

class RoleSerializer extends JsonSerializer<Role> {

    @Override
    void serialize(Role role, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeString(role.authority)
    }

}
