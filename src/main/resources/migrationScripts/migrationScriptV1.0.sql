CREATE TABLE IF NOT EXISTS gameachievements.game (
    id CHAR(64) PRIMARY KEY,
    display_name VARCHAR(50) NOT NULL,
    created TIMESTAMP NOT NULL,
    updated TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS gameachievements.achievement (
    id CHAR(64) PRIMARY KEY,
    display_name VARCHAR(100) NOT NULL,
    description VARCHAR(500) NOT NULL,
    icon VARCHAR(255),
    display_order INT,
    created TIMESTAMP NOT NULL,
    updated TIMESTAMP NOT NULL,
    game_id CHAR(64),
    FOREIGN KEY(game_id) REFERENCES gameachievements.game(id) ON DELETE CASCADE
);