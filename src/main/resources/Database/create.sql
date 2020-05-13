CREATE DATABASE organisational_api;
\c organisational_api;

CREATE TABLE users (id serial PRIMARY KEY, name VARCHAR, position VARCHAR, role VARCHAR, departmentid INTEGER );

CREATE TABLE departments (id serial PRIMARY KEY, name VARCHAR, description VARCHAR, numOfEmployees INTEGER);

CREATE TABLE news (id serial PRIMARY KEY, title VARCHAR, content VARCHAR, departmentid INTEGER);

CREATE DATABASE organisational_api_test WITH TEMPLATE organisational_api;
