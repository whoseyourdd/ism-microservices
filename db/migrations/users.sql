/*
 Navicat Premium Data Transfer

 Source Server         : ism-user
 Source Server Type    : PostgreSQL
 Source Server Version : 140001
 Source Host           : localhost:54323
 Source Catalog        : users
 Source Schema         : users

 Target Server Type    : PostgreSQL
 Target Server Version : 140001
 File Encoding         : 65001

 Date: 23/06/2024 03:47:09
*/


-- ----------------------------
-- Type structure for role_type
-- ----------------------------
DROP TYPE IF EXISTS "users"."role_type";
CREATE TYPE "users"."role_type" AS ENUM (
  'ADMIN',
  'TEACHER',
  'STUDENT'
);
ALTER TYPE "users"."role_type" OWNER TO "ism";

-- ----------------------------
-- Sequence structure for users_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "users"."users_id_seq";
CREATE SEQUENCE "users"."users_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS "users"."users";
CREATE TABLE "users"."users" (
  "id" int4 NOT NULL DEFAULT nextval('"users".users_id_seq'::regclass),
  "username" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(100) COLLATE "pg_catalog"."default",
  "password" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "role" "users"."role_type" DEFAULT 'STUDENT'::users.role_type
)
;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO "users"."users" VALUES (1, 'admin', 'Admin', '$2a$10$p127uTYJSyZjzSmVvY0IZO1Qkm3Ixl/IJJOz1s4bgRkNTrK20jeRu', 'ADMIN');
INSERT INTO "users"."users" VALUES (3, 'user', 'User', '$2a$10$6rjdxlro655Vs4M3drJ8DusOueW8da87D15vSr/9Yjoe.wmxJHySy', 'STUDENT');

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "users"."users_id_seq"
OWNED BY "users"."users"."id";
SELECT setval('"users"."users_id_seq"', 3, true);

-- ----------------------------
-- Indexes structure for table users
-- ----------------------------
CREATE UNIQUE INDEX "idx_unique_username" ON "users"."users" USING btree (
  "username" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table users
-- ----------------------------
ALTER TABLE "users"."users" ADD CONSTRAINT "users_pkey" PRIMARY KEY ("id");
