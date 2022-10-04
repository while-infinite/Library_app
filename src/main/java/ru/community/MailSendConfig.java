package ru.community;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.community.entity.Reader;
import ru.community.service.Notification;

@EnableScheduling
@RequiredArgsConstructor
@Configuration
public class MailSendConfig {

    private final Notification notification;
    private final BookBindingRepository bookBindingRepository;

    @Scheduled(cron = "0 0 14 * * ?")
    public void scheduledSendMail(){
        List<BookBinding> bookBindingList = bookBindingRepository.findAll();

        for (BookBinding bb : bookBindingList) {
            if(Status.EXPIRED = bb.getStatus){
                Reader reader = bb.getReader();
                notification.sendMail(reader.getEmail(), "Subject", "Body");
            }
        }
    }
}
