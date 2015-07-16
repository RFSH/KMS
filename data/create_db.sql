/*
 Navicat PostgreSQL Data Transfer

 Source Server         : KMS
 Source Server Version : 90305
 Source Host           : localhost
 Source Database       : kms
 Source Schema         : public

 Target Server Version : 90305
 File Encoding         : utf-8

 Date: 07/10/2015 12:05:46 PM
*/

-- ----------------------------
--  Table structure for abuse_reports
-- ----------------------------
DROP TABLE IF EXISTS "public"."abuse_reports";
CREATE TABLE "public"."abuse_reports" (
	"id" varchar(10) NOT NULL COLLATE "default",
	"content" text NOT NULL COLLATE "default",
	"knowledge_id" varchar(10) NOT NULL COLLATE "default",
	"employee_id" varchar(100) NOT NULL COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."abuse_reports" OWNER TO "hadi";

-- ----------------------------
--  Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS "public"."users";
CREATE TABLE "public"."users" (
	"id" varchar(5) NOT NULL COLLATE "default",
	"first_name" varchar(100) NOT NULL COLLATE "default",
	"last_name" varchar(100) NOT NULL COLLATE "default",
	"username" varchar(100) NOT NULL COLLATE "default",
	"email" varchar(100) NOT NULL COLLATE "default",
	"national_id" varchar(100) COLLATE "default",
	"password" varchar(100) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."users" OWNER TO "hadi";

-- ----------------------------
--  Records of users
-- ----------------------------
BEGIN;
INSERT INTO "public"."users" VALUES ('1', 'ادمین', 'ادمینستراتور', 'admin', 'admin@admin.com', '323', '123');
INSERT INTO "public"."users" VALUES ('2', 'هادی', 'ذوافقاری', 'hadi', 'hadi@zolfaghari.com', '3324802', '123');
INSERT INTO "public"."users" VALUES ('3', 'عارف ', 'شفاعی', 'aref', 'aref@shafaei.com', '324093', '123');
INSERT INTO "public"."users" VALUES ('4', 'مجید', 'رحیمی‌نژاد', 'majid', 'majid@rahiminejad.com', '334124', '123');
INSERT INTO "public"."users" VALUES ('5', 'محمد', 'رازقی', 'mohammad', 'mamad@razeghi.com', '32435', '123');
INSERT INTO "public"."users" VALUES ('6', 'محمد', 'مهتابی', 'mahtabi', 'mohammad@mahtabi.com', '345543', '123');
COMMIT;

-- ----------------------------
--  Table structure for knowledges
-- ----------------------------
DROP TABLE IF EXISTS "public"."knowledges";
CREATE TABLE "public"."knowledges" (
	"id" varchar(100) NOT NULL COLLATE "default",
	"employee_id" varchar(10) NOT NULL COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."knowledges" OWNER TO "hadi";

-- ----------------------------
--  Records of knowledges
-- ----------------------------
BEGIN;
INSERT INTO "public"."knowledges" VALUES ('1', '4');
INSERT INTO "public"."knowledges" VALUES ('2', '3');
INSERT INTO "public"."knowledges" VALUES ('3', '5');
COMMIT;

-- ----------------------------
--  Table structure for permission_levels
-- ----------------------------
DROP TABLE IF EXISTS "public"."permission_levels";
CREATE TABLE "public"."permission_levels" (
	"id" varchar(10) NOT NULL COLLATE "default",
	"name" varchar(100) NOT NULL COLLATE "default",
	"order" int4 NOT NULL DEFAULT 1
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."permission_levels" OWNER TO "hadi";

-- ----------------------------
--  Records of permission_levels
-- ----------------------------
BEGIN;
INSERT INTO "public"."permission_levels" VALUES ('1', 'سطح بالا', '1');
INSERT INTO "public"."permission_levels" VALUES ('2', 'سطح متوسط رو به بالا', '2');
INSERT INTO "public"."permission_levels" VALUES ('3', 'سطح متوسط', '3');
INSERT INTO "public"."permission_levels" VALUES ('4', 'سطح متوسط رو به پایین', '4');
INSERT INTO "public"."permission_levels" VALUES ('5', 'سطح پایین', '5');
COMMIT;

-- ----------------------------
--  Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS "public"."roles";
CREATE TABLE "public"."roles" (
	"id" varchar(10) NOT NULL COLLATE "default",
	"name" varchar(100) NOT NULL COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."roles" OWNER TO "hadi";

-- ----------------------------
--  Records of roles
-- ----------------------------
BEGIN;
INSERT INTO "public"."roles" VALUES ('1', 'مدیر عامل');
INSERT INTO "public"."roles" VALUES ('2', 'کارمند عادی');
INSERT INTO "public"."roles" VALUES ('3', 'حساب دار');
INSERT INTO "public"."roles" VALUES ('4', 'منشی');
COMMIT;

-- ----------------------------
--  Table structure for employees
-- ----------------------------
DROP TABLE IF EXISTS "public"."employees";
CREATE TABLE "public"."employees" (
	"id" varchar(10) NOT NULL COLLATE "default",
	"role_id" varchar(10) COLLATE "default",
	"permission_id" varchar(10) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."employees" OWNER TO "hadi";

-- ----------------------------
--  Records of employees
-- ----------------------------
BEGIN;
INSERT INTO "public"."employees" VALUES ('2', '1', '1');
INSERT INTO "public"."employees" VALUES ('3', '4', '5');
INSERT INTO "public"."employees" VALUES ('4', '2', '3');
INSERT INTO "public"."employees" VALUES ('5', '3', '4');
INSERT INTO "public"."employees" VALUES ('6', '2', '3');
COMMIT;

-- ----------------------------
--  Table structure for projects
-- ----------------------------
DROP TABLE IF EXISTS "public"."projects";
CREATE TABLE "public"."projects" (
	"id" varchar(10) NOT NULL COLLATE "default",
	"title" varchar(100) NOT NULL COLLATE "default",
	"description" text NOT NULL COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."projects" OWNER TO "hadi";

-- ----------------------------
--  Table structure for project_activities
-- ----------------------------
DROP TABLE IF EXISTS "public"."project_activities";
CREATE TABLE "public"."project_activities" (
	"id" varchar(10) NOT NULL COLLATE "default",
	"project_id" varchar(10) NOT NULL COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."project_activities" OWNER TO "hadi";

-- ----------------------------
--  Table structure for votes
-- ----------------------------
DROP TABLE IF EXISTS "public"."votes";
CREATE TABLE "public"."votes" (
	"employee_id" varchar(10) NOT NULL COLLATE "default",
	"knowledge_id" varchar(10) NOT NULL COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."votes" OWNER TO "hadi";

-- ----------------------------
--  Records of votes
-- ----------------------------
BEGIN;
INSERT INTO "public"."votes" VALUES ('2', '3');
INSERT INTO "public"."votes" VALUES ('6', '3');
INSERT INTO "public"."votes" VALUES ('5', '2');
INSERT INTO "public"."votes" VALUES ('3', '1');
COMMIT;

-- ----------------------------
--  Table structure for letters
-- ----------------------------
DROP TABLE IF EXISTS "public"."letters";
CREATE TABLE "public"."letters" (
	"id" varchar(10) NOT NULL COLLATE "default",
	"title" varchar(100) NOT NULL COLLATE "default",
	"content" text NOT NULL COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."letters" OWNER TO "hadi";

-- ----------------------------
--  Table structure for letter_path_nodes
-- ----------------------------
DROP TABLE IF EXISTS "public"."letter_path_nodes";
CREATE TABLE "public"."letter_path_nodes" (
	"id" varchar(10) NOT NULL COLLATE "default",
	"letter_id" varchar(10) NOT NULL COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."letter_path_nodes" OWNER TO "hadi";

-- ----------------------------
--  Table structure for tags
-- ----------------------------
DROP TABLE IF EXISTS "public"."tags";
CREATE TABLE "public"."tags" (
	"id" varchar(10) NOT NULL COLLATE "default",
	"name" varchar(100) NOT NULL COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."tags" OWNER TO "hadi";

-- ----------------------------
--  Records of tags
-- ----------------------------
BEGIN;
INSERT INTO "public"."tags" VALUES ('1', 'مدیریت دانش');
INSERT INTO "public"."tags" VALUES ('2', 'رایانه');
INSERT INTO "public"."tags" VALUES ('3', 'حسابداری');
COMMIT;

-- ----------------------------
--  Table structure for knowledge_tag
-- ----------------------------
DROP TABLE IF EXISTS "public"."knowledge_tag";
CREATE TABLE "public"."knowledge_tag" (
	"knowledege_id" varchar(10) NOT NULL COLLATE "default",
	"tag_id" varchar(10) NOT NULL COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."knowledge_tag" OWNER TO "hadi";

-- ----------------------------
--  Records of knowledge_tag
-- ----------------------------
BEGIN;
INSERT INTO "public"."knowledge_tag" VALUES ('1', '1');
INSERT INTO "public"."knowledge_tag" VALUES ('2', '2');
COMMIT;

-- ----------------------------
--  Table structure for managers
-- ----------------------------
DROP TABLE IF EXISTS "public"."managers";
CREATE TABLE "public"."managers" (
	"id" varchar(10) NOT NULL COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."managers" OWNER TO "hadi";

-- ----------------------------
--  Records of managers
-- ----------------------------
BEGIN;
INSERT INTO "public"."managers" VALUES ('1');
COMMIT;

-- ----------------------------
--  Table structure for questions
-- ----------------------------
DROP TABLE IF EXISTS "public"."questions";
CREATE TABLE "public"."questions" (
	"id" varchar(10) NOT NULL COLLATE "default",
	"title" varchar(100) NOT NULL COLLATE "default",
	"content" text NOT NULL COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."questions" OWNER TO "hadi";

-- ----------------------------
--  Records of questions
-- ----------------------------
BEGIN;
INSERT INTO "public"."questions" VALUES ('2', 'چگونه با رایانه کار می‌کنند؟', '<p>سلام،</p><p>من منشی جدید شرکت، عارف شفاعی هستم</p><p>متسافانه کار با رایانه را بلد نیستم، لطفا یکی تجربیات خود در این زمینه را در اختیار من بگذارد</p><p>با تشکر</p>');
COMMIT;

-- ----------------------------
--  Table structure for answers
-- ----------------------------
DROP TABLE IF EXISTS "public"."answers";
CREATE TABLE "public"."answers" (
	"id" varchar(10) NOT NULL COLLATE "default",
	"question_id" varchar(10) NOT NULL COLLATE "default",
	"content" text NOT NULL COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."answers" OWNER TO "hadi";

-- ----------------------------
--  Records of answers
-- ----------------------------
BEGIN;
INSERT INTO "public"."answers" VALUES ('3', '2', '<p>سلام عارف جان، به شرکت خوش اومدی</p> <p>متاسفانه باید بگم اگر کار با رایانه بلد نیستی اخراج می‌شوی</p><p>از اینکه این مدت با شما کار کردم بسیار خوشحالم، امیدوارم از کار بعدی خوشتان بیاید</p>');
COMMIT;

-- ----------------------------
--  Table structure for wikiknowledges
-- ----------------------------
DROP TABLE IF EXISTS "public"."wikiknowledges";
CREATE TABLE "public"."wikiknowledges" (
	"id" varchar(10) NOT NULL COLLATE "default",
	"title" varchar(100) NOT NULL COLLATE "default",
	"content" text NOT NULL COLLATE "default",
	"attachment" varchar(100) COLLATE "default",
	"is_approved" bool NOT NULL DEFAULT false,
	"is_deprecated" bool NOT NULL DEFAULT false,
	"usecases" text COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."wikiknowledges" OWNER TO "hadi";

-- ----------------------------
--  Records of wikiknowledges
-- ----------------------------
BEGIN;
INSERT INTO "public"."wikiknowledges" VALUES ('1', 'نحوه استفاده از سامانه مدیریت دانش', 'نحوه استفاده از سامانه بدین صورت است که ....', null, 't', 'f', null);
COMMIT;

-- ----------------------------
--  Primary key structure for table abuse_reports
-- ----------------------------
ALTER TABLE "public"."abuse_reports" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table users
-- ----------------------------
ALTER TABLE "public"."users" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table users
-- ----------------------------
CREATE UNIQUE INDEX  "users_id_key" ON "public"."users" USING btree("id" COLLATE "default" ASC NULLS LAST);

-- ----------------------------
--  Primary key structure for table knowledges
-- ----------------------------
ALTER TABLE "public"."knowledges" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table knowledges
-- ----------------------------
CREATE UNIQUE INDEX  "knowledges_id_key" ON "public"."knowledges" USING btree("id" COLLATE "default" ASC NULLS LAST);

-- ----------------------------
--  Primary key structure for table permission_levels
-- ----------------------------
ALTER TABLE "public"."permission_levels" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table permission_levels
-- ----------------------------
CREATE UNIQUE INDEX  "permission_levels_id_key" ON "public"."permission_levels" USING btree("id" COLLATE "default" ASC NULLS LAST);

-- ----------------------------
--  Primary key structure for table roles
-- ----------------------------
ALTER TABLE "public"."roles" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table roles
-- ----------------------------
CREATE UNIQUE INDEX  "roles_id_key" ON "public"."roles" USING btree("id" COLLATE "default" ASC NULLS LAST);

-- ----------------------------
--  Primary key structure for table employees
-- ----------------------------
ALTER TABLE "public"."employees" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table employees
-- ----------------------------
CREATE UNIQUE INDEX  "employees_id_key" ON "public"."employees" USING btree("id" COLLATE "default" ASC NULLS LAST);

-- ----------------------------
--  Primary key structure for table projects
-- ----------------------------
ALTER TABLE "public"."projects" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table projects
-- ----------------------------
CREATE UNIQUE INDEX  "projects_id_key" ON "public"."projects" USING btree("id" COLLATE "default" ASC NULLS LAST);

-- ----------------------------
--  Primary key structure for table project_activities
-- ----------------------------
ALTER TABLE "public"."project_activities" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table votes
-- ----------------------------
ALTER TABLE "public"."votes" ADD PRIMARY KEY ("employee_id", "knowledge_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table letters
-- ----------------------------
ALTER TABLE "public"."letters" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table letters
-- ----------------------------
CREATE UNIQUE INDEX  "letters_id_key" ON "public"."letters" USING btree("id" COLLATE "default" ASC NULLS LAST);

-- ----------------------------
--  Primary key structure for table letter_path_nodes
-- ----------------------------
ALTER TABLE "public"."letter_path_nodes" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table tags
-- ----------------------------
ALTER TABLE "public"."tags" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table tags
-- ----------------------------
CREATE UNIQUE INDEX  "tags_id_key" ON "public"."tags" USING btree("id" COLLATE "default" ASC NULLS LAST);

-- ----------------------------
--  Primary key structure for table knowledge_tag
-- ----------------------------
ALTER TABLE "public"."knowledge_tag" ADD PRIMARY KEY ("knowledege_id", "tag_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table managers
-- ----------------------------
ALTER TABLE "public"."managers" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table questions
-- ----------------------------
ALTER TABLE "public"."questions" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table questions
-- ----------------------------
CREATE UNIQUE INDEX  "questions_id_key" ON "public"."questions" USING btree("id" COLLATE "default" ASC NULLS LAST);

-- ----------------------------
--  Primary key structure for table answers
-- ----------------------------
ALTER TABLE "public"."answers" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table wikiknowledges
-- ----------------------------
ALTER TABLE "public"."wikiknowledges" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table abuse_reports
-- ----------------------------
ALTER TABLE "public"."abuse_reports" ADD CONSTRAINT "knowledge" FOREIGN KEY ("knowledge_id") REFERENCES "public"."knowledges" ("id") ON UPDATE NO ACTION ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."abuse_reports" ADD CONSTRAINT "employee" FOREIGN KEY ("employee_id") REFERENCES "public"."employees" ("id") ON UPDATE NO ACTION ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table knowledges
-- ----------------------------
ALTER TABLE "public"."knowledges" ADD CONSTRAINT "employee" FOREIGN KEY ("employee_id") REFERENCES "public"."employees" ("id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table employees
-- ----------------------------
ALTER TABLE "public"."employees" ADD CONSTRAINT "id" FOREIGN KEY ("id") REFERENCES "public"."users" ("id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."employees" ADD CONSTRAINT "permission" FOREIGN KEY ("permission_id") REFERENCES "public"."permission_levels" ("id") ON UPDATE NO ACTION ON DELETE SET NULL NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."employees" ADD CONSTRAINT "role" FOREIGN KEY ("role_id") REFERENCES "public"."roles" ("id") ON UPDATE NO ACTION ON DELETE SET NULL NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table project_activities
-- ----------------------------
ALTER TABLE "public"."project_activities" ADD CONSTRAINT "project" FOREIGN KEY ("project_id") REFERENCES "public"."projects" ("id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table votes
-- ----------------------------
ALTER TABLE "public"."votes" ADD CONSTRAINT "employee" FOREIGN KEY ("employee_id") REFERENCES "public"."employees" ("id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."votes" ADD CONSTRAINT "knowledge" FOREIGN KEY ("knowledge_id") REFERENCES "public"."knowledges" ("id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table letter_path_nodes
-- ----------------------------
ALTER TABLE "public"."letter_path_nodes" ADD CONSTRAINT "letter" FOREIGN KEY ("letter_id") REFERENCES "public"."letters" ("id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table knowledge_tag
-- ----------------------------
ALTER TABLE "public"."knowledge_tag" ADD CONSTRAINT "knowledge" FOREIGN KEY ("knowledege_id") REFERENCES "public"."knowledges" ("id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."knowledge_tag" ADD CONSTRAINT "tag" FOREIGN KEY ("tag_id") REFERENCES "public"."tags" ("id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table managers
-- ----------------------------
ALTER TABLE "public"."managers" ADD CONSTRAINT "id" FOREIGN KEY ("id") REFERENCES "public"."users" ("id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table questions
-- ----------------------------
ALTER TABLE "public"."questions" ADD CONSTRAINT "id" FOREIGN KEY ("id") REFERENCES "public"."knowledges" ("id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table answers
-- ----------------------------
ALTER TABLE "public"."answers" ADD CONSTRAINT "id" FOREIGN KEY ("id") REFERENCES "public"."knowledges" ("id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."answers" ADD CONSTRAINT "question" FOREIGN KEY ("question_id") REFERENCES "public"."questions" ("id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table wikiknowledges
-- ----------------------------
ALTER TABLE "public"."wikiknowledges" ADD CONSTRAINT "id" FOREIGN KEY ("id") REFERENCES "public"."knowledges" ("id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

