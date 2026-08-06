CREATE TABLE IF NOT EXISTS payment_order_seq (next_val BIGINT) ENGINE=InnoDB;
INSERT INTO payment_order_seq VALUES (1);

CREATE TABLE IF NOT EXISTS payment_order (
    id BIGINT NOT NULL,
    amount BIGINT NOT NULL,
    status VARCHAR(255) NOT NULL DEFAULT 'PENDING',
    payment_method VARCHAR(255) NOT NULL,
    payment_link_id VARCHAR(255),
    user_id BIGINT NOT NULL,
    booking_id BIGINT NOT NULL,
    saloon_id BIGINT NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;
