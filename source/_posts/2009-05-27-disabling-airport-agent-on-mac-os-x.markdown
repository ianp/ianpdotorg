---
author: ianp
date: '2009-05-27 17:46:07'
layout: post
slug: disabling-airport-agent-on-mac-os-x
status: publish
title: Disabling Airport Agent on Mac OS X
wordpress_id: '178'
categories:
- Technology
tags:
- Apple
- Mac
---

With OS X Leopard the airport base station utility is now started by
default and will run as a background process occasionally connecting to
Akamai servers to check for updates. I don't use an Airport base station
and I'd like to minimise the number of processes with open network ports
in the interests of security I'd like to disable this 'feature', here's
how: Open \_Airport Utility\_, it won't find any base stations
(obviously) but you can still open the preferences window, uncheck the
following three items: \* Check for updates when opening AirPort Utility
\* Check for updates: Weekly \* Monitor Apple wireless devices for
problems Doing this should both shut down the Airport agent \_and\_
prevent it from automatically starting the next time you log in.
