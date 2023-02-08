package one.neopro.edu.neoproedu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class NeoproEduApplicationTests {
	@Autowired
	private Controller controller;

	@Test
	void contextLoads() {
	}


	@Test
	void postTest() throws Exception {
		String response = String.valueOf(controller.registerNewClient(1L));
		assertEquals("1", response);

	}

}
