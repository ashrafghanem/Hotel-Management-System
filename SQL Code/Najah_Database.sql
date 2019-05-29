--------------------------------------------------------
--  File created - Saturday-August-12-2017   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence BOOKING_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "HOTELDB"."BOOKING_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 ORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence EMP_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "HOTELDB"."EMP_SEQ"  MINVALUE 2 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 2 CACHE 20 ORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table EMPLOYEE
--------------------------------------------------------

  CREATE TABLE "HOTELDB"."EMPLOYEE" 
   (	"EMP_NO" NUMBER(5,0), 
	"EMP_ID" NUMBER(9,0), 
	"SALARY" NUMBER(6,0), 
	"SOCIAL_STATE" VARCHAR2(15), 
	"PHONE_NO" NUMBER(15,0), 
	"FAMILY_COUNT" NUMBER(3,0)
   ) ;
--------------------------------------------------------
--  DDL for Table ASSIGNS
--------------------------------------------------------

  CREATE TABLE "HOTELDB"."ASSIGNS" 
   (	"USERNAME" VARCHAR2(15), 
	"PASSWORD" VARCHAR2(15), 
	"SECURITY_QUESTION" VARCHAR2(64), 
	"ANSWER" VARCHAR2(64), 
	"EMP_NO" NUMBER(5,0)
   ) ;
--------------------------------------------------------
--  DDL for Table GUEST
--------------------------------------------------------

  CREATE TABLE "HOTELDB"."GUEST" 
   (	"GUEST_ID" NUMBER(9,0), 
	"TOTAL_PAYMENT" FLOAT(126)
   ) ;
--------------------------------------------------------
--  DDL for Table BOOKING
--------------------------------------------------------

  CREATE TABLE "HOTELDB"."BOOKING" 
   (	"BOOKING_NO" NUMBER(6,0), 
	"BOOKING_DATE" DATE, 
	"BOOKING_TYPE" VARCHAR2(9), 
	"GUEST_ID" NUMBER(9,0), 
	"USERNAME" VARCHAR2(15)
   ) ;
--------------------------------------------------------
--  DDL for Table PERSON
--------------------------------------------------------

  CREATE TABLE "HOTELDB"."PERSON" 
   (	"ID" NUMBER(9,0), 
	"FNAME" VARCHAR2(12), 
	"MNAME" CHAR(1), 
	"LNAME" VARCHAR2(12), 
	"STREET" VARCHAR2(12), 
	"CITY" VARCHAR2(12), 
	"COUNTRY" VARCHAR2(12), 
	"NATIONALITY" VARCHAR2(15), 
	"MOBILE_NO" VARCHAR2(15), 
	"GENDER" CHAR(1), 
	"BIRTH_DATE" DATE, 
	"EMAIL" VARCHAR2(31)
   ) ;
--------------------------------------------------------
--  DDL for Table DEPENDENT
--------------------------------------------------------

  CREATE TABLE "HOTELDB"."DEPENDENT" 
   (	"EMP_NO" NUMBER(5,0), 
	"DEP_NAME" VARCHAR2(9), 
	"DEP_BD" DATE, 
	"DEP_GENDER" CHAR(1), 
	"DEP_RELATIONSHIP" VARCHAR2(15)
   ) ;
--------------------------------------------------------
--  DDL for Table MEAL
--------------------------------------------------------

  CREATE TABLE "HOTELDB"."MEAL" 
   (	"NAME" VARCHAR2(15), 
	"PRICE" FLOAT(126), 
	"BOOKING_NO" NUMBER(6,0)
   ) ;
--------------------------------------------------------
--  DDL for Table ROOM_TYPE
--------------------------------------------------------

  CREATE TABLE "HOTELDB"."ROOM_TYPE" 
   (	"TYPE_CODE" VARCHAR2(15), 
	"DESCRIPTION" VARCHAR2(128), 
	"PRICE" NUMBER(6,0), 
	"FLOOR" NUMBER(3,0)
   ) ;
--------------------------------------------------------
--  DDL for Table ROOM
--------------------------------------------------------

  CREATE TABLE "HOTELDB"."ROOM" 
   (	"ROOM_NO" NUMBER(3,0), 
	"PURPOSE_OF_VISIT" VARCHAR2(15), 
	"CHECK_IN_DATE" DATE, 
	"PHONE_NO" NUMBER(15,0), 
	"AVAILABILITY" CHAR(1), 
	"ROOM_TYPE" VARCHAR2(15), 
	"BOOKING_NO" NUMBER(6,0), 
	"VISIT_PERIOD" NUMBER(4,0)
   ) ;

---------------------------------------------------
--   DATA FOR TABLE PERSON
--   FILTER = none used
---------------------------------------------------
REM INSERTING into HOTELDB.PERSON
Insert into HOTELDB.PERSON (ID,FNAME,MNAME,LNAME,STREET,CITY,COUNTRY,NATIONALITY,MOBILE_NO,GENDER,BIRTH_DATE,EMAIL) values (402804033,'Ashraf','N','Ghanem','Al-Sahel','Tulkarm','Palestine','Palestinian','0595736981','M',to_timestamp('11-JAN-98 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'ans_g1998@hotmail.com');

---------------------------------------------------
--   END DATA FOR TABLE PERSON
---------------------------------------------------

---------------------------------------------------
--   DATA FOR TABLE BOOKING
--   FILTER = none used
---------------------------------------------------
REM INSERTING into HOTELDB.BOOKING

---------------------------------------------------
--   END DATA FOR TABLE BOOKING
---------------------------------------------------

---------------------------------------------------
--   DATA FOR TABLE MEAL
--   FILTER = none used
---------------------------------------------------
REM INSERTING into HOTELDB.MEAL

---------------------------------------------------
--   END DATA FOR TABLE MEAL
---------------------------------------------------

---------------------------------------------------
--   DATA FOR TABLE ROOM_TYPE
--   FILTER = none used
---------------------------------------------------
REM INSERTING into HOTELDB.ROOM_TYPE

---------------------------------------------------
--   END DATA FOR TABLE ROOM_TYPE
---------------------------------------------------

---------------------------------------------------
--   DATA FOR TABLE EMPLOYEE
--   FILTER = none used
---------------------------------------------------
REM INSERTING into HOTELDB.EMPLOYEE
Insert into HOTELDB.EMPLOYEE (EMP_NO,EMP_ID,SALARY,SOCIAL_STATE,PHONE_NO,FAMILY_COUNT) values (1,402804033,5000,'Single',92663809,0);

---------------------------------------------------
--   END DATA FOR TABLE EMPLOYEE
---------------------------------------------------

---------------------------------------------------
--   DATA FOR TABLE GUEST
--   FILTER = none used
---------------------------------------------------
REM INSERTING into HOTELDB.GUEST

---------------------------------------------------
--   END DATA FOR TABLE GUEST
---------------------------------------------------

---------------------------------------------------
--   DATA FOR TABLE ASSIGNS
--   FILTER = none used
---------------------------------------------------
REM INSERTING into HOTELDB.ASSIGNS
Insert into HOTELDB.ASSIGNS (USERNAME,PASSWORD,SECURITY_QUESTION,ANSWER,EMP_NO) values ('ashrafghanem','12345','Where were you born?','Tulkarm',1);

---------------------------------------------------
--   END DATA FOR TABLE ASSIGNS
---------------------------------------------------

---------------------------------------------------
--   DATA FOR TABLE DEPENDENT
--   FILTER = none used
---------------------------------------------------
REM INSERTING into HOTELDB.DEPENDENT

---------------------------------------------------
--   END DATA FOR TABLE DEPENDENT
---------------------------------------------------

---------------------------------------------------
--   DATA FOR TABLE ROOM
--   FILTER = none used
---------------------------------------------------
REM INSERTING into HOTELDB.ROOM

---------------------------------------------------
--   END DATA FOR TABLE ROOM
---------------------------------------------------
--------------------------------------------------------
--  Constraints for Table ASSIGNS
--------------------------------------------------------

  ALTER TABLE "HOTELDB"."ASSIGNS" MODIFY ("USERNAME" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."ASSIGNS" MODIFY ("PASSWORD" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."ASSIGNS" MODIFY ("SECURITY_QUESTION" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."ASSIGNS" MODIFY ("ANSWER" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."ASSIGNS" MODIFY ("EMP_NO" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."ASSIGNS" ADD PRIMARY KEY ("USERNAME") ENABLE;
--------------------------------------------------------
--  Constraints for Table BOOKING
--------------------------------------------------------

  ALTER TABLE "HOTELDB"."BOOKING" MODIFY ("BOOKING_NO" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."BOOKING" MODIFY ("BOOKING_DATE" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."BOOKING" MODIFY ("BOOKING_TYPE" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."BOOKING" MODIFY ("GUEST_ID" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."BOOKING" MODIFY ("USERNAME" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."BOOKING" ADD PRIMARY KEY ("BOOKING_NO") ENABLE;
--------------------------------------------------------
--  Constraints for Table DEPENDENT
--------------------------------------------------------

  ALTER TABLE "HOTELDB"."DEPENDENT" MODIFY ("EMP_NO" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."DEPENDENT" MODIFY ("DEP_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."DEPENDENT" ADD PRIMARY KEY ("EMP_NO", "DEP_NAME") ENABLE;
--------------------------------------------------------
--  Constraints for Table EMPLOYEE
--------------------------------------------------------

  ALTER TABLE "HOTELDB"."EMPLOYEE" MODIFY ("EMP_NO" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."EMPLOYEE" MODIFY ("EMP_ID" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."EMPLOYEE" MODIFY ("SALARY" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."EMPLOYEE" MODIFY ("SOCIAL_STATE" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."EMPLOYEE" ADD PRIMARY KEY ("EMP_NO") ENABLE;
--------------------------------------------------------
--  Constraints for Table GUEST
--------------------------------------------------------

  ALTER TABLE "HOTELDB"."GUEST" MODIFY ("GUEST_ID" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."GUEST" ADD PRIMARY KEY ("GUEST_ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table MEAL
--------------------------------------------------------

  ALTER TABLE "HOTELDB"."MEAL" MODIFY ("NAME" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."MEAL" MODIFY ("PRICE" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."MEAL" ADD PRIMARY KEY ("NAME") ENABLE;
--------------------------------------------------------
--  Constraints for Table PERSON
--------------------------------------------------------

  ALTER TABLE "HOTELDB"."PERSON" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."PERSON" MODIFY ("FNAME" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."PERSON" MODIFY ("MNAME" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."PERSON" MODIFY ("LNAME" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."PERSON" MODIFY ("STREET" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."PERSON" MODIFY ("CITY" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."PERSON" MODIFY ("COUNTRY" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."PERSON" MODIFY ("MOBILE_NO" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."PERSON" MODIFY ("GENDER" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."PERSON" MODIFY ("BIRTH_DATE" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."PERSON" ADD PRIMARY KEY ("ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table ROOM
--------------------------------------------------------

  ALTER TABLE "HOTELDB"."ROOM" MODIFY ("ROOM_NO" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."ROOM" MODIFY ("PHONE_NO" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."ROOM" MODIFY ("AVAILABILITY" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."ROOM" MODIFY ("ROOM_TYPE" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."ROOM" ADD PRIMARY KEY ("ROOM_NO") ENABLE;
--------------------------------------------------------
--  Constraints for Table ROOM_TYPE
--------------------------------------------------------

  ALTER TABLE "HOTELDB"."ROOM_TYPE" MODIFY ("TYPE_CODE" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."ROOM_TYPE" MODIFY ("PRICE" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."ROOM_TYPE" MODIFY ("FLOOR" NOT NULL ENABLE);
 
  ALTER TABLE "HOTELDB"."ROOM_TYPE" ADD PRIMARY KEY ("TYPE_CODE") ENABLE;
--------------------------------------------------------
--  DDL for Index SYS_C0011124
--------------------------------------------------------

  CREATE UNIQUE INDEX "HOTELDB"."SYS_C0011124" ON "HOTELDB"."ASSIGNS" ("USERNAME") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_C0011131
--------------------------------------------------------

  CREATE UNIQUE INDEX "HOTELDB"."SYS_C0011131" ON "HOTELDB"."BOOKING" ("BOOKING_NO") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_C0011117
--------------------------------------------------------

  CREATE UNIQUE INDEX "HOTELDB"."SYS_C0011117" ON "HOTELDB"."DEPENDENT" ("EMP_NO", "DEP_NAME") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_C0011113
--------------------------------------------------------

  CREATE UNIQUE INDEX "HOTELDB"."SYS_C0011113" ON "HOTELDB"."EMPLOYEE" ("EMP_NO") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_C0011107
--------------------------------------------------------

  CREATE UNIQUE INDEX "HOTELDB"."SYS_C0011107" ON "HOTELDB"."GUEST" ("GUEST_ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_C0011136
--------------------------------------------------------

  CREATE UNIQUE INDEX "HOTELDB"."SYS_C0011136" ON "HOTELDB"."MEAL" ("NAME") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_C0011105
--------------------------------------------------------

  CREATE UNIQUE INDEX "HOTELDB"."SYS_C0011105" ON "HOTELDB"."PERSON" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_C0011146
--------------------------------------------------------

  CREATE UNIQUE INDEX "HOTELDB"."SYS_C0011146" ON "HOTELDB"."ROOM" ("ROOM_NO") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_C0011141
--------------------------------------------------------

  CREATE UNIQUE INDEX "HOTELDB"."SYS_C0011141" ON "HOTELDB"."ROOM_TYPE" ("TYPE_CODE") 
  ;
--------------------------------------------------------
--  Ref Constraints for Table ASSIGNS
--------------------------------------------------------

  ALTER TABLE "HOTELDB"."ASSIGNS" ADD FOREIGN KEY ("EMP_NO")
	  REFERENCES "HOTELDB"."EMPLOYEE" ("EMP_NO") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BOOKING
--------------------------------------------------------

  ALTER TABLE "HOTELDB"."BOOKING" ADD FOREIGN KEY ("GUEST_ID")
	  REFERENCES "HOTELDB"."GUEST" ("GUEST_ID") ON DELETE CASCADE ENABLE;
 
  ALTER TABLE "HOTELDB"."BOOKING" ADD FOREIGN KEY ("USERNAME")
	  REFERENCES "HOTELDB"."ASSIGNS" ("USERNAME") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table DEPENDENT
--------------------------------------------------------

  ALTER TABLE "HOTELDB"."DEPENDENT" ADD FOREIGN KEY ("EMP_NO")
	  REFERENCES "HOTELDB"."EMPLOYEE" ("EMP_NO") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table EMPLOYEE
--------------------------------------------------------

  ALTER TABLE "HOTELDB"."EMPLOYEE" ADD FOREIGN KEY ("EMP_ID")
	  REFERENCES "HOTELDB"."PERSON" ("ID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table GUEST
--------------------------------------------------------

  ALTER TABLE "HOTELDB"."GUEST" ADD FOREIGN KEY ("GUEST_ID")
	  REFERENCES "HOTELDB"."PERSON" ("ID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table MEAL
--------------------------------------------------------

  ALTER TABLE "HOTELDB"."MEAL" ADD FOREIGN KEY ("BOOKING_NO")
	  REFERENCES "HOTELDB"."BOOKING" ("BOOKING_NO") ON DELETE SET NULL ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table ROOM
--------------------------------------------------------

  ALTER TABLE "HOTELDB"."ROOM" ADD FOREIGN KEY ("ROOM_TYPE")
	  REFERENCES "HOTELDB"."ROOM_TYPE" ("TYPE_CODE") ON DELETE CASCADE ENABLE;
 
  ALTER TABLE "HOTELDB"."ROOM" ADD FOREIGN KEY ("BOOKING_NO")
	  REFERENCES "HOTELDB"."BOOKING" ("BOOKING_NO") ON DELETE SET NULL ENABLE;

