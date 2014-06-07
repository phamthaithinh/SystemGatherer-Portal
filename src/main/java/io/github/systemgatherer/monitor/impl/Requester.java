package io.github.systemgatherer.monitor.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.github.systemgatherer.configuration.ConfigLoader;
import io.github.systemgatherer.configuration.Host;
import io.github.systemgatherer.monitor.IRequester;
import io.github.systemgatherer.monitor.Response;
import io.github.systemgatherer.notifier.INotifier;
import io.github.systemgatherer.notifier.NotificationModule;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Rinat Muhamedgaliev aka rmuhamedgaliev
 */
public class Requester implements IRequester, Job {

    @Override
    public String getStatus(Host host) {

        for (String check: host.getChecks()) {
            String url = "http://"+host.getIp()+":8080/?name="+check;
            try {
                System.out.println(getHttpResponse(url, host.getName(), check));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  "";
    }

    private String getHttpResponse(String url, String host, String check) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        HttpResponse response = client.execute(request);

        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        ObjectMapper mapper = new ObjectMapper();

        Response resultResponse = mapper.readValue(result.toString(), Response.class);

        Injector injector = Guice.createInjector(new NotificationModule());
        INotifier notifier = injector.getInstance(INotifier.class);

        if (resultResponse.getCode() != 0) {
            notifier.sendEmail(resultResponse.getName(), resultResponse.getInfo().toString());
            notifier.sendSMS(check, resultResponse.getInfo().toString(), host);
        }

        return result.toString();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        for (Host host: ConfigLoader.getConfig().getHosts()) {
            getStatus(host);
        }
    }
}
