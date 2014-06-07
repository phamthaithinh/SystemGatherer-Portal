package io.github.systemgatherer.notifier;

public interface INotifier {
  boolean sendEmail(String subject, String body);

  void sendSMS(String title, String content, String where);
}
