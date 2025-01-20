CREATE TABLE answers (
    id BIGINT NOT NULL AUTO_INCREMENT,
    message VARCHAR(256) NOT NULL UNIQUE,
    creation_date DATETIME NOT NULL,
    topic_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    solution BOOLEAN DEFAULT FALSE,

    PRIMARY KEY(id),
    CONSTRAINT fk_answers_topic_id FOREIGN KEY (topic_id) REFERENCES topics(id),
    CONSTRAINT fk_answers_author_id FOREIGN KEY (author_id) REFERENCES users(id)
);
