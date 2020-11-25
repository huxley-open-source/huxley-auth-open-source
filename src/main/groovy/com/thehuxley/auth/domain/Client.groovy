package com.thehuxley.auth.domain

import javax.persistence.CollectionTable
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.MapKeyColumn
import javax.persistence.OrderColumn
import javax.persistence.Table
import javax.persistence.Version

@Entity
@Table(schema ="public", name = "client")
class Client {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    Long id

    @Version
    @Column(name = "version")
    Long version

    @OrderColumn(name = "idx_client_on_client_id")
    @Column(name = "client_id", unique = true, nullable = false)
    String clientId

    @Column(name = "client_secret", nullable = true)
    String clientSecret

    @Column(name = "access_token_validity_seconds", nullable = true)
    Integer accessTokenValiditySeconds

    @Column(name = "refresh_token_validity_seconds", nullable = true)
    Integer refreshTokenValiditySeconds

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "client_scopes",
            joinColumns = @JoinColumn(
                    name = "client_id",
                    foreignKey = @ForeignKey(name = "client_scopes_client_id_fkey")
            ),
            indexes = @Index(name = "idx_client_scopes_on_client_id", columnList = "client_id")
    )
    @Column(name = "scopes_string")
    Set<String> scopes

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "client_authorities",
            joinColumns = @JoinColumn(
                    name = "client_id",
                    foreignKey = @ForeignKey(name = "client_authorities_client_id_fkey")
            ),
            indexes = @Index(name = "idx_client_authorities_on_client_id", columnList = "client_id")
    )
    @Column(name = "authorities_string")
    Set<String> authorities

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "client_authorized_grant_types",
            joinColumns = @JoinColumn(
                    name = "client_id",
                    foreignKey = @ForeignKey(name = "client_authorized_grant_types_client_id_fkey")
            ),
            indexes = @Index(name = "idx_client_authorized_grant_types_on_client_id", columnList = "client_id")
    )
    @Column(name = "authorized_grant_types_string")
    Set<String> authorizedGrantTypes

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "client_auto_approve_scopes",
            joinColumns = @JoinColumn(
                    name = "client_id",
                    foreignKey = @ForeignKey(name = "client_auto_approve_scopes_client_id_fkey")
            ),
            indexes = @Index(name = "idx_client_auto_approve_scopes_on_client_id", columnList = "client_id")
    )
    @Column(name = "auto_approve_scopes_string")
    Set<String> autoApproveScopes

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "client_redirect_uris",
            joinColumns = @JoinColumn(
                    name = "client_id",
                    foreignKey = @ForeignKey(name = "client_redirect_uris_client_id_fkey")
            ),
            indexes = @Index(name = "idx_client_redirect_uris_on_client_id", columnList = "client_id")
    )
    @Column(name = "redirect_uris_string")
    Set<String> redirectUris

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "client_resource_ids",
            joinColumns = @JoinColumn(
                    name = "client_id",
                    foreignKey = @ForeignKey(name = "client_resource_ids_client_id_fkey")
            ),
            indexes = @Index(name = "idx_client_resource_ids_on_client_id", columnList = "client_id")
    )
    @Column(name = "resource_ids_string")
    Set<String> resourceIds

    @ElementCollection(targetClass = String, fetch = FetchType.EAGER)
    @JoinTable(name = "client_additional_information",
            joinColumns = @JoinColumn(
                    name = "additional_information",
                    foreignKey = @ForeignKey(name = "client_additional_information_client_id_fkey")
            )
    )
    @Column(name = "additional_information_elt")
    @MapKeyColumn(name = "additional_information_idx")
    Map<String, Object> additionalInformation

}
