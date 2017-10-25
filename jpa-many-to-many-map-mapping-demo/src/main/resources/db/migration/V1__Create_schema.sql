CREATE SEQUENCE film_actor_sequence;


CREATE TABLE actor (
  id         INTEGER DEFAULT nextval( 'film_actor_sequence' :: REGCLASS ) NOT NULL,
  uuid       VARCHAR(255),
  first_name VARCHAR(255),
  last_name  VARCHAR(255),
  PRIMARY KEY (id)
);
CREATE TABLE customer (
  id         INTEGER DEFAULT nextval( 'film_actor_sequence' :: REGCLASS )   NOT NULL,
  uuid       VARCHAR(255)                                                   NOT NULL,
  first_name VARCHAR(255),
  last_name  VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE film (
  id     INTEGER DEFAULT nextval( 'film_actor_sequence' :: REGCLASS )  NOT NULL,
  uuid   VARCHAR(255)                                                  NOT NULL,
  length BIGINT,
  name   VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE film_to_actor (
  film_id        INTEGER NOT NULL,
  actor_id       INTEGER NOT NULL,
  main_character BOOLEAN NOT NULL,
  first_name     VARCHAR(255),
  last_name      VARCHAR(255),
  PRIMARY KEY (film_id, actor_id),
  FOREIGN KEY (film_id) REFERENCES film (id),
  FOREIGN KEY (actor_id) REFERENCES actor (id)
);


CREATE TABLE rental (
  id          INTEGER DEFAULT nextval( 'film_actor_sequence' :: REGCLASS )  NOT NULL,
  uuid        VARCHAR(255)                                                  NOT NULL,
  date        TIMESTAMP,
  customer_id INTEGER,
  film_id     INTEGER,
  PRIMARY KEY (id),
  FOREIGN KEY (customer_id) REFERENCES customer (id),
  FOREIGN KEY (film_id) REFERENCES film (id)
);