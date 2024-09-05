package com.firebasenotification.firebase.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationSendRequest {

    private String fcmToken;
    private ApplicationNotification notification;
}
