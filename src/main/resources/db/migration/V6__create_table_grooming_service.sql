CREATE TABLE grooming_service(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    service_type VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    description VARCHAR(255) NOT NULL,
    date_time DATETIME NOT NULL,
    pet_id BIGINT NOT NULL,
    CONSTRAINT fk_pet
        FOREIGN KEY (pet_id)
        REFERENCES pet(id)
);