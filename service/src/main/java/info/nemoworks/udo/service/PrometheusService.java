package info.nemoworks.udo.service;

//import com.alibaba.fastjson.JsonObject;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PrometheusService {
    @Value("http://localhost:9090/api/v1/query_range")
    private String uri;

    public JsonObject fetchPrometheusMetrics(String start, String end, String query, String step) {
//        String uri = "http://localhost:9090/api/v1/query_range";
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("start",start)
                .queryParam("end",end)
                .queryParam("query",query)
                .queryParam("step",step).build();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity entity = new HttpEntity(builder);
        HttpEntity<JsonObject> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
                JsonObject.class);
        return response.getBody();
    }
}
