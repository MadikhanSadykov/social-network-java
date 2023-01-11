CREATE TABLE IF NOT EXISTS local
(
        id BIGINT NOT NULL AUTO_INCREMENT,
        name VARCHAR(255),
        PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user
(
        id BIGINT NOT NULL AUTO_INCREMENT,
        email VARCHAR(64) NOT NULL,
        password VARCHAR(3000) NOT NULL,
        PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS role
(
        id BIGINT NOT NULL AUTO_INCREMENT,
        name VARCHAR(255),
        PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_role
(
        user_id BIGINT NOT NULL,
        role_id BIGINT NOT NULL,
        PRIMARY KEY (user_id, role_id),
        CONSTRAINT user_role_role_id_fk
            FOREIGN KEY (role_id)
                REFERENCES role (id)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION,
        CONSTRAINT user_role_user_id_fk
            FOREIGN KEY (user_id)
                REFERENCES user (id)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS status
(
        local_id BIGINT NOT NULL,
        id BIGINT NOT NULL,
        name VARCHAR(255),
        PRIMARY KEY (id, local_id),
        CONSTRAINT status_local_id_fk
            FOREIGN KEY (local_id)
                REFERENCES local (id)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION
);

CREATE INDEX idx_status_local_id ON status(id, local_id);

CREATE TABLE IF NOT EXISTS profile
(
         id BIGINT NOT NULL AUTO_INCREMENT,
         bio TEXT,
         birth_date DATE,
         created_date DATETIME(6),
         first_name VARCHAR(16) NOT NULL,
         image_url VARCHAR(255),
         last_name VARCHAR(16) NOT NULL,
         phone_number VARCHAR(20),
         updated_date DATETIME(6),
         user_id BIGINT,
         username VARCHAR(32) NOT NULL,
         PRIMARY KEY (ID),
         CONSTRAINT profile_user_id_fk
             FOREIGN KEY (user_id)
                 REFERENCES user (id)
                 ON DELETE CASCADE
                 ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS post
(
        id BIGINT NOT NULL AUTO_INCREMENT,
        caption VARCHAR(255),
        image_url VARCHAR(255),
        location VARCHAR(255),
        status_id BIGINT,
        title VARCHAR(255),
        updated_date DATETIME(6),
        profile_id BIGINT,
        PRIMARY KEY (id),
        CONSTRAINT post_profile_id_fk
            FOREIGN KEY (profile_id)
                REFERENCES profile (id)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION,
        CONSTRAINT post_status_id_fk
            FOREIGN KEY (status_id)
                REFERENCES status (id)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS comment
(
         id BIGINT NOT NULL AUTO_INCREMENT,
         created_date DATETIME(6),
         message TEXT,
         updated_date DATETIME(6),
         username VARCHAR(32) NOT NULL,
         post_id BIGINT,
         profile_id BIGINT,
         status_id BIGINT,
         PRIMARY KEY (id),
         CONSTRAINT comment_post_id_fk
             FOREIGN KEY (post_id)
                 REFERENCES post (id)
                 ON DELETE NO ACTION
                 ON UPDATE NO ACTION,
         CONSTRAINT comment_profile_id_fk
             FOREIGN KEY (profile_id)
                 REFERENCES profile (id)
                 ON DELETE NO ACTION
                 ON UPDATE NO ACTION,
         CONSTRAINT comment_status_id_fk
             FOREIGN KEY (status_id)
                 REFERENCES status (id)
                 ON DELETE NO ACTION
                 ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `like`
(
        post_id BIGINT NOT NULL,
        profile_id BIGINT NOT NULL,
        PRIMARY KEY (post_id, profile_id),
        CONSTRAINT LIKE_POST_ID_FK
            FOREIGN KEY (post_id)
                REFERENCES post (id)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION,
        CONSTRAINT like_profile_id_fk
            FOREIGN KEY (profile_id)
                REFERENCES profile (id)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS followership
(
        follower_id BIGINT NOT NULL,
        following_id BIGINT NOT NULL,
        PRIMARY KEY (following_id, follower_id),
        CONSTRAINT followership_follower_id_fk
            FOREIGN KEY (follower_id)
                REFERENCES profile (id)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION,
        CONSTRAINT followership_following_id_fk
            FOREIGN KEY (following_id)
                REFERENCES profile (id)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS friendship
(
        profile_id BIGINT NOT NULL,
        friend_id BIGINT NOT NULL,
        PRIMARY KEY (profile_id, friend_id),
        CONSTRAINT friendship_profile_id_fk
            FOREIGN KEY (profile_id)
                REFERENCES profile (id)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION,
        CONSTRAINT friendship_friend_id_fk
            FOREIGN KEY (friend_id)
                REFERENCES profile (id)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS profile_banned_list
(
        id BIGINT NOT NULL AUTO_INCREMENT,
        reason VARCHAR(255),
        profile_id BIGINT,
        PRIMARY KEY (id),
        CONSTRAINT profile_banned_list_profile_id_fk
            FOREIGN KEY (profile_id)
                REFERENCES profile (id)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS post_banned_list
(
        id BIGINT NOT NULL AUTO_INCREMENT,
        reason VARCHAR(255),
        post_id BIGINT,
        PRIMARY KEY (id),
        CONSTRAINT post_banned_list_post_id_fk
            FOREIGN KEY (post_id)
                REFERENCES post (id)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION
);