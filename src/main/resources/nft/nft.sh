#!/bin/bash

TEXT=$1

if [ ! -f "/etc/firewall.rules" ]; then
    sudo touch /etc/firewall.rules
fi

echo "$TEXT" | sudo tee /etc/firewall.rules >/dev/null
sudo systemctl restart nftables
