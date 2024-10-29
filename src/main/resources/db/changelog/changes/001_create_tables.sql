-- 001_create_tables.sql
CREATE TABLE recipes.recipe (
    uuid        UUID PRIMARY KEY    NOT NULL,
    title       VARCHAR(255)        NOT NULL,
    preparation TEXT                NOT NULL
);

CREATE TABLE recipes.ingredient (
    uuid        UUID PRIMARY KEY    NOT NULL,
    name        VARCHAR(255)        NOT NULL,
    unit        VARCHAR(100)        NOT NULL,
    quantity    DOUBLE PRECISION    NOT NULL,
    recipe_id   UUID                NOT NULL,
    FOREIGN KEY (recipe_id) REFERENCES recipes.recipe (uuid)
);