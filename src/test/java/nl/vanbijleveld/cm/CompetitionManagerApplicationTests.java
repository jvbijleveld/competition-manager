package nl.vanbijleveld.cm;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(CompetitionManagerApplication.class)
public class CompetitionManagerApplicationTests {

    @Autowired
    private MockMvc mvc;

    @InjectMocks
    private CompetitionManagerApplicationTests controller;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRootContext() throws Exception {
        mvc.perform(get("/").contentType(MediaType.TEXT_HTML)).andExpect(status().is4xxClientError());
    }

}
