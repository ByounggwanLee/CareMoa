create table HELPER (
    ID bigint not null,
	NAME varchar(50),
	NICKNAME varchar(40),
	ADDRESS_CITY varchar(20),
	ADDRESS_STATE varchar(20),
	ADDRESS_STREET varchar(20),
	BIRTHDAY date,
	HELPER_SCORE integer,
	CAMCODER_AGREE boolean,
	CAREER_PERIOD integer,
	STATUS integer,
	primary key (ID)
);

create table HELPERJOB (
    ID bigint not null,
	HELPER_ID bigint not null,
	JOB_TYPE varchar(10),
	TAKE_AGE integer,
	JOB_AREA1_ADDRESS_CITY varchar(255),
	JOB_AREA1_ADDRESS_STATE varchar(255),
	JOB_AREA1_ADDRESS_STREET varchar(255),
	JOB_AREA2_ADDRESS_CITY varchar(255),
	JOB_AREA2_ADDRESS_STATE varchar(255),
	JOB_AREA2_ADDRESS_STREET varchar(255),
	JOB_AREA3_ADDRESS_CITY varchar(255),
	JOB_AREA3_ADDRESS_STATE varchar(255),
	JOB_AREA3_ADDRESS_STREET varchar(255),
	WORKING_DAYS varchar(20),
	WORK_START_TIME time,
	WORK_END_TIME time,
	WAGE_TYPE integer,
	WAGE_AMOUNT integer,
	ONE_LINE_ME varchar(128),
	ABOUT_ME varchar(2048),
	ACTIVE_FLAG boolean,
	primary key (ID)
);

alter table HELPER 
   add constraint UK_HELPER01 unique (NICKNAME);

alter table HELPERJOB 
   add constraint UK_HELPERJOB01 unique (HELPER_ID);

alter table HELPERJOB
   add constraint FK_HELPERJOB01
   foreign key (HELPER_ID) 
   references HELPER;