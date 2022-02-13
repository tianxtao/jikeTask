package com.txt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description :
 * @Date : 2022-02-12
 */
@Data
@ConfigurationProperties(prefix = "klass")
public class KlassProperties {
    private String name1 = "一班";
    private String name2 = "二班";
}
