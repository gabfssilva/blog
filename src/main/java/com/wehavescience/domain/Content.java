package com.wehavescience.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Data
@Accessors(fluent = true)
public class Content implements Serializable{
    private String content;
}
