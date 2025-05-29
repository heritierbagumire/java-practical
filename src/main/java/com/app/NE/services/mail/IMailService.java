package com.app.NE.services.mail;

import com.app.NE.models.Message;

public interface IMailService {
    void sendMail(Message message);
}
