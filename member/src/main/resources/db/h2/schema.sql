create table MEMBER (
    ID bigint not null,
	USER_ID varchar(30) not null,
	PASSWORD varchar(30),
	NAME varchar(50),
	NICKNAME varchar(40),
	ADDRESS_CITY varchar(20),
	ADDRESS_STATE varchar(20),
	ADDRESS_STREET varchar(20),
	USER_SCORE integer,
	STATUS varchar(20),
	CREATED_TIME timestamp,
	MODIFIED_TIME timestamp,
	primary key (ID)
);

create table MEMBERROLE (
    ID bigint not null,
	MEMBER_ID bigint,
	ROLE varchar(20) not null,
	CREATOR_ID varchar(255),
	CREATED_TIME timestamp,
	MODIFIER_ID varchar(255),
	MODIFIED_TIME timestamp,
	primary key (ID)
);

alter table MEMBER 
   add constraint UK_MEMBER01 unique (NICKNAME);

alter table MEMBER 
   add constraint UK_MEMBER02 unique (USER_ID);

alter table MEMBERROLE 
   add constraint UK_MEMBERROLE01 unique (MEMBER_ID, ROLE);

alter table MEMBERROLE 
   add constraint FK_MEMBERROLE01
   foreign key (MEMBER_ID) 
   references MEMBER;