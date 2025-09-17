CREATE TABLE tbl_agencies (
    id CHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    latitude DECIMAL(9,6),
    longitude DECIMAL(9,6)
);