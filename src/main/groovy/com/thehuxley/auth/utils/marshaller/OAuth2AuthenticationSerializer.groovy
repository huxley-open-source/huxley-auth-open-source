package com.thehuxley.auth.utils.marshaller

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.springframework.security.oauth2.provider.OAuth2Authentication

class OAuth2AuthenticationSerializer extends JsonSerializer<OAuth2Authentication> {

    @Override
    void serialize(OAuth2Authentication o, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {

        gen.writeStartObject()
        gen.writeObjectField("authorities", o.authorities)
        gen.writeBooleanField("authenticated", o.authenticated)
        gen.writeObjectField("userAuthentication", o.userAuthentication)
        gen.writeBooleanField("clientOnly", o.clientOnly)
        gen.writeObjectField("oauth2Request", o.getOAuth2Request())
        gen.writeObjectField("credentials", o.credentials)
        gen.writeObjectField("principal", o.principal)
        gen.writeStringField("name", o.name)
        gen.writeEndObject()
    }

}
