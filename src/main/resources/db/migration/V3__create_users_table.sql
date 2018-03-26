CREATE TABLE caregiving.users
(
  id bigserial NOT NULL,
  name character varying(255),
  login character varying(255),
  CONSTRAINT users_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE caregiving.users
    OWNER TO postgres;