package org.firewall.helper;

import org.firewall.model.entity.Rule;
import org.firewall.model.enums.PolicyType;
import org.firewall.model.enums.Protocol;
import org.springframework.util.StringUtils;

public class RuleCommandGenerator {

    public static String getCommand(Rule rule) {
        StringBuilder ruleBuilder = new StringBuilder();

        ruleBuilder.append(rule.getIsEnabled() ? "" : "#");

        addSourceAddress(rule.getSourceAddress(), ruleBuilder);
        addDestinationAddress(rule.getDestinationAddress(), ruleBuilder);
        addDestinationPort(rule.getDestinationPort(), rule.getDestinationPortProtocol(), ruleBuilder);
        addPolicy(rule.getPolicy(), ruleBuilder);

        return ruleBuilder.toString();
    }

    private static void addPolicy(PolicyType policy, StringBuilder ruleBuilder) {
        if (policy != null) {
            ruleBuilder.append(policy.getValue());
        }
    }

    private static void addDestinationPort(String destinationPort, Protocol protocol, StringBuilder ruleBuilder) {
        if (StringUtils.hasText(destinationPort) && protocol != null) {
            ruleBuilder.append(protocol.getValue()).append(" dport { ").append(destinationPort).append(" } ");
        }
    }

    private static void addDestinationAddress(String destinationAddress, StringBuilder ruleBuilder) {
        if (StringUtils.hasText(destinationAddress)) {
                ruleBuilder.append("ip daddr { ").append(destinationAddress).append(" } ");
        }
    }

    private static void addSourceAddress(String sourceAddress, StringBuilder ruleBuilder) {
        if (StringUtils.hasText(sourceAddress)) {
                ruleBuilder.append("ip saddr { ").append(sourceAddress).append(" } ");
        }
    }

}
