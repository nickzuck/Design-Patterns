/*
Design a notification system which can send notification through multiple channels : Slack, Facebook, Email, SMS etc.
Some of the customers require the notification to be send through not just one channel but multiple channels.
The configuration again can change based not just on the customer but on the type of product as well. For this problem
though, we won't include product but the code should be flexible enough to cater to this requirement
 */

import java.lang.* ;

interface Notification {
    void send() ;
}

// Concrete Base implementation of Notification
class BaseNotification implements Notification {
    @Override
    public void send() {
        // Send from base notification
    }
}

abstract class NotificationDecorator implements Notification {
    protected Notification wrapped;

    public NotificationDecorator(Notification notificationObj) {
        this.wrapped = notificationObj;
    }

    @java.lang.Override
    public void send() {
        wrapped.send();
    }
}

class SlackNotification extends NotificationDecorator {
    private String channel ;

    public SlackNotification(Notification notificationObj, String channelName) {
        super(notificationObj);
        this.channel = channelName ;
    }

    @Override
    public void send() {
        super.send();
        System.out.println("Notified on slack channel: " + channel);
    }
}

class EmailNotification extends NotificationDecorator {
    private String email ;

    public EmailNotification(Notification notificationObj, String email) {
        super(notificationObj);
        this.email = email ;
    }

    @Override
    public void send() {
        super.send();
        System.out.println("Email send on : " + email);
    }
}

class SMSNotification extends NotificationDecorator {
    private String mobileNumber ;

    public SMSNotification(Notification notificationObj, String number) {
        super(notificationObj);
        this.mobileNumber = number;
    }

    @Override
    public void send() {
        super.send();
        System.out.println("SMS sent to : " + mobileNumber);
    }
}

class FacebookNotification extends NotificationDecorator {
    private String facebookId ;

    public FacebookNotification(Notification notificationObj, String fbId) {
        super(notificationObj);
        this.facebookId = fbId;
    }

    @Override
    public void send() {
        super.send();
        System.out.println("Facebook notification sent to : " + facebookId);
    }
}

public class decorator {
    public static void main(String[] args) {
        // Customer wants to send email + slack notification
        Notification emailSlackNotif = new SlackNotification(
                new EmailNotification( new BaseNotification(), "test@gmail.com"),
                "slackChannel"
        ) ;
        emailSlackNotif.send();
        System.out.println("");

        // Customer wants to send slack + facebook + sms notification
        Notification fbSlackSMSNotif = new SlackNotification(
                new FacebookNotification(
                        new SMSNotification(new BaseNotification(), "981883857"),
                        "fb:23424"),
                "slackChannel2"
        ) ;
        fbSlackSMSNotif.send();
    }
}