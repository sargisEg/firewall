package org.firewall.repository;

import org.firewall.model.entity.CustomRules;

public interface RuleRepository {

    CustomRules save(CustomRules customRules);

    CustomRules get();
}
