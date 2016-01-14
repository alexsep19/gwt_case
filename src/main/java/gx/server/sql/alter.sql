CREATE DATABASE db ENCODING 'UTF8';
CREATE USER rss WITH password 'rss';
CREATE SCHEMA AUTHORIZATION rss;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA rss TO rss;
CREATE TABLE rss.category (
  "id" SERIAL PRIMARY KEY,
  "name" varchar(30) DEFAULT NULL
);
insert INTO rss.category (name) VALUES('ыапыап11');
insert INTO rss.category (name) VALUES('sdfad11');
commit;
select * from rss.category;