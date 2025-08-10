CREATE TABLE archana_booking (
    money NUMERIC(38,2),
    archana_id BIGINT,
    created_at TIMESTAMP,
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    archana_name VARCHAR(255),
    prayers VARCHAR(255),
    user_name VARCHAR(255),
    user_unique_id VARCHAR(255)
);

CREATE TABLE cart_item (
    quantity INTEGER,
    total_price NUMERIC(38,2),
    unit_price NUMERIC(38,2),
    added_at TIMESTAMP,
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT,
    user_id BIGINT,
    product_name VARCHAR(255),
    user_unique_id VARCHAR(255)
);

CREATE TABLE product (
    price NUMERIC(38,2),
    stock INTEGER,
    id BIGSERIAL PRIMARY KEY,
    description VARCHAR(255),
    name VARCHAR(255)
);

CREATE TABLE program (
    capacity INTEGER,
    enrolled INTEGER,
    id BIGSERIAL PRIMARY KEY,
    scheduled_time TIMESTAMP,
    description VARCHAR(255),
    name VARCHAR(255)
);

CREATE TABLE program_registration (
    id BIGSERIAL PRIMARY KEY,
    program_id BIGINT,
    registered_at TIMESTAMP,
    user_id BIGINT,
    details VARCHAR(255),
    program_name VARCHAR(255),
    user_name VARCHAR(255),
    user_unique_id VARCHAR(255)
);

CREATE TABLE quiz (
    reward_per_correct NUMERIC(38,2),
    id BIGSERIAL PRIMARY KEY,
    description VARCHAR(255),
    title VARCHAR(255)
);

CREATE TABLE quiz_answers (
    quiz_id BIGINT NOT NULL,
    correct_answers VARCHAR(255)
);

CREATE TABLE quiz_questions (
    quiz_id BIGINT NOT NULL,
    questions VARCHAR(255)
);

CREATE TABLE quiz_user_answers (
    quiz_attempt_id BIGINT NOT NULL,
    user_answers VARCHAR(255)
);

CREATE TABLE quiz_attempt (
    correct_count INTEGER,
    reward_earned NUMERIC(38,2),
    total_questions INTEGER,
    attempted_at TIMESTAMP,
    id BIGSERIAL PRIMARY KEY,
    quiz_id BIGINT,
    user_id BIGINT,
    quiz_title VARCHAR(255),
    user_unique_id VARCHAR(255)
);

CREATE TABLE seva_request (
    money NUMERIC(38,2),
    created_at TIMESTAMP,
    id BIGSERIAL PRIMARY KEY,
    seva_id BIGINT,
    user_id BIGINT,
    details VARCHAR(255),
    seva_name VARCHAR(255),
    user_name VARCHAR(255),
    user_unique_id VARCHAR(255)
);

CREATE TABLE "user" (
    rewards NUMERIC(38,2),
    created_at TIMESTAMP,
    id BIGSERIAL PRIMARY KEY,
    mobile_number VARCHAR(255) UNIQUE,
    name VARCHAR(255),
    unique_id VARCHAR(255)
);