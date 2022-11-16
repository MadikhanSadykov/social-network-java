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
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

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
@Table(name = "post")
@Component
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String caption;

    @Column
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    private Profile profile;

    @OneToOne
    private Status status;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "like",
            joinColumns = { @JoinColumn(name = "post_id") },
            inverseJoinColumns = { @JoinColumn(name = "profile_id") }
    )
    private Set<Profile> profilesWhoLiked = new HashSet<>();

    @OneToMany
    private Set<PostImage> postImages = new HashSet<>();

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "post", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @JsonFormat(pattern = "yyyy-mm-dd HH::mm::ss")
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "yyyy-mm-dd HH::mm::ss")
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
