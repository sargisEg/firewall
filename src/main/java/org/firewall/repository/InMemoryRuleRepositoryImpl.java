package org.firewall.repository;

import lombok.RequiredArgsConstructor;
import org.firewall.model.entity.CustomRules;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;

@Component
@RequiredArgsConstructor
public class InMemoryRuleRepositoryImpl implements RuleRepository {

    private final AtomicReference<CustomRules> customRules;

    @Override
    public CustomRules save(CustomRules customRules) {
        this.customRules.set(customRules);
        return this.customRules.get();
    }

    @Override
    public CustomRules get() {
        return this.customRules.get();
    }
}
