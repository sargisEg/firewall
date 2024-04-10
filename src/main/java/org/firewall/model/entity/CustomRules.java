package org.firewall.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomRules {

    private RuleGroup input;

    private RuleGroup output;

    private RuleGroup forward;
}
