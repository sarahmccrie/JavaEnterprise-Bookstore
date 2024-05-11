
INSERT INTO `user` (userId, email, encryptedPassword, enabled) VALUES (1, 'mccries@sheridancollege.ca', '$2a$10$vzq5ZOJIlVYK0x.Ww9C7zeYkWSIAAnuVEqAgr1wij5.WnFdtZ3CiW', 1);
INSERT INTO `user` (userId, email, encryptedPassword, enabled) VALUES (2, 'john@sheridancollege.ca', '$2a$10$vzq5ZOJIlVYK0x.Ww9C7zeYkWSIAAnuVEqAgr1wij5.WnFdtZ3CiW', 1);
INSERT INTO `user` (userId, email, encryptedPassword, enabled) VALUES (3, 'eric@gmail.com', '$2a$10$vzq5ZOJIlVYK0x.Ww9C7zeYkWSIAAnuVEqAgr1wij5.WnFdtZ3CiW', 1);
INSERT INTO `user` (userId, email, encryptedPassword, enabled) VALUES (4, 'georgia@live.ca', '$2a$10$vzq5ZOJIlVYK0x.Ww9C7zeYkWSIAAnuVEqAgr1wij5.WnFdtZ3CiW', 1);


INSERT INTO `role` (roleName)
VALUES ('ROLE_USER');
INSERT INTO `role` (roleName)
VALUES ('ROLE_ADMIN');


INSERT INTO user_role (userId, roleId) VALUES (1, 2);
INSERT INTO user_role (userId, roleId) VALUES (2, 2);
INSERT INTO user_role (userId, roleId) VALUES (3, 1);
INSERT INTO user_role (userId, roleId) VALUES (4, 1);


INSERT INTO author (firstName, lastName) VALUES ('Suzanne', 'Collins');
INSERT INTO author (firstName, lastName) VALUES ('Jack', 'Johnson');
INSERT INTO author (firstName, lastName) VALUES ('Erica', 'Robinson');


INSERT INTO book (title, categoryName) VALUES ('To Kill a Mockingbird', 'Historic');
INSERT INTO book (title, categoryName) VALUES ('Jokes and Such', 'Comedy');
INSERT INTO book (title, categoryName) VALUES ('Scary Stories', 'Horror');


INSERT INTO book_author (bookId, authorId) VALUES (1, 1);
INSERT INTO book_author (bookId, authorId) VALUES (2, 2);
INSERT INTO book_author (bookId, authorId) VALUES (3, 3);

