package info.nemoworks.udo.graphql.dataFetchers;

import com.google.gson.JsonObject;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import info.nemoworks.udo.service.PrometheusService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

public class DocumentMetersMutation implements DataFetcher<JsonObject> {

    private final PrometheusService prometheusService;

    public DocumentMetersMutation(PrometheusService prometheusService) {
        this.prometheusService = prometheusService;
    }

    @Override
    public JsonObject get(DataFetchingEnvironment dataFetchingEnvironment) {
        String start = dataFetchingEnvironment.getArgument("start").toString();
        String end = dataFetchingEnvironment.getArgument("end").toString();
        String query = dataFetchingEnvironment.getArgument("query").toString();
        String step =  dataFetchingEnvironment.getArgument("step").toString();

        return prometheusService.fetchPrometheusMetrics(start, end, query, step);
    }
}
