package com.snglr.precios;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.snglr.precios.adapters.in.rest.TarifaController;

@SpringBootTest
class PreciosApplicationTests {
	
	@Autowired
	private TarifaController tarifaController;

	@Test
	void contextLoads() {
		assertThat(tarifaController).isNotNull();
	}

}
