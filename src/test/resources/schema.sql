CREATE TABLE IF NOT EXISTS address
(
    id        SERIAL PRIMARY KEY NOT NULL,
    longitude DOUBLE PRECISION,
    latitude  DOUBLE PRECISION

);

CREATE TABLE IF NOT EXISTS products
(
    id   SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    price DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS orders
(
    id         SERIAL PRIMARY KEY NOT NULL,
    product_id INT references products (id),
    quantity   INT
);

CREATE TABLE IF NOT EXISTS clients
(
    id         SERIAL PRIMARY KEY NOT NULL,
    name       VARCHAR(50),
    lastName   VARCHAR(50),
    address_id INT references address (id),
    order_id   INT references orders (id)
);

CREATE TABLE IF NOT EXISTS employees
(
    id         SERIAL PRIMARY KEY NOT NULL,
    name       VARCHAR(50),
    lastName   VARCHAR(50),
    address_id INT references address (id),
    role VARCHAR(50)
);