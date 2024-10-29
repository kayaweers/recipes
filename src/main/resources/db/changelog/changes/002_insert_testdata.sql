-- 002_insert_testdata.sql

INSERT INTO recipes.recipe (uuid, title, preparation) VALUES
('a4247127-2183-45d4-8712-998b69360047', 'Pasta pesto', 'Kook de pasta. Snij de groenten. Bak de groenten. Roer de pesto en groenten door de pasta.'),
('f6a16ff7-4a31-11eb-be7b-8344edc8f36b', 'Tomatensoep', 'Snij de tomaten. Fruit de uit en knoflook. Bak de tomaten. Voeg water toe en pureer.');

INSERT INTO recipes.ingredient (uuid, name, unit, quantity, recipe_id) VALUES
('2b54c43f-2b6d-4c92-aaaa-8a8ee0cfa0b1', 'tomaat', 'PIECE', 5, 'f6a16ff7-4a31-11eb-be7b-8344edc8f36b'),
('8875843f-6725-46d0-8acc-8a57c5533b52', 'pasta', 'G', 140, 'a4247127-2183-45d4-8712-998b69360047'),
('80ecda61-f144-4008-aac8-d4c78bdf1aa9', 'pesto', 'ML', 100, 'a4247127-2183-45d4-8712-998b69360047');