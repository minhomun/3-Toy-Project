CREATE TABLE `User_Info` (
                             `User_No`	BIGINT	auto_increment primary key,
                             `User_Id`	varchar(10)	NOT NULL,
                             `Password`	varchar(20)	NOT NULL,
                             `Name`	varchar(20)	NOT NULL,
                             `PhoneNumber`	varchar(20)	NOT NULL
);
CREATE TABLE `Notice_Table` (
                                `Table_No`	BIGINT	NOT null auto_increment primary key,
                                `User_No`	BIGINT	NOT null,
                                `User_Id`	varchar(10)	NOT NULL,
                                `TITLE`	varchar(50)	NOT NULL,
                                `CONTENT`	varchar(500)	NOT NULL,
                                `DATE`	DATETIME	NOT null,
                                foreign key (`User_No`) references `User_Info` (`User_No`)
);
CREATE TABLE `Notice_Table_Comment` (
                                        `Comment_No`	BIGINT	not null auto_increment primary key,
                                        `Table_No`	BIGINT	NOT NULL,
                                        `User_No`	BIGINT	NOT NULL,
                                        `User_Id`	varchar(10)	NOT NULL,
                                        `Content`	varchar(50)	NOT NULL,
                                        `Date`	DATETIME	NOT null,
                                        foreign key (`User_No`) references `User_Info` (`User_No`),
                                        foreign key (`Table_No`) references `Notice_Table` (`Table_No`)
);
CREATE TABLE `Free_Table` (
                              `Table_No`	BIGINT	NOT null not null auto_increment primary key,
                              `User_No`	BIGINT	NOT NULL,
                              `User_Id`	varchar(10)	NOT NULL,
                              `TITLE`	varchar(50)	NOT NULL,
                              `CONTENT`	varchar(500)	NOT NULL,
                              `DATE`	DATE	NOT NULL,
                              foreign key (`User_No`) references `User_Info` (`User_No`)
);
CREATE TABLE `Free_Table_Comment` (
                                      `Comment_No`	BIGINT	not null auto_increment primary key,
                                      `Table_No`	BIGINT	NOT NULL,
                                      `User_No`	BIGINT	NOT NULL,
                                      `User_Id`	varchar(10)	NOT NULL,
                                      `Content`	varchar(50)	NOT NULL,
                                      `Date`	DATETIME	NOT NULL,
                                      foreign key (`User_No`) references `User_Info` (`User_No`),
                                      foreign key (`Table_No`) references `Free_Table` (`Table_No`)
);
CREATE TABLE `FAQ_Table` (
                             `Table_No`	BIGINT	not null auto_increment primary key,
                             `User_No`	BIGINT	NOT NULL,
                             `User_Id`	varchar(10)	NOT NULL,
                             `TITLE`	varchar(50)	NOT NULL,
                             `CONTENT`	varchar(500)	NOT NULL,
                             `DATE`	DATETIME	NOT null,
                             foreign key (`User_No`) references `User_Info` (`User_No`)
);

insert into `User_Info` (`User_Id`, `Password`, `Name`, `PhoneNumber`) values ('admin', 'admin', 'admin', '010-0000-0000');
insert into `User_Info` (`User_Id`, `Password`, `Name`, `PhoneNumber`) values ('normal', 'normal', 'normal', '010-0000-0000');