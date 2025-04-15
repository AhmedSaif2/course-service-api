-- Insert authors
INSERT INTO author (name) VALUES ('Alice Johnson');
INSERT INTO author (name) VALUES ('Bob Smith');

-- Insert courses
INSERT INTO course (name, description) VALUES ('Java Basics', 'Introduction to Java programming');
INSERT INTO course (name, description) VALUES ('Spring Boot', 'Building APIs with Spring Boot');
INSERT INTO course (name, description) VALUES ('.Net', 'Building APIs with .Net');
INSERT INTO course (name, description) VALUES ('Data Structure & Algorithms', 'Creating efficient algorithms');

INSERT INTO course_author (course_id, author_id) VALUES (0, 0);
INSERT INTO course_author (course_id, author_id) VALUES (1, 1);

-- Insert ratings
INSERT INTO rating (number, course_id) VALUES (5, 0);
INSERT INTO rating (number, course_id) VALUES (4, 0);
INSERT INTO rating (number, course_id) VALUES (3, 1);

-- Insert assessments
INSERT INTO assessment (title, course_id) VALUES ('Java Quiz 1', 0);
INSERT INTO assessment (title, course_id) VALUES ('Spring Boot Assignment', 1);