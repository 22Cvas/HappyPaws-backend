package org.ncapas.happypawsbackend.Domain.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id_user;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @NotBlank(message = "El DUI no puede estar vacío")
    @Pattern(regexp = "^\\d{8}-\\d{1}$", message = "El DUI debe tener el formato 12345678-9")
    @Column (name = "DUI", unique = true)
    private String DUI;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "^[0-9]{8}$", message = "El teléfono debe tener 8 dígitos numéricos")
    @Column(name = "Phone")
    private String phone;

    @Column(name = "by")
    private Integer by;

    @Column(name = "state")
    private Integer state;

    @Column(name = "creation_date")
    private Date creation_date;

    @Column(name = "last_update")
    private Date last_update;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    private List<Rol> roles = new ArrayList<>();;

    @OneToMany(mappedBy = "Users")
    private List<Aplication> aplications = new ArrayList<>();;

    @OneToMany
    @JoinColumn(name = "id_token")
    private List<Token> tokens = new ArrayList<>();;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return state == 1;
    }
}