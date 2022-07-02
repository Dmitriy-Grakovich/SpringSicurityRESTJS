package ru.kata.spring.boot_security.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {


    private String password;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Not null")
    @Size(min = 2, max = 30, message = "")
    private String name;

    @NotEmpty(message = "Not null")
    @Size(min = 2, max = 30, message = "")
    private String lastName;

    @Min(value = 0, message = " > 0")
    @Max(value = 130, message = "<130")
    private Integer age;

    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn (name = "user_id"), inverseJoinColumns =
    @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @Override
    public String toString() {
        return id + " | " + name + " | " + lastName + " | " + age + " |";
    }

    public void addRole(Role role){
        roles.add(role);
    }
}
