package com.app.NE.services.mail;

import com.app.NE.models.Notification;

public interface IMailService {
    void sendMail(Notification notification);
}
