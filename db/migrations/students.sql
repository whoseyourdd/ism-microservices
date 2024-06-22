/*
 Navicat Premium Data Transfer

 Source Server         : ism-student
 Source Server Type    : PostgreSQL
 Source Server Version : 140001
 Source Host           : localhost:54322
 Source Catalog        : students
 Source Schema         : students

 Target Server Type    : PostgreSQL
 Target Server Version : 140001
 File Encoding         : 65001

 Date: 23/06/2024 03:47:03
*/


-- ----------------------------
-- Type structure for gender_type
-- ----------------------------
DROP TYPE IF EXISTS "students"."gender_type";
CREATE TYPE "students"."gender_type" AS ENUM (
  'MALE',
  'FEMALE'
);
ALTER TYPE "students"."gender_type" OWNER TO "ism";

-- ----------------------------
-- Sequence structure for students_student_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "students"."students_student_id_seq";
CREATE SEQUENCE "students"."students_student_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Table structure for students
-- ----------------------------
DROP TABLE IF EXISTS "students"."students";
CREATE TABLE "students"."students" (
  "student_id" int4 NOT NULL DEFAULT nextval('"students".students_student_id_seq'::regclass),
  "student_name" varchar(255) COLLATE "pg_catalog"."default",
  "student_dob" date,
  "student_email" varchar(255) COLLATE "pg_catalog"."default",
  "student_gender" "students"."gender_type",
  "student_address" varchar(255) COLLATE "pg_catalog"."default",
  "classroom_id" int4
)
;

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO "students"."students" VALUES (1, 'John', '1994-06-23', 'john@school.id', 'MALE', 'John Address', 1);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "students"."students_student_id_seq"
OWNED BY "students"."students"."student_id";
SELECT setval('"students"."students_student_id_seq"', 1, true);

-- ----------------------------
-- Primary Key structure for table students
-- ----------------------------
ALTER TABLE "students"."students" ADD CONSTRAINT "students_pkey" PRIMARY KEY ("student_id");
