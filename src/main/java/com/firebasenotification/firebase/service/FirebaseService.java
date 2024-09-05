package com.firebasenotification.firebase.service;

import com.firebasenotification.firebase.model.ApplicationNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {

    FirebaseMessaging firebaseMessaging;

    public FirebaseService( FirebaseMessaging firebaseMessaging ){
        this.firebaseMessaging = firebaseMessaging;
    }

    public String sendNotificationToDeviceTokenApp(ApplicationNotification applicationNotification,
        String token) throws FirebaseMessagingException {
        Notification notification = Notification
            .builder()
            .setTitle(applicationNotification.getSubject())
            .setBody(applicationNotification.getContent())
            .build();
        Message message = Message
            .builder()
            .setToken(token)
            .setNotification(notification)
            .putAllData(applicationNotification.getData())
            .build();
        return firebaseMessaging.send(message);
    }

    public String sendNotificationToDeviceTopic(ApplicationNotification applicationNotification,
        String topic) throws FirebaseMessagingException {
        Notification notification = Notification
            .builder()
            .setTitle(applicationNotification.getSubject())
            .setBody(applicationNotification.getContent())
            .build();
        Message message = Message
            .builder()
            .setTopic(topic)
            .setNotification(notification)
            .putAllData(applicationNotification.getData())
            .build();
        return firebaseMessaging.send(message);
    }

}
