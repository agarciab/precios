package com.snglr.precios.adapters.in.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TarifaControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	// Token de autorización a incluir en todas las peticiones
	private static final String AUTH_TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."
			+ "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNzM4NTI0MjA2fQ."
			+ "1d_yAXQezLvvjoJobiQBGrR3FptqZ7LX4X-pV2C1SPk";

	/**
	 * Test 1: Petición a las 10:00 del día 14 para el producto 35455 y brand 1
	 * (ZARA). Se espera que se retorne la tarifa con PRICE_LIST 1, válida de
	 * "2020-06-14T00:00:00" a "2020-12-31T23:59:59" y precio 35.50 EUR.
	 */
	@Test
	void testTarifaAt10OnJune14() throws Exception {
		mockMvc.perform(get("/tarifas").param("date", "2020-06-14T10:00:00").param("productId", "35455")
				.param("brandId", "1").header("Authorization", AUTH_TOKEN).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.productId", is(35455)))
				.andExpect(jsonPath("$.brandId", is(1))).andExpect(jsonPath("$.priceList", is(1)))
				.andExpect(jsonPath("$.price", is(35.50))).andExpect(jsonPath("$.curr", is("EUR")))
				.andExpect(jsonPath("$.startDate", is("2020-06-14T00:00:00")))
				.andExpect(jsonPath("$.endDate", is("2020-12-31T23:59:59")));
	}

	/**
	 * Test 2: Petición a las 16:00 del día 14 para el producto 35455 y brand 1
	 * (ZARA). Se espera que se retorne la tarifa con PRICE_LIST 2, válida de
	 * "2020-06-14T15:00:00" a "2020-06-14T18:30:00" y precio 25.45 EUR.
	 */
	@Test
	void testTarifaAt16OnJune14() throws Exception {
		mockMvc.perform(get("/tarifas").param("date", "2020-06-14T16:00:00").param("productId", "35455")
				.param("brandId", "1").header("Authorization", AUTH_TOKEN).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.productId", is(35455)))
				.andExpect(jsonPath("$.brandId", is(1))).andExpect(jsonPath("$.priceList", is(2)))
				.andExpect(jsonPath("$.price", is(25.45))).andExpect(jsonPath("$.curr", is("EUR")))
				.andExpect(jsonPath("$.startDate", is("2020-06-14T15:00:00")))
				.andExpect(jsonPath("$.endDate", is("2020-06-14T18:30:00")));
	}

	/**
	 * Test 3: Petición a las 21:00 del día 14 para el producto 35455 y brand 1
	 * (ZARA). Se espera que se retorne la tarifa con PRICE_LIST 1, con precio 35.50
	 * EUR.
	 */
	@Test
	void testTarifaAt21OnJune14() throws Exception {
		mockMvc.perform(get("/tarifas").param("date", "2020-06-14T21:00:00").param("productId", "35455")
				.param("brandId", "1").header("Authorization", AUTH_TOKEN).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.productId", is(35455)))
				.andExpect(jsonPath("$.brandId", is(1))).andExpect(jsonPath("$.priceList", is(1)))
				.andExpect(jsonPath("$.price", is(35.50))).andExpect(jsonPath("$.curr", is("EUR")))
				.andExpect(jsonPath("$.startDate", is("2020-06-14T00:00:00")))
				.andExpect(jsonPath("$.endDate", is("2020-12-31T23:59:59")));
	}

	/**
	 * Test 4: Petición a las 10:00 del día 15 para el producto 35455 y brand 1
	 * (ZARA). Se espera que se retorne la tarifa con PRICE_LIST 3, válida de
	 * "2020-06-15T00:00:00" a "2020-06-15T11:00:00" y precio 30.50 EUR.
	 */
	@Test
	void testTarifaAt10OnJune15() throws Exception {
		mockMvc.perform(get("/tarifas").param("date", "2020-06-15T10:00:00").param("productId", "35455")
				.param("brandId", "1").header("Authorization", AUTH_TOKEN).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.productId", is(35455)))
				.andExpect(jsonPath("$.brandId", is(1))).andExpect(jsonPath("$.priceList", is(3)))
				.andExpect(jsonPath("$.price", is(30.50))).andExpect(jsonPath("$.curr", is("EUR")))
				.andExpect(jsonPath("$.startDate", is("2020-06-15T00:00:00")))
				.andExpect(jsonPath("$.endDate", is("2020-06-15T11:00:00")));
	}

	/**
	 * Test 5: Petición a las 21:00 del día 16 para el producto 35455 y brand 1
	 * (ZARA). Se espera que se retorne la tarifa con PRICE_LIST 4, válida de
	 * "2020-06-15T16:00:00" a "2020-12-31T23:59:59" y precio 38.95 EUR.
	 */
	@Test
	void testTarifaAt21OnJune16() throws Exception {
		mockMvc.perform(get("/tarifas").param("date", "2020-06-16T21:00:00").param("productId", "35455")
				.param("brandId", "1").header("Authorization", AUTH_TOKEN).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.productId", is(35455)))
				.andExpect(jsonPath("$.brandId", is(1))).andExpect(jsonPath("$.priceList", is(4)))
				.andExpect(jsonPath("$.price", is(38.95))).andExpect(jsonPath("$.curr", is("EUR")))
				.andExpect(jsonPath("$.startDate", is("2020-06-15T16:00:00")))
				.andExpect(jsonPath("$.endDate", is("2020-12-31T23:59:59")));
	}

	/**
	 * Casos extremos
	 */

	/**
	 * Test 6: Petición fuera de rango: a las 10:00 del día 12 para el producto
	 * 35455 y brand 1 (ZARA). Se espera que no se encuentre ninguna tarifa y se
	 * retorne un HTTP 404.
	 */
	@Test
	void testTarifaOutOfRange() throws Exception {
		mockMvc.perform(get("/tarifas").param("date", "2020-06-12T10:00:00").param("productId", "35455")
				.param("brandId", "1").header("Authorization", AUTH_TOKEN).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	/**
	 * Test 7: Petición con formato de fecha inválido. Se espera que se retorne un
	 * HTTP 400 Bad Request.
	 */
	@Test
	void testInvalidDateFormat() throws Exception {
		mockMvc.perform(get("/tarifas").param("date", "fecha-invalida").param("productId", "35455")
				.param("brandId", "1").header("Authorization", AUTH_TOKEN).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	/**
	 * Test 8: Petición con parámetro requerido faltante (por ejemplo, falta
	 * 'productId'). Se espera que se retorne un HTTP 400 Bad Request.
	 */
	@Test
	void testMissingRequiredParameter() throws Exception {
		mockMvc.perform(get("/tarifas").param("date", "2020-06-14T10:00:00")
				// Falta el parámetro "productId"
				.param("brandId", "1").header("Authorization", AUTH_TOKEN).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
}
