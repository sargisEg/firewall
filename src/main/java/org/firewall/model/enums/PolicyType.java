package org.firewall.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PolicyType {
    ACCEPT("accept"),
    DROP("drop");

    private final String value;
}
