START TRANSACTION;

CREATE SCHEMA IF NOT EXISTS "classrooms";

CREATE TABLE 
	classrooms.grades (
    grade_id SERIAL PRIMARY KEY,
    grade_level VARCHAR(50) UNIQUE NOT NULL
);


CREATE TABLE 
	classrooms.classrooms (
    classroom_id SERIAL PRIMARY KEY,
    classroom_name VARCHAR(255),
    grade_id INT,
    CONSTRAINT fk_grade FOREIGN KEY (grade_id) REFERENCES classrooms.grades(grade_id)
);

CREATE UNIQUE INDEX idx_unique_classroom_name_grade_id ON classrooms.classrooms (classroom_name, grade_id);

COMMIT;
