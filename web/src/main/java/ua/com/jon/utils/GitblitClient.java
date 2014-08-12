package ua.com.jon.utils;

import com.jon.tron.domain.GitUser;
import com.jon.tron.domain.GitUsers;
import com.jon.tron.domain.Tasks;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 05.08.14
 */
@Service
public class GitblitClient {
    private static final Logger log = Logger.getLogger(GitblitClient.class);

    @Autowired
    private RestTemplate restTemplate;

    public void getUsers() {

        Class responseClazz = GitUsers.class;
        String url = "http://localhost:8081/gitblit/?req=LIST_REPOSITORIES";
//        Object response = restTemplate.getForObject(url, responseClazz);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<GitUsers> response =
                restTemplate.exchange(url, HttpMethod.GET, entity, GitUsers.class, "1");
        GitUsers resource = response.getBody();
        log.info(resource);
    }

    private List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJackson2HttpMessageConverter());
        return converters;
    }
}
