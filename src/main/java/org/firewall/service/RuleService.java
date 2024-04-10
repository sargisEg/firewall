package org.firewall.service;

import org.firewall.model.entity.CustomRules;

import java.io.IOException;

public interface RuleService {
    void updateFirewallRules(CustomRules customRules) throws IOException;

    CustomRules getRules();
}
