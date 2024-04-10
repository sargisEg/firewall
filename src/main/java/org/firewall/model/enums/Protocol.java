package org.firewall.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Protocol {

    TCP("tcp"),
    UDP("udp");

    private final String value;
}
