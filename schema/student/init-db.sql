START TRANSACTION;

CREATE SCHEMA IF NOT EXISTS "students";

CREATE TABLE 
	students.students (
    student_id SERIAL PRIMARY KEY,
    student_name VARCHAR(255),
    student_dob DATE,
    student_email VARCHAR(255),
    student_gender VARCHAR(50),
    student_address VARCHAR(255),
    classroom_id INT
);

COMMIT;
