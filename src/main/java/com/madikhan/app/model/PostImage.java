package com.madikhan.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "post_image", schema = "social-network")
public class PostImage extends BaseEntity<Long> {

    @Column(length = 512)
    private String imageUrl;

    @Column(length = 128)
    private String name;

    @ManyToOne
    private Post post;
}
