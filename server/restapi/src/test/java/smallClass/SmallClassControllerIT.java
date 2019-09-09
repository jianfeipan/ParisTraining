package smallClass;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SmallClassControllerIT {

    @LocalServerPort
    private int port;

    private URL getUsersUrl;
    private URL postUserUrl;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.getUsersUrl = new URL("http://localhost:" + port + "/smallClass/getUsers");
        this.postUserUrl = new URL("http://localhost:" + port + "/smallClass/addtUser");
    }

    @Test
    public void getEmptyList() throws Exception {
        ResponseEntity<String> response = template.getForEntity(getUsersUrl.toString(),
                String.class);
        assertThat(response.getBody(), equalTo("[]"));
    }

    @Test
    public void postUserThenGet() throws Exception {
        ResponseEntity<String> response = template.getForEntity(getUsersUrl.toString(),
                String.class);
        assertThat(response.getBody(), equalTo("[]"));

        HttpEntity<String> request = new HttpEntity<>("Jianfei");
        response = template.postForEntity(postUserUrl.toString(),
                request,
                String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(response.getBody(), equalTo("success"));

        response = template.getForEntity(getUsersUrl.toString(),
                String.class);
        assertThat(response.getBody(), equalTo("[\"Jianfei\"]"));
    }

    @Test
    public void postDuplicatedUserThenGet() throws Exception {
        ResponseEntity<String> response = template.getForEntity(getUsersUrl.toString(),
                String.class);
        assertThat(response.getBody(), equalTo("[]"));

        HttpEntity<String> request = new HttpEntity<>("Jianfei");
        response = template.postForEntity(postUserUrl.toString(),
                request,
                String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(response.getBody(), equalTo("success"));

        response = template.getForEntity(getUsersUrl.toString(),
                String.class);
        assertThat(response.getBody(), equalTo("[\"Jianfei\"]"));

        response = template.postForEntity(postUserUrl.toString(),
                request,
                String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CONFLICT));
        assertThat(response.getBody(), equalTo("userExists"));

        response = template.getForEntity(getUsersUrl.toString(),
                String.class);
        assertThat(response.getBody(), equalTo("[\"Jianfei\"]"));
    }
}
