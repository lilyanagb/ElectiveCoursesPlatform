-- Таблица за ролите
CREATE TABLE role (
    id INT PRIMARY KEY,
    name VARCHAR(45) NOT NULL
);

-- Таблица за потребителите (преподаватели и студенти)
CREATE TABLE user (
    id INT PRIMARY KEY,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    role_id INT,
    FOREIGN KEY (role_id) REFERENCES role(id)
);

-- Таблица за преподавателите
CREATE TABLE teacher (
    id INT PRIMARY KEY,
    user_id INT,
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

-- Таблица за студентите
CREATE TABLE student (
    id INT PRIMARY KEY,
    user_id INT,
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

-- Таблица за курсовете
CREATE TABLE course (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    teacher_id INT,
    FOREIGN KEY (teacher_id) REFERENCES teacher(id)
);

-- Таблица за записванията на студенти в курсове
CREATE TABLE enrollment (
    id INT PRIMuserARY KEY,
    student_id INT,
    course_id INT,
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (course_id) REFERENCES course(id)
);