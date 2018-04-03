CREATE TABLE caregiving.users
(
  id bigserial NOT NULL,
  name character varying(100),
  login character varying(50),
  password character varying(255),
  type character varying(50),
  CONSTRAINT users_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE caregiving.users
    OWNER TO postgres;

CREATE INDEX users_name_idx
  ON caregiving.users
  USING btree
  (name);

CREATE INDEX users_login_idx
  ON caregiving.users
  USING btree
  (login);