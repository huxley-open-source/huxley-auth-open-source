package com.thehuxley.auth.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class UserRoleId implements Serializable {

    @Column(name = "user_id", insertable = false, updatable = false)
    Long userId

    @Column(name = "role_id", insertable = false, updatable = false)
    Long roleId
    
    @Override
    def int hashCode() {
      final int prime = 31
      int result = 1
      result = prime * result + ((userId == null) ? 0 : userId.hashCode())
      result = prime * result + ((roleId == null) ? 0 : roleId.hashCode())
      return result
    }

    @Override
    def boolean equals(Object obj) {
      if (this == obj)
        return true
      if (obj == null)
        return false
      if (getClass() != obj.getClass())
        return false
      UserRoleId other = (UserRoleId) obj
      if (userId == null) {
        if (other.userId != null)
          return false
      } else if (!userId.equals(other.userId))
        return false
      if (roleId == null) {
        if (other.roleId != null)
          return false
      } else if (!roleId.equals(other.roleId))
        return false
      return true
    }

}