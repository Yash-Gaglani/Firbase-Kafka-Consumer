package com.firebasenotification.kafkaconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebasenotification.config.ApplicationConstants;
import com.firebasenotification.firebase.model.NotificationSendRequest;
import com.firebasenotification.firebase.service.FirebaseService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    FirebaseService firebaseService;
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationListener.class);


    public NotificationListener(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @KafkaListener(topics = ApplicationConstants.NOTIFICATION_TOPIC,
        groupId = ApplicationConstants.CONSUMER_GROUP
    )
    public void processApplicationNotification(String notificationSendRequest)
        throws FirebaseMessagingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        NotificationSendRequest request = mapper.readValue(notificationSendRequest, NotificationSendRequest.class);
        LOGGER.info(request.toString());
        firebaseService.sendNotificationToDeviceTokenApp(
            request.getNotification(), request.getFcmToken());
    }
}
