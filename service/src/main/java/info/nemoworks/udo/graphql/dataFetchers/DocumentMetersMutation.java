package info.nemoworks.udo.graphql.dataFetchers;

import com.alibaba.fastjson.JSONObject;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

public class DocumentMetersMutation implements DataFetcher<JSONObject> {

    @Override
    public JSONObject get(DataFetchingEnvironment dataFetchingEnvironment) {
        String uri = "http://localhost:9090/api/v1/query_range";
        String start = dataFetchingEnvironment.getArgument("start").toString();
        String end = dataFetchingEnvironment.getArgument("end").toString();
        String query = dataFetchingEnvironment.getArgument("query").toString();
        String step =  dataFetchingEnvironment.getArgument("step").toString();

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("start",start)
                .queryParam("end",end)
                .queryParam("query",query)
                .queryParam("step",step).build();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity entity = new HttpEntity(builder);
        HttpEntity<JSONObject> response = restTemplate.exchange(builder.toUriString(),HttpMethod.GET, entity,
                JSONObject.class);
        return response.getBody();
    }
}