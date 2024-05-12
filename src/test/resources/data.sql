INSERT INTO address (longitude, latitude)
VALUES
    (12.4924, 41.8902),
    (2.3522, 48.8566),
    (1.1276, 51.5074),
    (56.4524, 27.1234),
    (8.7522, 74.8566)
;

INSERT INTO clients (name, lastName, address_id)
VALUES
    ('John', 'Doe', 1),
    ('Jane', 'Smith', 2),
    ('Michael', 'Johnson', 3)
;

INSERT INTO products (name, price)
VALUES
    ('Croissant', 8.50),
    ('Rye bread', 5.00)
;

INSERT INTO employees (name, lastName, role, address_id)
VALUES
    ('Adam', 'Smith', 'Manager', 4),
    ('Michael', 'Jordan', 'Customer Service', 5)
;

INSERT INTO orders (product_id, quantity)
VALUES
    (1, 1),
    (2, 2),
    (2, 3)
;