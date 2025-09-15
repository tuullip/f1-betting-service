INSERT INTO customer (id, first_name, last_name, email)
VALUES (1, 'John', 'Doe', 'john.doe@example.com'),
       (2, 'Jane', 'Smith', 'jane.smith@example.com');

INSERT INTO customer_wallet (id, customer_id, balance, currency)
VALUES (1, 1, 100.00, 'EUR'),
       (2, 2, 250.50, 'EUR');
