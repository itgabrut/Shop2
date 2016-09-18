package com.ilya.service;


import com.ilya.model.Client;
import org.junit.*;
import static utils.TestData.TestUser.TEST_CLIENT;

/**
 * Created by ilya on 14.09.2016.
 */
public class UserServiceTest {

   private static ClientService service = new ClientServiceImpl();


    @Before
    public void setUp() throws Exception {
        // Re-initialize
       Client saved =  service.addClient(TEST_CLIENT);
        TEST_CLIENT.setId(saved.getId());
    }

    @After
    public void cleanUp() throws Exception{
        service.deleteClient(TEST_CLIENT.getId());
        TEST_CLIENT.setId(0);
    }



    @Test
    public void testSave() throws Exception {
        Assert.assertEquals(TEST_CLIENT,service.getClient(TEST_CLIENT.getId()));
    }

    @Test
    public void testErrorDelete(){
        Assert.assertEquals(service.deleteClient(1),false);
    }
    @Test
    public void testUpdate(){
        Client client = service.getClient(TEST_CLIENT.getId());
        client.setName("Anton");
        service.addClient(client);
        Assert.assertEquals(client,service.getClient(client.getId()));
    }
}
