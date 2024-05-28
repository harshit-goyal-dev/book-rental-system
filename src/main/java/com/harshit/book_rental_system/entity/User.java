package com.harshit.book_rental_system.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.harshit.book_rental_system.dto.UserRequestDto;
import com.harshit.book_rental_system.enums.Role;
import com.harshit.book_rental_system.exception.InvalidRoleException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email
    private String email;

    @NotEmpty
    @Length(min=8)
    private String password;

    @NotNull
    private Role role;

    @Size(max = 2)
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Book> books;

    public User(@NotNull UserRequestDto dto){
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.role = findRole(dto.getRole());
    }

    public Role findRole(String role){
        if(role.equalsIgnoreCase("Admin"))return Role.ADMIN;
        else if(role.equalsIgnoreCase("User")) return Role.USER;
        else throw new InvalidRoleException("Role is invalid");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
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
