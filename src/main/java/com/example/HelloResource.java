package com.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class HelloResource {
    @GET
    @Path("/add")
    @Produces(MediaType.TEXT_PLAIN)
    public String add(@QueryParam("num1") int num1, @QueryParam("num2") int num2) throws InterruptedException {
        String[] results = new String[10];
        for(int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                int sum = num1 + num2;
                results[finalI] = "Process ID: " + finalI + ", result: " + num1 + " + " + num2 + " = " + sum;
            }).start();
        }

        Thread.sleep(5000);

        StringBuilder output = new StringBuilder();
        for(String s : results) {
            output.append(s).append("\n");
        }
        return output.toString();
    }
}