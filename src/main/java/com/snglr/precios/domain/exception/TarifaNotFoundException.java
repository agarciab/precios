package com.snglr.precios.domain.exception;

/**
 * Excepción de dominio que se lanza cuando no se encuentra ninguna tarifa
 * para los criterios de búsqueda indicados.
 */
public class TarifaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
     * Crea una nueva excepción con el mensaje proporcionado.
     *
     * @param message el mensaje descriptivo de la excepción.
     */
    public TarifaNotFoundException(String message) {
        super(message);
    }
}
