package com.madikhan.app.model;

import com.madikhan.app.util.converter.BirthdayConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "profile", schema = "social-network")
public class Profile extends AuditableEntity<Long> {

    @OneToOne
    private User user;

    @Column(length = 16, nullable = false)
    private String firstName;

    @Column(length = 16, nullable = false)
    private String lastName;

    @Column(columnDefinition = "text")
    private String bio;

    @Column()
    @Convert(converter = BirthdayConverter.class)
    private Birthday birthDate;

    @Column(length = 32)
    private String phoneNumber;

    @ManyToMany(cascade = { CascadeType.ALL } )
    @JoinTable(
            name = "friendship",
            joinColumns = { @JoinColumn(name = "profile_id") },
            inverseJoinColumns = { @JoinColumn(name = "friend_id") }
    )
    private Set<Profile> friends = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.ALL } )
    @JoinTable(
            name = "followership",
            joinColumns = { @JoinColumn(name = "following_id") },
            inverseJoinColumns = { @JoinColumn(name = "follower_id") }
    )
    private Set<Profile> followers = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.ALL } )
    @JoinTable(
            name = "followership",
            joinColumns = { @JoinColumn(name = "follower_id") },
            inverseJoinColumns = { @JoinColumn(name = "following_id") }
    )
    private Set<Profile> followings = new HashSet<>();

    @ManyToMany
    private Set<Profile> profileBlackList = new HashSet<>();

    @Column
    private String imageUrl;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profile", orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @ManyToMany(mappedBy = "profilesWhoLiked")
    private Set<Post> likedPosts = new HashSet<>();

}
