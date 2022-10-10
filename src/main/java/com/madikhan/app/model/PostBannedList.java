package com.madikhan.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "post_banned_list", schema = "social-network")
public class PostBannedList extends BaseEntity<Long> {

    @OneToOne
    private Post post;

    @Column
    private String reason;
}
