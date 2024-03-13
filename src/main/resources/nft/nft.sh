#!/bin/bash

TEXT=$1

if [ ! -f "/etc/vipilink.rules" ]; then
    sudo touch /etc/vipilink.rules
fi

echo "$TEXT" | sudo tee /etc/vipilink.rules >/dev/null
sudo nft -f /etc/nftables.conf