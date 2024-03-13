package org.firewall.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.firewall.model.enums.PolicyType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rule {

    @EqualsAndHashCode.Exclude
    private String id;

    private Boolean isEnabled;

    private String sourceAddress;

    private String destinationAddress;

    private String destinationPort;

    private PolicyType policy;

    @EqualsAndHashCode.Exclude
    private String command;

    @EqualsAndHashCode.Exclude
    private Long orderNumber;

}
