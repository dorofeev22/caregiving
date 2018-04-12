CREATE TABLE caregiving.user_role
(
  id bigserial NOT NULL,
  name character varying(50),
  description character varying(255),
  CONSTRAINT user_role_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE caregiving.user_role
    OWNER TO postgres;

CREATE INDEX user_role_name_idx
  ON caregiving.user_role
  USING btree
  (name);

CREATE INDEX user_role_description_idx
  ON caregiving.user_role
  USING btree
  (description);

INSERT INTO caregiving.user_role(name, description)
    VALUES ('main role', 'Main role'), ('create user', 'Create user'), ('delete user', 'Delete user'), ('edit user', 'Edit user');

ALTER TABLE caregiving.users ADD COLUMN user_role bigint;

ALTER TABLE caregiving.users ADD CONSTRAINT fk_users_user_role FOREIGN KEY (user_role)
  REFERENCES caregiving.user_role (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE CASCADE;

CREATE INDEX users_user_role_idx
  ON caregiving.users
  USING btree
  (user_role);

