package com.alpha.pandemic.models;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component //添加到Ioc容器中
@ConfigurationProperties(prefix = "alioss")
public class AliOss
{
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String urlPrefix;
}
