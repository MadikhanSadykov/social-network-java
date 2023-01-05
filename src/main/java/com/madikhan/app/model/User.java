package com.madikhan.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(exclude= {"roles", "profile"})
@ToString(exclude = {"roles", "profile"})
@Table(name = "user")
@Component
public class User implements Serializable {

    private static final long serialVersionUID = 8875668993303528282L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64, unique = true, nullable = false)
    private String email;

    @Column(length = 3000, nullable = false)
    private String password;

    @OneToOne(mappedBy = "user")
    private Profile profile;

    @Transient
    private String confirmPassword;

    @ManyToMany(cascade = { CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") }
    )
    private Set<Role> roles = new HashSet<>();

}
