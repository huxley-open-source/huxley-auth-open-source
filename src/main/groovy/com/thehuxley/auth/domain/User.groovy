package com.thehuxley.auth.domain

import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.SequenceGenerator
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType
import javax.persistence.Transient
import javax.persistence.UniqueConstraint
import javax.persistence.Version

@Entity
@Table(schema ="public", name = "user", uniqueConstraints = [
        @UniqueConstraint(name = "user_username_uk", columnNames = "username"),
        @UniqueConstraint(name = "user_email_uk", columnNames = "email")
])
class User implements Serializable {

    @Id 
    @SequenceGenerator(name = "sequence-generator", schema = "public", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
    @Column(name = "id", unique = true, nullable = false)
    Long id

    @Version @Column(name = "version")
    Long version

    @Column(name = "username", unique = true, nullable = false)
    String username

    @JsonIgnore  @Column(name = "password", unique = true, nullable = false)
    String password

    @Column(name = "enabled", nullable = false)
    Boolean enabled = true

    @Column(name = "account_expired", nullable = false)
    Boolean accountExpired = false

    @Column(name = "account_locked", nullable = false)
    Boolean accountLocked = false

    @Column(name = "password_expired", nullable = false)
    Boolean passwordExpired = false

    @Column(name = "name", nullable = false)
    String name

    @Column(name = "email", nullable = false)
    String email

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created", nullable = true)
    Date dateCreated

    @Column(name = "last_updated", nullable = true)
    Date lastUpdated

    @Column(name = "avatar", nullable = true)
    String avatar = "default.png"

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "key.userId")
    Set<UserRole> authorities

    @Transient
    def getAuthorities() {
        authorities.collect { UserRole userRole -> return userRole.role }
    }

}
