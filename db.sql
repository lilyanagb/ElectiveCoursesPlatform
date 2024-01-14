-- Таблица за ролите
CREATE TABLE role (
    id INT PRIMARY KEY,
    name VARCHAR(45) NOT NULL
);

-- Таблица за потребителите (преподаватели и студенти)
CREATE TABLE user (
    id INT PRIMARY KEY,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(45) NOT NULL,
    role_id INT,
    FOREIGN KEY (role_id) REFERENCES role(id)
);

-- Таблица за курсовете
CREATE TABLE course (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(45) NOT NULL,
    credits INT NOT NULL
);

-- Таблица за записванията на студенти в курсове
CREATE TABLE enrollment (
    id INT PRIMARY KEY,
    user_id INT,
    course_id INT,
    enrollment_type_id INT,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (course_id) REFERENCES course(id),
    FOREIGN KEY (enrollment_type_id) REFERENCES enrollment_type(id)
);

CREATE TABLE enrollment_type (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);