CREATE TABLE caregiving.product
(
  number character varying(100) NOT NULL,
  name character varying(255),
  CONSTRAINT product_pkey PRIMARY KEY (number)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE caregiving.product
    OWNER TO postgres;

