package org.example.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "demo")
public class DemoProperties {

    private String sayWhat;

    private String toWho;
}
