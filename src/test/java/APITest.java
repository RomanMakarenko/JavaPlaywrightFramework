import com.jayway.jsonpath.JsonPath;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APITest {

    @Test
    public void e2eAPITest() {
        Playwright playwright = Playwright.create();
        APIRequestContext apiRequest = playwright.request().newContext();

        //Log-in
        Map<Object, Object> loginData = new HashMap<>();
        loginData.put("email",  "romantest@test.com");
        loginData.put("password",  "qw123123_QW");

        APIResponse loginResponse = apiRequest.post("https://api.eventhub.rahulshettyacademy.com/api/auth/login",
                RequestOptions.create().setData(loginData));
        Assert.assertTrue(loginResponse.ok());
        String loginToken = JsonPath.read(loginResponse.text(), "$.token");

        //Create event
        Map<Object, Object> eventData = new HashMap<>();
        eventData.put("category", "Concert");
        eventData.put("city", "Lviv");
        eventData.put("description", "some");
        eventData.put("eventDate", "2026-08-30T08:11:00.000Z");
        eventData.put("price", 10);
        eventData.put("title", "PlaywrightTest");
        eventData.put("totalSeats", 200);
        eventData.put("venue", "Central");
        APIResponse createEventResponse = apiRequest.post("https://api.eventhub.rahulshettyacademy.com/api/events",
                RequestOptions.create().setHeader("Authorization", "Bearer " + loginToken)
                        .setData(eventData));
        Assert.assertTrue(createEventResponse.ok(), "Create new event");
        String newEventId = JsonPath.read(createEventResponse.text(), "$.data.id").toString();
        System.out.println(newEventId);

        //Get Events list
        APIResponse getEventsResponse = apiRequest.get("https://api.eventhub.rahulshettyacademy.com/api/events",
                RequestOptions.create()
                        .setHeader("Authorization", "Bearer " + loginToken)
                        .setQueryParam("page", "1")
                        .setQueryParam("limit", "12"));
        Assert.assertTrue(getEventsResponse.ok(), "Event list is present");
        List<Integer> eventsIdsList = JsonPath.read(getEventsResponse.text(), "$.data[*].id");
        Assert.assertTrue(eventsIdsList.contains(Integer.parseInt(newEventId)), "Created event id present in list");

        //Delete event
        APIResponse deleteEventResponse = apiRequest.delete("https://api.eventhub.rahulshettyacademy.com/api/events/" + newEventId,
                RequestOptions.create()
                        .setHeader("Authorization", "Bearer " + loginToken));
        Assert.assertTrue(deleteEventResponse.ok(), "Delete is successful");

        APIResponse getEventsResponseAfterDelete = apiRequest.get("https://api.eventhub.rahulshettyacademy.com/api/events",
                RequestOptions.create()
                        .setHeader("Authorization", "Bearer " + loginToken)
                        .setQueryParam("page", "1")
                        .setQueryParam("limit", "12"));
        Assert.assertTrue(getEventsResponseAfterDelete.ok(), "Event list is present");
        List<Integer> eventsIdsListAfterDelete = JsonPath.read(getEventsResponseAfterDelete.text(), "$.data[*].id");
        Assert.assertFalse(eventsIdsListAfterDelete.contains(Integer.parseInt(newEventId)), "Created event id present in list");
    }
}
