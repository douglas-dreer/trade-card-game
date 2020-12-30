package br.com.pokemon.tradecardgame.utils;

import org.springframework.core.convert.Property;
import org.springframework.http.HttpHeaders;

import java.util.*;

public class Utils {
    private HttpHeaders getHeaders(HashMap<String, List<String>> mapHeaders) {
        HttpHeaders headers = new HttpHeaders();
        mapHeaders.forEach((k,v) -> {
            headers.addAll(k, v);
        });
        return headers;
    }

    public void activeProxy() {
       Properties properties = new Properties();
       properties.put("http.proxyHost", "proxy.tcs.com");
       properties.put("http.proxyPort", "8080");
       System.setProperties(properties);
    }
}
