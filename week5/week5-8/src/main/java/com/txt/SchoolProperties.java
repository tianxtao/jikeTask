package com.txt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description :
 * @Date : 2022-02-12
 */
@Data
@ConfigurationProperties(prefix ="school")
public class SchoolProperties {
    private String name = "西安皇家学院";
}
