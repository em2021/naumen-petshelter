package ru.project.naumenpetshelter.utils;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedHashMap;
import java.util.List;

public class LinkUtils {

    private final static String cloudDownloadLink = "https://cloud-api.yandex.net/v1/disk/public/resources/download?public_key=";

    /**
     * Returns a direct download link for a resource on Yandex Disk
     *
     * @param publicKey a public URL for a resource on Yandex Disk
     * @param jsonKey   a key to search the value of the download link in the JSON body
     */
    public static String getDownloadLink(String publicKey, String jsonKey) throws IOException, URISyntaxException,
            InterruptedException, ParseException {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI(cloudDownloadLink + publicKey))
                .build();
        HttpResponse<String> response = httpClient.send(request, bodyHandler);
        JSONParser parser = new JSONParser(response.body());
        LinkedHashMap<String, Object> result = parser.parseObject();
        return result.get(jsonKey).toString();
    }

    /**
     * Returns the filename parsed from a URL query param
     *
     * @param url            a URL to parse query param from
     * @param queryParamName query param name to search for
     */
    public static String getFileName(URL url, String queryParamName) throws URISyntaxException {
        List<NameValuePair> queryParams = new URIBuilder(url.toURI()).getQueryParams();
        return queryParams.stream()
                .filter(param -> param.getName().equalsIgnoreCase(queryParamName))
                .map(NameValuePair::getValue)
                .findFirst()
                .orElse("");
    }
}