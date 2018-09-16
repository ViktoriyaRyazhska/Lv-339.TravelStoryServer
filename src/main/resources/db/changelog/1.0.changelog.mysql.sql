--liquibase formatted sql

--changeset Рома:1537132063285-1
CREATE TABLE chats (id BIGINT AUTO_INCREMENT NOT NULL, chat_name VARCHAR(255) NULL, CONSTRAINT PK_CHATS PRIMARY KEY (id));

--changeset Рома:1537132063285-2
CREATE TABLE chats_connected_users (connected_chats_id BIGINT NOT NULL, connected_users_id BIGINT NOT NULL);

--changeset Рома:1537132063285-3
CREATE TABLE comments (id BIGINT AUTO_INCREMENT NOT NULL, comment_massage VARCHAR(255) NULL, created_at datetime NOT NULL, travel_story_id BIGINT NULL, user_id BIGINT NOT NULL, CONSTRAINT PK_COMMENTS PRIMARY KEY (id));

--changeset Рома:1537132063285-4
CREATE TABLE likes (id BIGINT AUTO_INCREMENT NOT NULL, travel_story_id BIGINT NULL, user_id BIGINT NULL, CONSTRAINT PK_LIKES PRIMARY KEY (id));

--changeset Рома:1537132063285-5
CREATE TABLE messages (id BIGINT AUTO_INCREMENT NOT NULL, created_date_time datetime NOT NULL, message_content VARCHAR(255) NULL, chat_id BIGINT NOT NULL, user_id BIGINT NOT NULL, CONSTRAINT PK_MESSAGES PRIMARY KEY (id));

--changeset Рома:1537132063285-6
CREATE TABLE travel_story (id BIGINT AUTO_INCREMENT NOT NULL, created_date date NULL, `description` VARCHAR(255) NULL, travel_story_status VARCHAR(255) NULL, updated_date date NULL, user_owner_id BIGINT NULL, CONSTRAINT PK_TRAVEL_STORY PRIMARY KEY (id));

--changeset Рома:1537132063285-7
CREATE TABLE users (id BIGINT AUTO_INCREMENT NOT NULL, date_of_birth date NULL, email VARCHAR(255) NULL, first_name VARCHAR(255) NULL, last_name VARCHAR(255) NULL, last_update_date datetime NULL, password VARCHAR(25) NOT NULL, registration_date datetime NULL, user_role VARCHAR(255) NULL, user_status VARCHAR(255) NULL, CONSTRAINT PK_USERS PRIMARY KEY (id));

--changeset Рома:1537132063285-8
CREATE INDEX FK64w44ngcpqp99ptcb9werdfmb ON messages(chat_id);

--changeset Рома:1537132063285-9
CREATE INDEX FK8h3k1tdr94oyu0ujhew2h0vtl ON chats_connected_users(connected_chats_id);

--changeset Рома:1537132063285-10
CREATE INDEX FK8omq0tc18jd43bu5tjh6jvraq ON comments(user_id);

--changeset Рома:1537132063285-11
CREATE INDEX FKc7w5gq0ixe0iucj4jw2tn9ccf ON chats_connected_users(connected_users_id);

--changeset Рома:1537132063285-12
CREATE INDEX FKi3ktl3l5t983vs4jd9ieluwh3 ON likes(travel_story_id);

--changeset Рома:1537132063285-13
CREATE INDEX FKl8qp627fwhfrreskhvhoivihe ON travel_story(user_owner_id);

--changeset Рома:1537132063285-14
CREATE INDEX FKnvx9seeqqyy71bij291pwiwrg ON likes(user_id);

--changeset Рома:1537132063285-15
CREATE INDEX FKpsmh6clh3csorw43eaodlqvkn ON messages(user_id);

--changeset Рома:1537132063285-16
CREATE INDEX FKrvd6qs9hgg3q2cip1cilfwdbq ON comments(travel_story_id);

