package com.hazelcast;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Member {

    public static void main(String[] args) throws InterruptedException {
        ClientConfig clientConfig = new ClientConfig();

        clientConfig.setClusterName("dev");
        clientConfig.getNetworkConfig().addAddress("localhost:5701");

        HazelcastInstance hazelcastClient = HazelcastClient.newHazelcastClient(clientConfig);

        System.out.println("Client connected to cluster: " + hazelcastClient.getConfig().getClusterName());

        Map<String, Person> map = hazelcastClient.getMap("new_map");

        for (int i=0;i<10000;i++) {
            map.put("Person" + i, new Person("Person" + i));
        }

        List<Person> personsList = new ArrayList<Person>(map.values());

        System.out.println(personsList);

        TimeUnit.SECONDS.sleep(30);

        hazelcastClient.shutdown();
    }
}
