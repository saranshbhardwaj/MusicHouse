# MusicHouse
This is an online music application. Here, a user can login and search different music products or a user can login as an admin(if given admin access) then the user can add, delete or update new music products.

To run this code, you need to run the below mentioned scripts in MySQL DB:

create table Productlist(
ProductID INT AUTO_INCREMENT,
ProductName VARCHAR(80),
ProductCode VARCHAR(50),
ProductStatus VARCHAR(50),
Price VARCHAR(100),
Description VARCHAR(1000),
ImageUrl VARCHAR(1000),
Rating VARCHAR(100),
PRIMARY KEY (ProductID)
);




Insert into Productlist(ProductName,ProductCode,ProductStatus,Price,Description, ImageUrl ,Rating)
values ('Fender1009', 'FD09', 'Available', '$110', 'Fender Guitar', 'images/guitar.jpg','3.5'),
('Yahama FD200', 'FD09', 'Available', '$110', 'Yahama Guitar', 'images/guitar.jpg','3.5'),
('Ibanez RG 600FM', 'RGFM', 'Available', '$110', 'Ibanez Guitar', 'images/guitar.jpg','3.5'),
('Martin TL400', 'TL4', 'Available', '$110', 'Martin Guitar', 'images/guitar.jpg','3.5'),
('Tayler FG8000', 'FG8', 'Available', '$110', 'Tayler Guitar', 'images/guitar.jpg','3.5')
