CREATE TABLE teachers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    lastname VARCHAR(50),
    gender VARCHAR(50),
    subject VARCHAR(50)
);

CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    lastname VARCHAR(50),
    gender VARCHAR(50),
    class VARCHAR(50)
);

CREATE TABLE teachers_students (
    teacher_id INT,
    student_id INT,
    FOREIGN KEY (teacher_id) REFERENCES teachers (id),
    FOREIGN KEY (student_id) REFERENCES students (id)
);

SELECT t.* FROM teachers_students AS ts
INNER JOIN students s ON s.id = ts.student_id
INNER JOIN teachers t ON t.id = ts.teacher_id
WHERE s.name = 'George';