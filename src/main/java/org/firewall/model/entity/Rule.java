package org.firewall.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.firewall.model.enums.PolicyType;
import org.firewall.model.enums.Protocol;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rule {

    private Boolean isEnabled;

    private String sourceAddress;

    private String destinationAddress;

    private String destinationPort;

    private Protocol destinationPortProtocol;

    private PolicyType policy;

}
