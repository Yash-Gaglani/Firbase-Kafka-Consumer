package com.firebasenotification.firebase.model;

import java.util.Map;
import lombok.Data;

@Data
public class ApplicationNotification {

    private String subject;
    private String content;
    private String topic;
    private String notificationType;
    private Map<String, String> data;

}
