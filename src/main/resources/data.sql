INSERT INTO test VALUES (1,'t1');
INSERT INTO test VALUES (2,'t2');
INSERT INTO test VALUES (3,'t3');
INSERT INTO test VALUES (4,'t4');
-- Note: queries are in alphabetical order --
INSERT INTO customer VALUES (1, 451.12,'jon@snow.com','Jon','Snow',1234567890);
INSERT INTO customer VALUES (2, 128.89,'arya@stark.com','Arya','Stark',0987654321);
INSERT INTO customer VALUES (3, 500.00,'tyrian@lannister.com','Tyrian','Lannister',1234509876);
COMMIT;

--dishes commit-
INSERT INTO dish VALUES (1,'2','abir','NULL','strawberry cake',5,'Cake title');
INSERT INTO dish VALUES (2,'1','syed','NULL','RED velvet cake',5,'Cake title');
COMMIT;