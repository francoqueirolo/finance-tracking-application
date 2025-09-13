CREATE TABLE users
(
    id                serial       NOT NULL,
    username          varchar(50)  NOT NULL,
    email             varchar(100) NOT NULL,
    "password"        varchar(255) NOT NULL,
    first_name        varchar(50)  NOT NULL,
    last_name         varchar(50)  NOT NULL,
    phone             varchar(20),
    profile_image_url varchar(255),
    is_active         boolean      NOT NULL,
    is_verified       boolean      NOT NULL DEFAULT false,
    created_at        timestamp             DEFAULT CURRENT_TIMESTAMP,
    updated_at        timestamp,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

COMMENT
ON TABLE users IS 'Tabla para manejo de usuarios';

CREATE TABLE roles
(
    id          serial       NOT NULL,
    "name"      varchar(255) NOT NULL,
    description varchar(255),
    created_at  timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at  timestamp,
    CONSTRAINT roles_pkey PRIMARY KEY (id)
);

CREATE TABLE users_roles
(
    user_id integer NOT NULL,
    role_id integer NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (user_id, role_id)
);

CREATE TABLE email_verification_tokens
(
    id          serial       NOT NULL,
    user_id     integer      NOT NULL,
    token       varchar(255) NOT NULL,
    expiry_date timestamp    NOT NULL,
    is_revoked  boolean DEFAULT false,
    CONSTRAINT email_verification_tokens_pkey PRIMARY KEY (id)
);

CREATE TABLE password_reset_tokens
(
    id          serial       NOT NULL,
    user_id     integer      NOT NULL,
    token       varchar(255) NOT NULL,
    expiry_date timestamp    NOT NULL,
    is_used     boolean      NOT NULL DEFAULT false,
    CONSTRAINT password_reset_tokens_pkey PRIMARY KEY (id)
);

CREATE TABLE refresh_tokens
(
    id          serial       NOT NULL,
    user_id     integer      NOT NULL,
    token       varchar(255) NOT NULL,
    expiry_date timestamp    NOT NULL,
    is_revoked  boolean      NOT NULL DEFAULT false,
    CONSTRAINT refresh_tokens_pkey PRIMARY KEY (id)
);

CREATE TABLE account_types
(
    id          serial       NOT NULL,
    "name"      varchar(255) NOT NULL,
    description varchar(255),
    CONSTRAINT account_types_pkey PRIMARY KEY (id)
);

CREATE TABLE accounts
(
    id              serial  NOT NULL,
    user_id         integer NOT NULL,
    account_type_id integer NOT NULL,
    "name"          varchar(255),
    description     varchar(255),
    initial_balance numeric((15, 2
) ),
  current_balance numeric((15, 2)),
  is_active boolean,
  bank_name varchar(255),
  account_number varchar(255),
  CONSTRAINT accounts_pkey PRIMARY KEY(id)
);

CREATE TABLE categories
(
    id      serial  NOT NULL,
    user_id integer NOT NULL,
    "name"  varchar(100)
) NOT NULL,
  description varchar(255),
  color varchar(7),
  icon varchar(50),
  CONSTRAINT categories_pkey PRIMARY KEY(id)
);

CREATE TABLE transactions
(
    id          serial  NOT NULL,
    account_id  integer NOT NULL,
    category_id integer NOT NULL,
    amount      numeric((15, 2
) ) NOT NULL,
  description varchar(255) NOT NULL,
  transaction_date timestamp NOT NULL,
  transaction_type varchar(20) NOT NULL,
  notes text,
  "location" varchar(255) NOT NULL,
  CONSTRAINT transactions_pkey PRIMARY KEY(id)
);

CREATE TABLE budgets
(
    id           serial       NOT NULL,
    user_id      integer      NOT NULL,
    "name"       varchar(100) NOT NULL,
    description  varchar(255),
    total_amount numeric((15, 2
) ) NOT NULL,
  start_date date NOT NULL,
  end_date date NOT NULL,
  budget_type varchar(20) NOT NULL,
  is_active boolean DEFAULT true,
  CONSTRAINT budgets_pkey PRIMARY KEY(id)
);

CREATE TABLE budget_categories
(
    id           serial  NOT NULL,
    budget_id    integer NOT NULL,
    category_id  integer NOT NULL,
    spent_amount numeric((15, 2
) ) NOT NULL,
  allocated_amount numeric((15, 2)) NOT NULL,
  CONSTRAINT budget_categories_pkey PRIMARY KEY(id)
);

CREATE TABLE financial_goals
(
    id            serial       NOT NULL,
    user_id       integer      NOT NULL,
    "name"        varchar(100) NOT NULL,
    description   varchar(255),
    target_amount numeric((15, 2
) ) NOT NULL,
  current_amount numeric((15, 2)) NOT NULL,
  target_date date NOT NULL,
  goal_type varchar(20) NOT NULL,
  is_achieved boolean DEFAULT false,
  is_active boolean DEFAULT true,
  CONSTRAINT financial_goals_pkey PRIMARY KEY(id)
);

CREATE TABLE goal_contribution
(
    id             serial  NOT NULL,
    goal_id        integer NOT NULL,
    transaction_id integer NOT NULL,
    amount         numeric((15, 2
) ) NOT NULL,
  contribution_date date NOT NULL,
  notes text,
  CONSTRAINT goal_contribution_pkey PRIMARY KEY(id)
);

ALTER TABLE
    users_roles
    ADD
        CONSTRAINT users_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE
    users_roles
    ADD
        CONSTRAINT users_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE
    email_verification_tokens
    ADD
        CONSTRAINT email_verification_tokens_user_id_fkey FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE
    password_reset_tokens
    ADD
        CONSTRAINT password_reset_tokens_user_id_fkey FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE
    refresh_tokens
    ADD
        CONSTRAINT refresh_tokens_user_id_fkey FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE
    accounts
    ADD
        CONSTRAINT accounts_user_id_fkey FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE
    accounts
    ADD
        CONSTRAINT accounts_account_type_id_fkey FOREIGN KEY (account_type_id) REFERENCES account_types (id);

ALTER TABLE
    transactions
    ADD
        CONSTRAINT transactions_account_id_fkey FOREIGN KEY (account_id) REFERENCES accounts (id);

ALTER TABLE
    transactions
    ADD
        CONSTRAINT transactions_category_id_fkey FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE
    budget_categories
    ADD
        CONSTRAINT budget_categories_budget_id_fkey FOREIGN KEY (budget_id) REFERENCES budgets (id);

ALTER TABLE
    budget_categories
    ADD
        CONSTRAINT budget_categories_category_id_fkey FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE
    goal_contribution
    ADD
        CONSTRAINT goal_contribution_goal_id_fkey FOREIGN KEY (goal_id) REFERENCES financial_goals (id);

ALTER TABLE
    goal_contribution
    ADD
        CONSTRAINT goal_contribution_transaction_id_fkey FOREIGN KEY (transaction_id) REFERENCES transactions (id);

ALTER TABLE
    financial_goals
    ADD
        CONSTRAINT financial_goals_user_id_fkey FOREIGN KEY (user_id) REFERENCES users (id);
