package io.github.systemgatherer.sheduller;

<<<<<<< HEAD
import io.github.systemgatherer.monitor.impl.Requester;
=======
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.github.systemgatherer.monitor.RequestJob;
import io.github.systemgatherer.notifier.NotificationModule;
>>>>>>> 7b8de7310480a09a5e5d13563618e0cef6bb9a90
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
* @author Rinat Muhamedgaliev aka rmuhamedgaliev
*/
public class Sheduller {

    public void jobRunner() throws SchedulerException {
<<<<<<< HEAD
=======
        Injector injector = Guice.createInjector(new NotificationModule());
        RequestJob requestJob = injector.getInstance(RequestJob.class);
>>>>>>> 7b8de7310480a09a5e5d13563618e0cef6bb9a90

        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        JobDetail job = newJob(requestJob.getClass()).withIdentity("job1", "group1")
                .build();

        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInMinutes(1)
                        .repeatForever())
                .build();

        sched.scheduleJob(job, trigger);

        sched.start();


    }
}
