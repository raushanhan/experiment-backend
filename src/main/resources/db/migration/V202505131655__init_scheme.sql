CREATE TABLE users (
                       id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       username VARCHAR(100) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password_hash TEXT NOT NULL,
                       created_at TIMESTAMP DEFAULT now()
);

CREATE TABLE notes (
                       id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       title VARCHAR(255),
                       content TEXT,
                       latitude DOUBLE PRECISION,
                       longitude DOUBLE PRECISION,
                       image_url TEXT,
                       created_at TIMESTAMP DEFAULT now(),
                       updated_at TIMESTAMP DEFAULT now(),
                       user_id INTEGER REFERENCES users(id) ON DELETE CASCADE
);
