CREATE DATABASE caregiving
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       CONNECTION LIMIT = -1;
\connect caregiving;
CREATE SCHEMA caregiving;