CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       cpf VARCHAR(11) NOT NULL UNIQUE,
                       name VARCHAR(150) NOT NULL,
                       birthdate DATE NOT NULL,
                       login VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       address TEXT NOT NULL
);

CREATE TABLE product (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         price NUMERIC(19,2) NOT NULL
);

CREATE TABLE stock (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       product_sku BIGINT NOT NULL,
                       quantity INT NOT NULL CHECK (quantity >= 0),
                       FOREIGN KEY (product_sku) REFERENCES product(id)
);


CREATE TABLE orders (
                        id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                        customer_id UUID NOT NULL,
                        payment_id UUID,
                        status VARCHAR(50) NOT NULL,
                        payment_type VARCHAR(50) NOT NULL
);

CREATE TABLE product_buyed (
        id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
        order_id UUID,
        sku BIGINT NOT NULL,
        quantity INT,
        FOREIGN KEY (order_id) REFERENCES orders(id)
);
