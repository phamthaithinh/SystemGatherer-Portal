package io.github.systemgatherer.monitor.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.name.Named;
import io.github.systemgatherer.configuration.Host;
import io.github.systemgatherer.monitor.IRequester;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author Rinat Muhamedgaliev aka rmuhamedgaliev
 */
public class Requester implements IRequester {

    @Override
    public String getStatus(Host host) {

        for (String check: host.getChecks()) {
            String url = "http://"+host.getIp()+":8080/?name="+check;
            try {
                System.out.println(getHttpResponse(url));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  "";
    }

    private String getHttpResponse(String url) throws IOException {
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

        JsonNode node = mapper.convertValue(result, JsonNode.class);

        return result.toString();
    }
}
