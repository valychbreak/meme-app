package com.int20h.task.memeapp;

import com.int20h.task.memeapp.service.OAuthHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
/*@WebAppConfiguration*/
/*@WebMvcTest(value = UserController.class, secure = true)*/
public class AuthorizationTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	private MockMvc mvc;

	/*@MockBean
	private PartyRepository partyRepo;*/

	@Autowired
	private OAuthHelper helper;

	/*@Autowired
	private WebApplicationContext context;*/

	
	@Before
	public void prepare() {
		/*this.mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.build();*/
	}

	@Test
	@WithAnonymousUser
	public void getPartyTest() throws Exception {
		//RequestPostProcessor token = bearerToken("gigy");
		/*MultiValueMap<String, String> vars = new LinkedMultiValueMap<String, String>();
		vars.add("grant_type", "password");
		vars.add("username", "peter@example.com");
		vars.add("password", "password");

		Map<String, String> vars2 = new HashMap<String, String>();
		vars2.put("grant_type", "password");
		vars2.put("username", "peter@example.com");
		vars2.put("password", "password");

		//ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("http://localhost:8000/gigy/oauth/token", "gigy:secret", String.class, vars);

		new OAuth2Request(
				vars2, "gigy", Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")), true, null, null, null, null, null);
		ResultActions perform = mvc.perform(post("/oauth/token").params(vars).requestAttr("gigy", "secrete").with(authentication()));
		String contentAsString = perform.andReturn().getResponse().getContentAsString();
		*/

		//given(partyRepo.findOne(1l)).willReturn(party);
		OAuth2AccessToken token = helper.createAccessToken("gigy");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + token.getValue());
		ResponseEntity<String> responseEntity = testRestTemplate.exchange("/user", HttpMethod.GET, new HttpEntity<>(headers), String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
		/*mvc.perform(get("/user").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());*/
				/*.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.location", is("Garden")));*/
	}

	// For use with MockMvc
	public RequestPostProcessor bearerToken(final String clientid) {
		return mockRequest -> {
			OAuth2AccessToken token = helper.createAccessToken(clientid);
			mockRequest.addHeader("Authorization", "Bearer " + token.getValue());
			return mockRequest;
		};
	}

	/*@Test
	public void partyNotFoundTest() throws Exception {
		mvc.perform(get("/parties/2").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());
	}*//*

	@Test
	public void getPartiesTest() throws Exception {
		List<Party> parties = new ArrayList<Party>();
		parties.add(party);

		//given(partyRepo.findAll()).willReturn(parties);
		mvc.perform(get("/parties").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].location", is("Garden")));
	}*/
}
