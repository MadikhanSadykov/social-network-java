package com.madikhan.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "profile")
@Component
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 32, nullable = false)
    private String username;

    @Column(name = "first_name", length = 16, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 16, nullable = false)
    private String lastName;

    @Column(columnDefinition = "text")
    private String bio;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "phone_number", length = 32)
    private String phoneNumber;

    @Column(name = "user_id")
    private Long userId;

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

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profile", orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @ManyToMany(mappedBy = "profilesWhoLiked")
    private Set<Post> likedPosts = new HashSet<>();

    @JsonFormat(pattern = "yyyy-mm-dd HH::mm::ss")
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "yyyy-mm-dd HH::mm::ss")
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = LocalDateTime.now();
    }

}
