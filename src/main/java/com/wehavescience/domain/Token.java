package com.wehavescience.domain;

import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.mongodb.morphia.annotations.Entity;

import java.io.Serializable;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Accessors(fluent = true)
@EqualsAndHashCode
@Entity("tokens")
public class Token implements Serializable {
    private String username;
    private String token;
}
