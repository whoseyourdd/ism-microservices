/*
 Navicat Premium Data Transfer

 Source Server         : ism-classroom
 Source Server Type    : PostgreSQL
 Source Server Version : 140001
 Source Host           : localhost:54321
 Source Catalog        : classrooms
 Source Schema         : classrooms

 Target Server Type    : PostgreSQL
 Target Server Version : 140001
 File Encoding         : 65001

 Date: 23/06/2024 03:46:48
*/


-- ----------------------------
-- Sequence structure for classrooms_classroom_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "classrooms"."classrooms_classroom_id_seq";
CREATE SEQUENCE "classrooms"."classrooms_classroom_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for grades_grade_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "classrooms"."grades_grade_id_seq";
CREATE SEQUENCE "classrooms"."grades_grade_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Table structure for classrooms
-- ----------------------------
DROP TABLE IF EXISTS "classrooms"."classrooms";
CREATE TABLE "classrooms"."classrooms" (
  "classroom_id" int4 NOT NULL DEFAULT nextval('"classrooms".classrooms_classroom_id_seq'::regclass),
  "classroom_name" varchar(255) COLLATE "pg_catalog"."default",
  "grade_id" int4
)
;

-- ----------------------------
-- Records of classrooms
-- ----------------------------
INSERT INTO "classrooms"."classrooms" VALUES (1, 'First Grade Class A', 1);

-- ----------------------------
-- Table structure for grades
-- ----------------------------
DROP TABLE IF EXISTS "classrooms"."grades";
CREATE TABLE "classrooms"."grades" (
  "grade_id" int4 NOT NULL DEFAULT nextval('"classrooms".grades_grade_id_seq'::regclass),
  "grade_level" varchar(50) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Records of grades
-- ----------------------------
INSERT INTO "classrooms"."grades" VALUES (1, 'First Grade');
INSERT INTO "classrooms"."grades" VALUES (2, 'Second Grade');

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "classrooms"."classrooms_classroom_id_seq"
OWNED BY "classrooms"."classrooms"."classroom_id";
SELECT setval('"classrooms"."classrooms_classroom_id_seq"', 1, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "classrooms"."grades_grade_id_seq"
OWNED BY "classrooms"."grades"."grade_id";
SELECT setval('"classrooms"."grades_grade_id_seq"', 2, true);

-- ----------------------------
-- Indexes structure for table classrooms
-- ----------------------------
CREATE UNIQUE INDEX "idx_unique_classroom_name_grade_id" ON "classrooms"."classrooms" USING btree (
  "classroom_name" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST,
  "grade_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table classrooms
-- ----------------------------
ALTER TABLE "classrooms"."classrooms" ADD CONSTRAINT "classrooms_pkey" PRIMARY KEY ("classroom_id");

-- ----------------------------
-- Uniques structure for table grades
-- ----------------------------
ALTER TABLE "classrooms"."grades" ADD CONSTRAINT "grades_grade_level_key" UNIQUE ("grade_level");

-- ----------------------------
-- Primary Key structure for table grades
-- ----------------------------
ALTER TABLE "classrooms"."grades" ADD CONSTRAINT "grades_pkey" PRIMARY KEY ("grade_id");

-- ----------------------------
-- Foreign Keys structure for table classrooms
-- ----------------------------
ALTER TABLE "classrooms"."classrooms" ADD CONSTRAINT "fk_grade" FOREIGN KEY ("grade_id") REFERENCES "classrooms"."grades" ("grade_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
