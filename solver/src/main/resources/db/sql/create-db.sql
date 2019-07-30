--DROP TABLE users IF EXISTS;

CREATE TABLE book (
  isbn         VARCHAR(30) PRIMARY KEY,
  title VARCHAR(30),
  author  VARCHAR(50),
  publishedDate  VARCHAR(50)
);

CREATE TABLE review (
  isbn         VARCHAR(30),
  reviewername VARCHAR(30),
  content  VARCHAR(50),
  rating  double
);
