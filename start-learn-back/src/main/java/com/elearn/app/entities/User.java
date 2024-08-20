package com.elearn.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String userId;

    private String name;

    // username=email
    @Column(unique = true)
    private String email;

    private String phoneNumber;

    private String password;

    private String about;

    private boolean active;

    private boolean emailVarified;

    private boolean smsVerified;

    private Date createAt;

    private String profilePath;

    private String recentOTP;

    @ManyToMany(mappedBy = "users",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    public void assignRole(Role role)
    {
        this.roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role){
        this.roles.remove(role);
        role.getUsers().remove(this);
    }



}
