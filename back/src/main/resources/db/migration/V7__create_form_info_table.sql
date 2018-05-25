CREATE TABLE caregiving.form_info
(
  id bigserial NOT NULL,
  info jsonb,
  CONSTRAINT form_info_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE caregiving.form_info
    OWNER TO postgres;

