package com.thehuxley.auth.domain

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.thehuxley.auth.utils.marshaller.RoleSerializer

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.SequenceGenerator
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.UniqueConstraint
import javax.persistence.Version

@Entity @JsonSerialize(using = RoleSerializer)
@Table(schema ="public", name = "role", uniqueConstraints = [
        @UniqueConstraint(name = "role_authority_uk", columnNames = "authority")
])
class Role implements Serializable {

    @Id 
    @SequenceGenerator(name = "sequence-generator", schema = "public", sequenceName = "role_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
    @Column(name = "id", unique = true, nullable = false)
    Long id

    @Version 
    @Column(name = "version")
    Long version

    @Column(name = "authority", unique = true, nullable = false)
    String authority

}
