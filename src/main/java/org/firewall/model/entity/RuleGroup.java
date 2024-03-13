package org.firewall.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.firewall.model.enums.PolicyType;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RuleGroup {

    private String id;

    private Set<Rule> rules;

    private PolicyType policyType;

}
