package com.myseat.api.myseat.service;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.myseat.Launcher;
import com.myseat.api.myseat.model.Chair;
import com.myseat.api.myseat.model.Chairs;
import com.myseat.api.myseat.model.Content;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= Launcher.class)
public class MyseatServiceTest {

	private MockRestServiceServer mockServer;

	@Mock
	private RestTemplate restTemplate = new RestTemplate();
	
	@InjectMocks
	private MyseatService myseatService;

	@Before
	public void setUp() {
		mockServer = MockRestServiceServer.bindTo(restTemplate).build();	
	}

	
	@Test
	public void testGetSensorsInGroup() {
		mockServer.expect(requestTo("https://apiV3.myseat.fr/Request/GetChairsInGroup/key/TfkycL8tgKKbkEHEXlyzeKKRZ0pGtSKdZWC1xAkE/id/244"))
		.andExpect(method(HttpMethod.GET))
		.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("apiKey", "TfkycL8tgKKbkEHEXlyzeKKRZ0pGtSKdZWC1xAkE");
		params.put("groupId", "244");
		restTemplate.getForObject("https://apiV3.myseat.fr/Request/GetChairsInGroup/key/{apiKey}/id/{groupId}", Content.class, params);
		mockServer.verify();
	}
	
	
	public Content createContentResponse() {
		Chair chair = new Chair();
		chair.setId_geometry(1);
		chair.setName("A");
		chair.setLast_communication("2018-05-01");
		chair.setPrevious_communication("2018-05-01");
		chair.setQrCode("Q");
		chair.setSensor("A1");
		chair.setStatus(1);
		List<Chair> chairList = new ArrayList<Chair>();
		chairList.add(chair);
		Chairs chairs = new Chairs();
		chairs.setChairs(chairList);
		Content content = new Content();
		content.setChairs(chairs);
		
		return content;
		
	}
	

}
