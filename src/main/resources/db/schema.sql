CREATE TABLE IF NOT EXISTS app_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS bidding_series (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    series_name VARCHAR(255) NOT NULL,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    status VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS bid (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    plate_number VARCHAR(255) NOT NULL,
    amount DOUBLE NOT NULL,
    timestamp TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES app_user(id)
);

CREATE TABLE IF NOT EXISTS payment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    bid_id BIGINT,
    amount DOUBLE NOT NULL,
    type VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    timestamp TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES app_user(id),
    FOREIGN KEY (bid_id) REFERENCES bid(id)
);