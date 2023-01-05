package com.madikhan.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class LocalId implements Serializable {

    private static final long serialVersionUID = 7969312284284367874L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "local_id")
    private Local local;

}
