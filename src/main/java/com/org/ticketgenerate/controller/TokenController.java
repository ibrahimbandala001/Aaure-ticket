package com.org.ticketgenerate.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/ticket")
public class TokenController {
    //    @Autowired
//    TicketRepository ticketRepository;
//
//    @PostMapping("/creation")
//    public void create(@RequestBody TicketModel a)
//    {
//        ticketRepository.save(a);
//    }
//    @GetMapping
//    public void retr(@RequestBody TicketModel a)
//    {
//        ticketRepository.findAll();
//    }
//
//}
    @PostMapping("/create")
    public HttpResponse<String> getAccessToken(String tenantId, String clientId, String clientSecret, String resource)
            throws MalformedURLException, IOException, InterruptedException,URISyntaxException {
        String endpoint = String.format("https://login.microsoftonline.com/%s/oauth2/token", tenantId);
        String postBody = String.format("grant_type=client_credentials&client_id=%s&client_secret=%s&resource=%s",
                clientId, clientSecret, resource, "https://management.azure.com/");
        HttpURLConnection conn = (HttpURLConnection) new URL(endpoint).openConnection();
        conn.setRequestMethod("POST");
        conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setDoOutput(true);
        conn.getOutputStream().write(postBody.getBytes());
        conn.connect();
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(conn.getInputStream());
        String accessToken = null;
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String name = parser.getCurrentName();
            if ("access_token".equals(name)) {
                parser.nextToken();
                accessToken = parser.getText();
            }
        }
        String authentication = accessToken;
        String authorizationHeader = "Bearer " + authentication;
        String query = "SELECT CustomerCountry,MarketplaceLicenseType,MonthStartDate from ISVUsage";
        HttpResponse<String> error=null;

        HttpClient client = HttpClient.newHttpClient();

        // Create HTTP request object
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.partnercenter.microsoft.com/insights/v1/cmp/ScheduledQueries/testQueryResult?exportQuery" + query))
                .GET()
                .header("Authorization", authorizationHeader)
                .header("Content-Type", "application/json")
                .build();
// Send HTTP request
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        return response;

        //         return accessToken;
    }
}






