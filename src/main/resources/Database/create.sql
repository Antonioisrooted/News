CREATE DATABASE news;
\c news

CREATE TABLE departments (id SERIAL PRIMARY KEY, department_name VARCHAR, department_description VARCHAR, department_id int, number_of_employees int);

CREATE TABLE employees ( id SERIAL PRIMARY KEY, employee_name VARCHAR, position_in_company VARCHAR, employee_id int, role VARCHAR, department VARCHAR);

CREATE TABLE departments_employee (id SERIAL PRIMARY KEY, department_id INTEGER, employee_id int);

CREATE TABLE news (id SERIAL PRIMARY KEY, general_news VARCHAR, department_news VARCHAR);

CREATE TABLE department_news (id SERIAL PRIMARY KEY, department_id INTEGER,news_id INTEGER);

CREATE DATABASE news_test WITH TEMPLATE news;

