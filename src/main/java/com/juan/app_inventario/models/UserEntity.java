package com.juan.app_inventario.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @Column(name = "account_No_Expired")
    private boolean accountNoExpired;

    @Column(name = "account_No_Locked")
    private boolean accountNoLocked;

    @Column(name = "credential_No_Expired")
    private boolean credentialNoExpired;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

    // Constructor privado para que solo se pueda crear mediante el Builder
    private UserEntity(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.isEnabled = builder.isEnabled;
        this.accountNoExpired = builder.accountNoExpired;
        this.accountNoLocked = builder.accountNoLocked;
        this.credentialNoExpired = builder.credentialNoExpired;
        this.roles = builder.roles;
    }

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean isAccountNoExpired() {
        return accountNoExpired;
    }

    public void setAccountNoExpired(boolean accountNoExpired) {
        this.accountNoExpired = accountNoExpired;
    }

    public boolean isAccountNoLocked() {
        return accountNoLocked;
    }

    public void setAccountNoLocked(boolean accountNoLocked) {
        this.accountNoLocked = accountNoLocked;
    }

    public boolean isCredentialNoExpired() {
        return credentialNoExpired;
    }

    public void setCredentialNoExpired(boolean credentialNoExpired) {
        this.credentialNoExpired = credentialNoExpired;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    // Implementación del patrón Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String username;
        private String password;
        private boolean isEnabled;
        private boolean accountNoExpired;
        private boolean accountNoLocked;
        private boolean credentialNoExpired;
        private Set<RoleEntity> roles = new HashSet<>();

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder isEnabled(boolean isEnabled) {
            this.isEnabled = isEnabled;
            return this;
        }

        public Builder accountNoExpired(boolean accountNoExpired) {
            this.accountNoExpired = accountNoExpired;
            return this;
        }

        public Builder accountNoLocked(boolean accountNoLocked) {
            this.accountNoLocked = accountNoLocked;
            return this;
        }

        public Builder credentialNoExpired(boolean credentialNoExpired) {
            this.credentialNoExpired = credentialNoExpired;
            return this;
        }

        public Builder roles(Set<RoleEntity> roles) {
            this.roles = roles;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(this);
        }
    }
}
