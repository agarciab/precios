-- data.sql
-- Descripción de los campos:
-- BRAND_ID: Foreign key de la cadena del grupo (1 = ZARA).
-- START_DATE, END_DATE: Rango de fechas en el que aplica la tarifa indicada.
-- PRICE_LIST: Identificador de la tarifa de precios aplicable.
-- PRODUCT_ID: Identificador del código de producto.
-- PRIORITY: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rango de fechas, se aplica la de mayor prioridad (mayor valor numérico).
-- PRICE: Precio final de venta.
-- CURR: Código ISO de la moneda.

--DROP TABLE IF EXISTS PRICES;

/* CREATE TABLE PRICES (
    id UUID NOT NULL DEFAULT RANDOM_UUID(),
    BRAND_ID BIGINT NOT NULL,              
    START_DATE TIMESTAMP NOT NULL,         
    END_DATE TIMESTAMP NOT NULL,           
    PRICE_LIST INT NOT NULL,               
    PRODUCT_ID BIGINT NOT NULL,            
    PRIORITY INT NOT NULL,                 
    PRICE DECIMAL(10,2) NOT NULL,          
    CURR VARCHAR(3) NOT NULL,              
    PRIMARY KEY (id)
);*/

INSERT INTO PRICES (ID, BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR)
VALUES (RANDOM_UUID(), 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR');

INSERT INTO PRICES (ID, BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR)
VALUES (RANDOM_UUID(), 1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR');

INSERT INTO PRICES (ID, BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR)
VALUES (RANDOM_UUID(), 1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR');

INSERT INTO PRICES (ID, BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR)
VALUES (RANDOM_UUID(), 1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR');

--commit;
