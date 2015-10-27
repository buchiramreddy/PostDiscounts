# PostDiscounts

CREATE TABLE PRODUCT(
    id INT NOT NULL auto_increment,
    name VARCHAR(50) NOT NULL,
    joining_date DATE NOT NULL,
    salary DOUBLE NOT NULL,
    ssn VARCHAR(30) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

jdbc.driverClassName = com.mysql.jdbc.Driver
jdbc.url = jdbc:mysql://127.0.0.1:3306/ads
jdbc.username = root
jdbc.password = bx7e7UuxqE*K
hibernate.dialect = org.hibernate.dialect.MySQLDialect
hibernate.show_sql = true
hibernate.format_sql = true
