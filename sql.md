-- PostgreSQL Schema for Ram Krishna Application
-- This schema defines all tables required for the backend, including users, pledges, prayers, products, programs, quizzes, seva requests, and related entities.
-- Each table and field is documented for clarity and maintainability.

-- Table: archana_booking
-- Stores information about archana bookings made by users.
CREATE TABLE archana_booking (
    money NUMERIC(38,2),                -- Amount of money for the archana
    archana_id BIGINT,                  -- Reference to archana type/id
    created_at TIMESTAMP,               -- Timestamp when the booking was created
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,                     -- Reference to the user who booked
    archana_name VARCHAR(255),          -- Name of the archana
    prayers VARCHAR(255),               -- Prayers associated with the archana
    user_name VARCHAR(255),             -- Name of the user
    user_unique_id VARCHAR(255)         -- Unique ID of the user
);

-- Table: cart_item
-- Stores items added to a user's cart.
CREATE TABLE cart_item (
    quantity INTEGER,                   -- Quantity of the product
    total_price NUMERIC(38,2),          -- Total price for this cart item
    unit_price NUMERIC(38,2),           -- Price per unit
    added_at TIMESTAMP,                 -- When the item was added to the cart
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT,                  -- Reference to the product
    user_id BIGINT,                     -- Reference to the user
    product_name VARCHAR(255),          -- Name of the product
    user_unique_id VARCHAR(255)         -- Unique ID of the user
);

-- Table: product
-- Stores product catalog information.
CREATE TABLE product (
    price NUMERIC(38,2),                -- Price of the product
    stock INTEGER,                      -- Stock available
    id BIGSERIAL PRIMARY KEY,
    description VARCHAR(255),           -- Product description
    name VARCHAR(255)                   -- Product name
);

-- Table: program
-- Stores information about programs/events.
CREATE TABLE program (
    capacity INTEGER,                   -- Maximum capacity
    enrolled INTEGER,                   -- Number of enrolled users
    id BIGSERIAL PRIMARY KEY,
    scheduled_time TIMESTAMP,           -- Scheduled time of the program
    description VARCHAR(255),           -- Program description
    name VARCHAR(255)                   -- Program name
);

-- Table: program_registration
-- Stores user registrations for programs.
CREATE TABLE program_registration (
    id BIGSERIAL PRIMARY KEY,
    program_id BIGINT,                  -- Reference to the program
    registered_at TIMESTAMP,            -- When the user registered
    user_id BIGINT,                     -- Reference to the user
    details VARCHAR(255),               -- Additional details
    program_name VARCHAR(255),          -- Name of the program
    user_name VARCHAR(255),             -- Name of the user
    user_unique_id VARCHAR(255)         -- Unique ID of the user
);

-- Table: quiz
-- Stores quiz metadata.
CREATE TABLE quiz (
    reward_per_correct NUMERIC(38,2),   -- Reward per correct answer
    id BIGSERIAL PRIMARY KEY,
    description VARCHAR(255),           -- Quiz description
    title VARCHAR(255)                  -- Quiz title
);

-- Table: quiz_answers
-- Stores correct answers for each quiz.
CREATE TABLE quiz_answers (
    quiz_id BIGINT NOT NULL,            -- Reference to the quiz
    correct_answers VARCHAR(255)        -- Correct answers (comma-separated or JSON)
);

-- Table: quiz_questions
-- Stores questions for each quiz.
CREATE TABLE quiz_questions (
    quiz_id BIGINT NOT NULL,            -- Reference to the quiz
    questions VARCHAR(255)              -- Questions (comma-separated or JSON)
);

-- Table: quiz_user_answers
-- Stores user answers for quiz attempts.
CREATE TABLE quiz_user_answers (
    quiz_attempt_id BIGINT NOT NULL,    -- Reference to the quiz attempt
    user_answers VARCHAR(255)           -- User's answers (comma-separated or JSON)
);

-- Table: quiz_attempt
-- Stores each attempt a user makes on a quiz.
CREATE TABLE quiz_attempt (
    correct_count INTEGER,              -- Number of correct answers
    reward_earned NUMERIC(38,2),        -- Reward earned for this attempt
    total_questions INTEGER,            -- Total number of questions
    attempted_at TIMESTAMP,             -- When the attempt was made
    id BIGSERIAL PRIMARY KEY,
    quiz_id BIGINT,                     -- Reference to the quiz
    user_id BIGINT,                     -- Reference to the user
    quiz_title VARCHAR(255),            -- Title of the quiz
    user_unique_id VARCHAR(255)         -- Unique ID of the user
);

-- Table: seva_request
-- Stores seva (service) requests made by users.
CREATE TABLE seva_request (
    money NUMERIC(38,2),                -- Amount of money for seva
    created_at TIMESTAMP,               -- When the seva was requested
    id BIGSERIAL PRIMARY KEY,
    seva_id BIGINT,                     -- Reference to seva type/id
    user_id BIGINT,                     -- Reference to the user
    details VARCHAR(255),               -- Additional details
    seva_name VARCHAR(255),             -- Name of the seva
    user_name VARCHAR(255),             -- Name of the user
    user_unique_id VARCHAR(255)         -- Unique ID of the user
);

-- Table: user
-- Stores user account and profile information.
CREATE TABLE "user" (
    rewards NUMERIC(38,2),              -- Total rewards earned by the user
    created_at TIMESTAMP,               -- When the user was created
    id BIGSERIAL PRIMARY KEY,
    mobile_number VARCHAR(255) UNIQUE,  -- User's mobile number (unique)
    name VARCHAR(255),                  -- Name of the user
    unique_id VARCHAR(255)              -- Unique ID for the user
);

-- Table: pledge
-- Stores user pledges (commitments).
CREATE TABLE pledge (
    id SERIAL PRIMARY KEY,
    unique_id VARCHAR(255),             -- Unique ID of the user
    sacred_commitment VARCHAR(255),     -- Sacred commitment text
    personal_commitment VARCHAR(255)    -- Personal commitment text
);

-- Table: prayer
-- Stores user-submitted prayers.
CREATE TABLE prayer (
    id SERIAL PRIMARY KEY,
    unique_id VARCHAR(255),             -- Unique ID of the user
    prayer VARCHAR(255)                 -- Prayer text
);