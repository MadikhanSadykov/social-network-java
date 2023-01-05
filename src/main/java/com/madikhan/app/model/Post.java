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
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
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
public class  Post implements Serializable {

    private static final long serialVersionUID = 341154168179551535L;

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
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "like",
            joinColumns = { @JoinColumn(name = "post_id") },
            inverseJoinColumns = { @JoinColumn(name = "profile_id") }
    )
    private Set<Profile> profilesLikedPost = new HashSet<>();

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "post", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToOne(mappedBy = "post")
    private PostBannedList postBannedList;

    @JsonFormat(pattern = "yyyy-mm-dd HH::mm::ss")
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;


    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = LocalDateTime.now();
    }

}
