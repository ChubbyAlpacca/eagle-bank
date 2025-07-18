CREATE TABLE ADDRESS (
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    USER_ID VARCHAR(255) NOT NULL,
    LINE_1 VARCHAR(255) NOT NULL,
    LINE_2 VARCHAR(255),
    LINE_3 VARCHAR(255),
    TOWN VARCHAR(255) NOT NULL,
    COUNTY VARCHAR(255) NOT NULL,
    POST_CODE VARCHAR(20) NOT NULL,
    CREATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE (USER_ID, LINE_1, TOWN, COUNTY, POST_CODE),
    CONSTRAINT FK_USER_ADDRESS FOREIGN KEY (USER_ID)
        REFERENCES USERS(USER_ID) ON DELETE CASCADE
);
