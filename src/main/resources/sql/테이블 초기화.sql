CREATE TABLE users (
    user_id VARCHAR(20) NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    password VARCHAR(10) NOT NULL,
    role VARCHAR(5) DEFAULT 'user',
    ssn VARCHAR(13),
    cell_phone VARCHAR(14),
    addr VARCHAR(100),
    email VARCHAR(50),
    reg_date DATE,
    PRIMARY KEY (user_id)
);

-- product 테이블 생성
CREATE TABLE product (
    prod_no INT AUTO_INCREMENT PRIMARY KEY,
    prod_name VARCHAR(100) NOT NULL,
    prod_detail VARCHAR(200),
    manufacture_day VARCHAR(8),
    price DECIMAL(10, 2), -- NUMBER(10) 대신 DECIMAL(10, 2)을 사용
    image_file VARCHAR(100),
    reg_date DATE
);

-- transaction 테이블 생성
CREATE TABLE transaction (
    tran_no INT AUTO_INCREMENT PRIMARY KEY,
    prod_no INT NOT NULL,
    buyer_id VARCHAR(20) NOT NULL,
    payment_option CHAR(3),
    receiver_name VARCHAR(20),
    receiver_phone VARCHAR(14),
    demailaddr VARCHAR(100),
    dlvy_request VARCHAR(100),
    tran_status_code CHAR(3),
    order_data DATE, -- order_data 대신 order_date를 사용
    dlvy_date DATE,
    FOREIGN KEY (prod_no) REFERENCES product (prod_no),
    FOREIGN KEY (buyer_id) REFERENCES users (user_id)
);

INSERT INTO users 
VALUES ( 'user01', 'SCOTT', '1111', 'user', NULL, NULL, NULL, NULL, NOW()); 

INSERT INTO users 
VALUES ( 'user02', 'SCOTT', '2222', 'user', NULL, NULL, NULL, NULL, NOW()); 

INSERT INTO users 
VALUES ( 'user03', 'SCOTT', '3333', 'user', NULL, NULL, NULL, NULL, NOW()); 

INSERT INTO users 
VALUES ( 'user04', 'SCOTT', '4444', 'user', NULL, NULL, NULL, NULL, NOW()); 

INSERT INTO users 
VALUES ( 'user05', 'SCOTT', '5555', 'user', NULL, NULL, NULL, NULL, NOW()); 

INSERT INTO users 
VALUES ( 'user06', 'SCOTT', '6666', 'user', NULL, NULL, NULL, NULL, NOW()); 

INSERT INTO users 
VALUES ( 'user07', 'SCOTT', '7777', 'user', NULL, NULL, NULL, NULL, NOW()); 

INSERT INTO users 
VALUES ( 'user08', 'SCOTT', '8888', 'user', NULL, NULL, NULL, NULL, NOW()); 

INSERT INTO users 
VALUES ( 'user09', 'SCOTT', '9999', 'user', NULL, NULL, NULL, NULL, NOW()); 

INSERT INTO users 
VALUES ( 'user10', 'SCOTT', '1010', 'user', NULL, NULL, NULL, NULL, NOW()); 

INSERT INTO users 
VALUES ( 'user11', 'SCOTT', '1111', 'user', NULL, NULL, NULL, NULL, NOW());

INSERT INTO users 
VALUES ( 'user12', 'SCOTT', '1212', 'user', NULL, NULL, NULL, NULL, NOW());

INSERT INTO users 
VALUES ( 'user13', 'SCOTT', '1313', 'user', NULL, NULL, NULL, NULL, NOW());

INSERT INTO users 
VALUES ( 'user14', 'SCOTT', '1414', 'user', NULL, NULL, NULL, NULL, NOW());

INSERT INTO users 
VALUES ( 'user15', 'SCOTT', '1515', 'user', NULL, NULL, NULL, NULL, NOW());

INSERT INTO users 
VALUES ( 'user16', 'SCOTT', '1616', 'user', NULL, NULL, NULL, NULL, NOW());

INSERT INTO users 
VALUES ( 'user17', 'SCOTT', '1717', 'user', NULL, NULL, NULL, NULL, NOW());

INSERT INTO users 
VALUES ( 'user18', 'SCOTT', '1818', 'user', NULL, NULL, NULL, NULL, NOW());

INSERT INTO users 
VALUES ( 'user19', 'SCOTT', '1919', 'user', NULL, NULL, NULL, NULL, NOW());

INSERT INTO users 
VALUES ( 'user20', 'SCOTT', '2020', 'user', NULL, NULL, NULL, NULL, NOW());

INSERT INTO users 
VALUES ( 'user21', 'SCOTT', '2121', 'user', NULL, NULL, NULL, NULL, NOW());

INSERT INTO users 
VALUES ( 'user22', 'SCOTT', '2222', 'user', NULL, NULL, NULL, NULL, NOW());

INSERT INTO users 
VALUES ( 'user23', 'SCOTT', '2323', 'user', NULL, NULL, NULL, NULL, NOW());
           
           
insert into product (prod_name, prod_detail, manu_date, price, file_name, reg_date) values ('보르도','최고 디자인 신품','20120201',1170000, 'AHlbAAAAvewfegAB.jpg',NOW());
insert into product (prod_name, prod_detail, manu_date, price, file_name, reg_date) values ('vaio vgn FS70B','소니 바이오 노트북 신동품','20120514',2000000, 'AHlbAAAAtBqyWAAA.jpg',NOW());
insert into product (prod_name, prod_detail, manu_date, price, file_name, reg_date) values ('자전거','자전거 좋아요~','20120514',10000, 'AHlbAAAAvetFNwAA.jpg',NOW());
insert into product (prod_name, prod_detail, manu_date, price, file_name, reg_date) values ('보드세트','한시즌 밖에 안썼습니다. 눈물을 머금고 내놓음 ㅠ.ㅠ','20120217', 200000, 'AHlbAAAAve1WwgAC.jpg',NOW());
insert into product (prod_name, prod_detail, manu_date, price, file_name, reg_date) values ('인라인','좋아욥','20120819', 20000, 'AHlbAAAAve37LwAD.jpg',NOW());
insert into product (prod_name, prod_detail, manu_date, price, file_name, reg_date) values ('삼성센스 2G','sens 메모리 2Giga','20121121',800000, 'AHlbAAAAtBqyWAAA.jpg',NOW());
insert into product (prod_name, prod_detail, manu_date, price, file_name, reg_date) values ('연꽃','정원을 가꿔보세요','20121022',232300, 'AHlbAAAAtDPSiQAA.jpg',NOW());
insert into product (prod_name, prod_detail, manu_date, price, file_name, reg_date) values ('삼성센스','노트북','20120212',600000, 'AHlbAAAAug1vsgAA.jpg',NOW());
