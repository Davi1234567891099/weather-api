CREATE TABLE IF NOT EXISTS users (
  id uuid NOT NULL,
  user_name varchar(255) NULL,
  password varchar(255) NULL,
  account_non_expired boolean NULL,
  account_non_locked boolean NULL,
  credentials_non_expired boolean NULL,
  enabled boolean NULL,
  PRIMARY KEY (id)
);