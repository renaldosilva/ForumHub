CREATE TABLE topics (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(256) NOT NULL UNIQUE,
    message VARCHAR(256) NOT NULL UNIQUE,
    creation_date DATETIME NOT NULL,
    status VARCHAR(50) NOT NULL,
    author_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT fk_topics_author_id FOREIGN KEY (author_id) REFERENCES users(id),
    CONSTRAINT fk_topics_course_id FOREIGN KEY (course_id) REFERENCES courses(id)
);
