bot4j
=====

[![Build](https://img.shields.io/travis/nitroventures/bot4j.svg)](https://travis-ci.org/nitroventures/bot4j)
[![License](https://img.shields.io/badge/License-BSD%203--Clause-blue.svg)](https://opensource.org/licenses/BSD-3-Clause)

This is a bot framework for Java, which enables the development of lightweight messenger bots (a.k.a. chatbots) with Java. It provides integrations for Facebook Messenger, Slack, Telegram and Alexa and internally unifies message formats by a messaging middleware.

The framework is work in progress, however has been applied in several bot development projects.


Getting started
---------------

### Example project

Clone the [bot4j-example](https://github.com/nitroventures/bot4j-example) repository and follow instructions for getting started.

### Existing project

To include bot4j in your Maven project edit your `pom.xml` file as follows

```
<repositories>
	<repository>
		<id>maven.nitroventures.de</id>
		<url>http://maven.nitroventures.de</url>
	</repository>
</repositories>
```

```
<dependency>
	<groupId>ai.nitro</groupId>
	<artifactId>bot4j</artifactId>
	<version>1.2.0</version>
</dependency>
```


Messenger Integration
---------------------

The bot4j framework provides webhooks for receiving messages from Facebook (`FacebookWebhook`), Slack (`SlackEventWebhook`, `SlackActionWebhook` and `SlackOAuthWebhook`), Telegram (`TelegramWebhook`) and Alexa (`AlexaWebhook`). Bots can send messages to Facebook, Slack and Telegram, if authentication parameters are set in `facebook.properties`, `slack.properties` and `telegram.properties`.

Additional messenger integrations are in the works.


Messaging Middleware
--------------------

Bot4j provides a messaging middleware, which converts incoming and outgoing messages to and from an internal message format. The bot interacts with the internal message format and thus can be connected to several messaging platforms.

Incoming messages are converted to the internal `ReceiveMessage` format containing texts, image attachments, receive notifications etc. Vice versa, outgoing messages are converted from the internal `SendMessage` format to platform-specific message formats. A `SendMessage` can contain texts, images as well as structured elements such as buttons, lists etc.


Dependency Injection
--------------------

All framework classes are instantiated by dependency injection and therefore can be substituted with custom implementations. Dependency injection is based on [guice](https://github.com/google/guice) and [JSR 330](https://www.jcp.org/en/jsr/detail?id=330).


License
-------

Licensed under the BSD 3-Clause License. See LICENSE for details.

### And finally...

Patches accepted, or create an issue!
