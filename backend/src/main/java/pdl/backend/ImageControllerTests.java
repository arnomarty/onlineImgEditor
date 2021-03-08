package pdl.backend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class ImageControllerTests {

	@Autowired
	private MockMvc mockMvc;
	@LocalServerPort
	private int port;

	@Test
	@Order(1)
	public void getImageListShouldReturnSuccess() throws Exception {
		// TODO
	}

	@Test
	@Order(2)
	public void getImageShouldReturnNotFound() throws Exception {
		this.mockMvc.perform(get("/200")).andExpect(status().isNotFound());
	}

	@Test
	@Order(3)
	public void getImageShouldReturnSuccess() throws Exception {
		// TODO
	}

	@Test
	@Order(4)
	public void deleteImageShouldReturnBadRequest() throws Exception {
		// TODO
	}

	@Test
	@Order(5)
	public void deleteImageShouldReturnNotFound() throws Exception {
		// TODO
	}

	@Test
	@Order(6)
	public void deleteImageShouldReturnSuccess() throws Exception {
		// TODO
	}

	@Test
	@Order(7)
	public void createImageShouldReturnSuccess() throws Exception {
		// TODO
	}

	@Test
	@Order(8)
	public void createImageShouldReturnUnsupportedMediaType() throws Exception {
		// TODO
	}
	
}
