package com.cnpm.hr;

import com.cnpm.hr.controller.AuthController;
import com.cnpm.hr.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@SpringBootTest
@WebMvcTest(AuthController.class)
class HrApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AuthService authService;

	// Existing tests...
//
//	@Test
//	public void testLogoutWhenValidRequestThenSuccess() throws Exception {
//		when(authService.logout())
//				.thenReturn(ResponseEntity.ok().build());
//
//		mockMvc.perform(MockMvcRequestBuilders
//						.post("/api/v1/user/logout")
//						.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isOk());
//	}
//
//	@Test
//	public void testLogoutWhenInvalidRequestThenFailure() throws Exception {
//		when(authService.logout())
//				.thenReturn(ResponseEntity.badRequest().build());
//
//		mockMvc.perform(MockMvcRequestBuilders
//						.post("/api/v1/user/logout")
//						.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isBadRequest());
//	}

}
