package com.txt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description :
 * @Date : 2022-02-12
 */
@Data
@ConfigurationProperties(prefix ="student")
public class StudentProperties {
    private int id1 = 1101;
    private String name1 = "小明";
    private int id2 = 1102;
    private String name2 = "小红";
    private int id3 = 1103;
    private String name3 = "小花";
}
