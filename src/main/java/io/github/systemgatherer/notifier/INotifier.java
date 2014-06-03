package io.github.systemgatherer.notifier;

public interface INotifier {
    boolean sendEmail(String to, String from, String subject, String body);
}
