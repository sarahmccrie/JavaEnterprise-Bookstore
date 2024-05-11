CREATE TABLE `user` (
    userId BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(45) UNIQUE NOT NULL,
    encryptedPassword VARCHAR(95) NOT NULL,
    enabled BIT NOT NULL
);


CREATE TABLE role (
    roleId BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
  	roleName VARCHAR(30) NOT NULL UNIQUE 
);


CREATE TABLE user_role (
    userId BIGINT NOT NULL,
    roleId BIGINT NOT NULL,
    CONSTRAINT FK_userRole_user FOREIGN KEY (userId) REFERENCES `user`(userId),
    CONSTRAINT FK_userRole_role FOREIGN KEY (roleId) REFERENCES role(roleId),
    PRIMARY KEY (userId, roleId)
);

CREATE TABLE author (
    authorId BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    firstName VARCHAR(45) NOT NULL,
    lastName VARCHAR(45) NOT NULL
);

CREATE TABLE book (
    bookId BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    title VARCHAR(45) NOT NULL,
    categoryName VARCHAR(45)
);

CREATE TABLE book_author (
    bookId BIGINT,
    authorId BIGINT,
    PRIMARY KEY (bookId, authorId),
    FOREIGN KEY (bookId) REFERENCES book(bookId),
    FOREIGN KEY (authorId) REFERENCES author(authorId)
);
