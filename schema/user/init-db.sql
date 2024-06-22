START TRANSACTION;

CREATE SCHEMA IF NOT EXISTS "users";

CREATE TYPE users.role_type AS ENUM ('ADMIN', 'TEACHER', 'STUDENT');

CREATE TABLE users.users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    name VARCHAR(100),
		password VARCHAR(255) NOT NULL,
    role users.role_type DEFAULT 'STUDENT'
	);

CREATE UNIQUE INDEX idx_unique_username ON users.users (username);

COMMIT;
