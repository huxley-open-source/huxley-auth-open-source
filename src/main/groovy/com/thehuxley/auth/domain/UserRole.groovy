package com.thehuxley.auth.domain

import javax.persistence.AssociationOverride
import javax.persistence.AssociationOverrides
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OrderColumn
import javax.persistence.Table

@Entity
@Table(schema ="public", name = "user_role")
@AssociationOverrides([
        @AssociationOverride(
                name = "pk_user_id",
                joinColumns = @JoinColumn(name = "key.userId", foreignKey = @ForeignKey(name = "user_role_user_id_fkey"))),
        @AssociationOverride(
                name = "pk_role_id",
                joinColumns = @JoinColumn(name = "key.roleId", foreignKey = @ForeignKey(name = "user_role_role_id_fkey")))
])
class UserRole {

    @EmbeddedId
    UserRoleId key

    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    @OrderColumn(name = "user_role_on_user_id_idx")
    User user

    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name="role_id", insertable = false, updatable = false)
    @OrderColumn(name = "user_role_on_role_id_idx")
    Role role

}
