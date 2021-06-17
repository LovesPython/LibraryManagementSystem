/*
 * データベース(library)と
 * ユーザ(libraryuser)の作成
 */
DROP DATABASE IF EXISTS library;
DROP USER IF EXISTS libraryuser;
CREATE USER libraryuser WITH PASSWORD 'himitu';
CREATE DATABASE library OWNER libraryuser ENCODING 'UTF8';
\c library

-- 1. member
-- ユーザ台帳テーブルの作成
CREATE TABLE member (
	member_id SERIAL PRIMARY KEY,
	member_name VARCHAR(255) NOT NULL,
	member_address VARCHAR(255) NOT NULL,
	member_tel VARCHAR(15) NOT NULL,
	member_email VARCHAR(255) NOT NULL,
	member_password VARCHAR(15) DEFAULT 'himitu' NOT NULL,
	member_birthday DATE NOT NULL,
	joined_at DATE DEFAULT CURRENT_DATE NOT NULL,
	canceled_at DATE,
	is_staff BOOLEAN DEFAULT '0' NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP DEFAULT null
);

-- テーブルの所有者設定
ALTER TABLE member OWNER TO libraryuser;

-- ユーザ台帳テーブルのデータ
INSERT INTO member(member_name, member_address, member_tel, member_email, member_password, member_birthday, joined_at, canceled_at, is_staff, created_at, updated_at, deleted_at) VALUES('木田浩志', '愛媛県宇和島市桜町4-11', '0893508232', 'kouji798@gyayvcqea.dml', DEFAULT, '1983-07-13', '2010-01-01', null, '1', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO member(member_name, member_address, member_tel, member_email, member_password, member_birthday, joined_at, canceled_at, is_staff, created_at, updated_at, deleted_at) VALUES('笠井成美', '大阪府枚方市印田町3-20', '0616085193', 'Narumi_Kasai@zana.zazaf.nd', DEFAULT, '1964-01-21', DEFAULT, null, '0', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO member(member_name, member_address, member_tel, member_email, member_password, member_birthday, joined_at, canceled_at, is_staff, created_at, updated_at, deleted_at) VALUES('川端由紀江', '長崎県佐世保市宇久町木場2-9-12', '0953395747', 'yukie1281@zdiczyosqw.rw', DEFAULT, '1992-01-31', DEFAULT, null, '0', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO member(member_name, member_address, member_tel, member_email, member_password, member_birthday, joined_at, canceled_at, is_staff, created_at, updated_at, deleted_at) VALUES('南部広行', '群馬県邑楽郡板倉町朝日野3-9', '0279154153', 'hiroyuki5474@rcuwbue.tatz.xmn', DEFAULT, '1988-12-15', DEFAULT, null, '0', DEFAULT, DEFAULT, DEFAULT);

-----------------------------------------------------------
-----------------------------------------------------------

-- 2. document_catalog
-- 資料目録テーブルの作成
CREATE TABLE document_catalog (
	isbn_no VARCHAR(15) PRIMARY KEY,
    document_name VARCHAR(255) NOT NULL,
    category_code INTEGER NOT NULL,
    author VARCHAR(255) NOT NULL,
    publisher VARCHAR(255),
    published_at DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP DEFAULT null
);

-- テーブルの所有者設定
ALTER TABLE document_catalog OWNER TO libraryuser;

-- 資料目録テーブルのデータ
INSERT INTO document_catalog VALUES('4906638015', '7つの習慣', 3, 'スティーブン・R・コビー', 'キングベアー出版', '1996-12-25', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO document_catalog VALUES('4480425993', 'よいこの君主論', 1, '架神恭介/辰巳一世', '筑摩書房', '2009-05-11', DEFAULT, DEFAULT, DEFAULT);

-----------------------------------------------------------
-----------------------------------------------------------

-- 3. document_ledger
-- 資料台帳テーブルの作成
CREATE TABLE document_ledger (
    document_id SERIAL PRIMARY KEY,
    isbn_no VARCHAR(15) NOT NULL,
    added_at DATE DEFAULT CURRENT_DATE NOT NULL,
    discarded_at DATE,
    note VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP DEFAULT null
);

-- テーブルの所有者設定
ALTER TABLE document_ledger OWNER TO libraryuser;

-- 資料台帳テーブルのサンプルデータ
INSERT INTO document_ledger(isbn_no, added_at, discarded_at, note, created_at, updated_at, deleted_at) VALUES('1234567890', '1995-4-8', '2003-8-5', '劣化のため廃棄', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, added_at, discarded_at, note, created_at, updated_at, deleted_at) VALUES('4906638015', '2010-1-7', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, added_at, discarded_at, note, created_at, updated_at, deleted_at) VALUES('4906638015', '2010-1-7', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, added_at, discarded_at, note, created_at, updated_at, deleted_at) VALUES('4480425993', '2015-10-18', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, added_at, discarded_at, note, created_at, updated_at, deleted_at) VALUES('4480425993', '2015-10-18', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, added_at, discarded_at, note, created_at, updated_at, deleted_at) VALUES('4480425993', '2015-10-18', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);

-----------------------------------------------------------
-----------------------------------------------------------

--4. lending_ledger
-- 貸出台帳テーブルの作成
CREATE TABLE lending_ledger (
	member_id SERIAL,
    document_id INTEGER,
    lent_at DATE DEFAULT CURRENT_DATE NOT NULL,
    return_deadline DATE NOT NULL,
    returned_at DATE,
    note VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP DEFAULT null
);

-- テーブルの所有者設定
ALTER TABLE lending_ledger OWNER TO libraryuser;

-- 貸出台帳テーブルのサンプルデータ
INSERT INTO lending_ledger VALUES(2, '12345', '2010-06-15', '2010-06-30', '2010-06-22', NULL, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO lending_ledger VALUES(2, '65489', '2010-06-15', '2010-06-30', '2010-06-22', NULL, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO lending_ledger VALUES(2, '30570', '2010-06-15', '2010-06-30', '2010-06-22', NULL, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO lending_ledger VALUES(3, '36980', '2010-06-16', '2010-07-01', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);

-----------------------------------------------------------
-----------------------------------------------------------

-- 5. category_code
-- 分類コードテーブルの作成
CREATE TABLE category_code (
    code INTEGER PRIMARY KEY,
    category_name VARCHAR(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP DEFAULT null
);

-- テーブルの所有者設定
ALTER TABLE category_code OWNER TO libraryuser;

-- 分類コードテーブルのデータ（固定値）
INSERT INTO category_code VALUES(0, '総記', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO category_code VALUES(1, '哲学', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO category_code VALUES(2, '歴史', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO category_code VALUES(3, '社会科学', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO category_code VALUES(4, '自然科学', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO category_code VALUES(5, '技術', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO category_code VALUES(6, '産業', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO category_code VALUES(7, '芸術', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO category_code VALUES(8, '言語', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO category_code VALUES(9, '文学', DEFAULT, DEFAULT, DEFAULT);