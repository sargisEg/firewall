package org.firewall.controller;

import lombok.RequiredArgsConstructor;
import org.firewall.model.entity.CustomRules;
import org.firewall.service.RuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/firewall")
public class RuleController {

    private final RuleService ruleService;

    @PutMapping
    ResponseEntity<Void> updateFirewallRules(@RequestBody CustomRules customRules) {
        try {
            ruleService.updateFirewallRules(customRules);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<CustomRules> getFirewallRules() {
        return ResponseEntity.ok().body(ruleService.getRules());
    }
}
