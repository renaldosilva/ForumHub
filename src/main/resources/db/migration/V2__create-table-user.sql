CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    profile_id BIGINT,

    PRIMARY KEY(id),
    CONSTRAINT fk_profiles_user_id FOREIGN KEY (profile_id) REFERENCES profiles(id)
);