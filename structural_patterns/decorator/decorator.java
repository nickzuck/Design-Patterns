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

class BaseNotification implements Notification {
    @Override
    public void send() {
        // Send from base notification
    }
}

class SlackNotification extends BaseNotification {
    protected Notification notificationObj;
    private String channel ;

    public SlackNotification(Notification notificationObj, String channelName) {
        this.notificationObj = notificationObj;
        this.channel = channelName ;
    }

    @Override
    public void send() {
        super.send();
        System.out.println("Notified on slack channel: " + channel);
    }
}

class EmailNotification extends BaseNotification {
    protected Notification notificationObj;
    private String email ;

    public EmailNotification(Notification notificationObj, String email) {
        this.notificationObj = notificationObj;
        this.email = email ;
    }

    @Override
    public void send() {
        super.send();
        System.out.println("Email send on : " + email);
    }
}

class SMSNotification extends BaseNotification {
    protected Notification notificationObj;
    private String mobileNumber ;

    public SMSNotification(Notification notificationObj, String number) {
        this.notificationObj = notificationObj;
        this.mobileNumber = number;
    }

    @Override
    public void send() {
        super.send();
        System.out.println("SMS sent to : " + mobileNumber);
    }
}

class FacebookNotification extends BaseNotification {
    protected Notification notificationObj;
    private String facebookId ;

    public FacebookNotification(Notification notificationObj, String fbId) {
        this.notificationObj = notificationObj;
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