package com.turkcell.spring.workshop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @Column(name="id")
    @GeneratedValue()
    private Integer id;

    private String name;
    @Column(name = "last_name")
    private String lastName;
    private String username;
    private String password;
    private String role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="users_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private List<Role> roles;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // roller
        // todo: refactor with multiple roles
        List<SimpleGrantedAuthority> listOfRoles =
                roles.stream().map((role) -> new SimpleGrantedAuthority(role.getRoleName())).toList();
        return listOfRoles;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
