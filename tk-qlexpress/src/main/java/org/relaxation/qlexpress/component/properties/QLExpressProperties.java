package org.relaxation.qlexpress.component.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "qlexpress")
public class QLExpressProperties {
    private List<Operator> operator;
}
