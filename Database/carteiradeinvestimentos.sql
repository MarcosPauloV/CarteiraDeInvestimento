CREATE TABLE asset (
    id UUID NOT NULL
        CONSTRAINT asset_pk
            PRIMARY KEY,
    totalValue DOUBLE PRECISION,
    totalQuantity INT
);

CREATE TABLE users (
    id UUID NOT NULL
        CONSTRAINT user_pk
            PRIMARY KEY,
    name VARCHAR(45),
    email VARCHAR(45),
    password VARCHAR(45),
    asset_id UUID
        CONSTRAINT user_fk_asset_id
            REFERENCES asset
);

CREATE TABLE investment (
    id UUID NOT NULL
        CONSTRAINT investment_pk
            PRIMARY KEY,
    name VARCHAR(45),
    description VARCHAR(45),
    category VARCHAR(45),
    value DOUBLE PRECISION,
    asset_id UUID
        CONSTRAINT investment_fk_asset_id
            REFERENCES asset
);

CREATE TABLE transaction (
    id UUID CONSTRAINT transaction_pk PRIMARY KEY,
    transactionDate VARCHAR(45),
    totalValue DOUBLE PRECISION,
    quantity INT,
    user_id UUID
        CONSTRAINT transaction_fk_user_id
            REFERENCES users,
    investment_id UUID
        CONSTRAINT transaction_fk_investment_id
            REFERENCES investment
);

-- Database Used

CREATE TABLE users (
 id UUID NOT NULL
   CONSTRAINT user_pk
         PRIMARY KEY,
 name VARCHAR(45),
 email VARCHAR(45),
 password VARCHAR(45),
);

CREATE TABLE transaction (
       id UUID CONSTRAINT transaction_pk PRIMARY KEY,
       transactionDate VARCHAR(45),
       totalValue DOUBLE PRECISION,
       quantity INT,
       user_id UUID
           CONSTRAINT transaction_fk_user_id
               REFERENCES users,
       investment_id UUID
);