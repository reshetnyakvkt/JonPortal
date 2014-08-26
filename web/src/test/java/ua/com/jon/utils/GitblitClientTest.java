package ua.com.jon.utils;

import context.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class GitblitClientTest {
    @Autowired
    private GitblitClient client;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testMain() throws Exception {
        client.createUser("a", "a");
    }
}