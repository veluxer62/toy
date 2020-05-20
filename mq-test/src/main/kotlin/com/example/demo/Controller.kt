package com.example.demo

import org.apache.activemq.ScheduledMessage
import org.springframework.jms.core.JmsTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
        private val jmsTemplate: JmsTemplate
) {
    @GetMapping("/send-mq")
    fun sendMq(@RequestParam("body") body: String, @RequestParam("delay") delay: Long) {
        println("body: ${body}, delay: $delay")
        jmsTemplate.convertAndSend("mailbox", Message(body)) {
            it.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay)
            it
        }
    }
}