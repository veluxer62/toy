package com.example.demo

import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class Receiver {
    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    fun receiveMessage(message: Message) {
        val restTemplate = RestTemplate()
        restTemplate.postForEntity(
                "https://hooks.slack.com/services/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",
                SlackMessage(text = message.toString()),
                String::class.java
        )
    }
}

data class SlackMessage(
        val text: String
)