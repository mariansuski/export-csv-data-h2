CREATE TABLE tags
    (
        id INTEGER PRIMARY KEY AUTO_INCREMENT,
        user_id INTEGER,
        movie_id INTEGER,
        tag VARCHAR(255),
        timestamp_value VARCHAR(255)
    );
