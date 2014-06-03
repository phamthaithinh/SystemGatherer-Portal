package io.github.systemgatherer.sheduller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.github.systemgatherer.monitor.IRequester;
import io.github.systemgatherer.monitor.impl.Requester;
import io.github.systemgatherer.notifier.NotificationModule;
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

        Injector injector = Guice.createInjector(new NotificationModule());
        IRequester requester =

        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        JobDetail job = newJob(Requester.class)
                .withIdentity("job1", "group1")
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
