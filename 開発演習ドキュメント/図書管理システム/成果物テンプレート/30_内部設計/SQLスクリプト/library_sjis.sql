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
	is_staff BOOLEAN DEFAULT '0' NOT NULL,
    joined_at DATE DEFAULT CURRENT_DATE NOT NULL,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    canceled_at DATE DEFAULT NULL
);

-- テーブルの所有者設定
ALTER TABLE member OWNER TO libraryuser;

-- ユーザ台帳テーブルのデータ
---- 職員
INSERT INTO member(member_name, member_address, member_tel, member_email, member_password, member_birthday, is_staff, joined_at, updated_at, canceled_at) VALUES('木田浩志', '東京都千代田区有楽町3-9-7', '0893508232', 'kouji798@gyayvcqea.dml', DEFAULT, '1983-07-13', '1', DEFAULT, DEFAULT, DEFAULT);
---- 利用者
INSERT INTO member(member_name, member_address, member_tel, member_email, member_password, member_birthday, is_staff, joined_at, updated_at, canceled_at) VALUES('笠井成美', '神奈川県足柄上郡大井町柳3-1-5', '0616085193', 'Narumi_Kasai@zana.zazaf.nd', DEFAULT, '1964-01-21', '0', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO member(member_name, member_address, member_tel, member_email, member_password, member_birthday, is_staff, joined_at, updated_at, canceled_at) VALUES('川端由紀江', '神奈川県小田原市多古3-19', '0953395747', 'yukie1281@zdiczyosqw.rw', DEFAULT, '1992-01-31', '0', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO member(member_name, member_address, member_tel, member_email, member_password, member_birthday, is_staff, joined_at, updated_at, canceled_at) VALUES('南部広行', '東京都新宿区二十騎町3-7-10', '0279154153', 'hiroyuki5474@rcuwbue.tatz.xmn', DEFAULT, '1988-12-15', '0', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO member(member_name, member_address, member_tel, member_email, member_password, member_birthday, is_staff, joined_at, updated_at, canceled_at) VALUES('大畑沙羅', '神奈川県川崎市川崎区観音4-20-5', '0308631746', 'sara0451@eowlw.he', DEFAULT, '1995-10-20', '0', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO member(member_name, member_address, member_tel, member_email, member_password, member_birthday, is_staff, joined_at, updated_at, canceled_at) VALUES('宮内良雄', '東京都新宿区愛住町3-13', '0363683792', 'omiyauchi@dmohvkbc.ul', DEFAULT, '1978-02-03', '0', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO member(member_name, member_address, member_tel, member_email, member_password, member_birthday, is_staff, joined_at, updated_at, canceled_at) VALUES('河崎俊子', '東京都西多摩郡檜原村本宿3-2', '0446542057', 'mtoshiko@ddypiugrkm.umzuu.lu', DEFAULT, '1968-06-27', '0', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO member(member_name, member_address, member_tel, member_email, member_password, member_birthday, is_staff, joined_at, updated_at, canceled_at) VALUES('増山明弘', '東京都千代田区神田多町2-17-14', '0466138770', 'akihiro68445@ztrviehxl.hem', DEFAULT, '1984-09-12', '0', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO member(member_name, member_address, member_tel, member_email, member_password, member_birthday, is_staff, joined_at, updated_at, canceled_at) VALUES('末永勇次', '東京都豊島区西池袋3-19-1', '0300163171', 'zonfinpjxppuftyuuji6303@xkdt.hz', DEFAULT, '1992-03-14', '0', DEFAULT, DEFAULT, DEFAULT);

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
    deleted_at TIMESTAMP DEFAULT NULL
);

-- テーブルの所有者設定
ALTER TABLE document_catalog OWNER TO libraryuser;

-- 資料目録テーブルのデータ
INSERT INTO document_catalog VALUES('4906638015', '7つの習慣', 3, 'スティーブン・R・コビー', 'キングベアー出版', '1996-12-25', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO document_catalog VALUES('4480425993', 'よいこの君主論', 1, '架神恭介/辰巳一世', '筑摩書房', '2009-05-11', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO document_catalog VALUES('4309029167', '推し、燃ゆ', 9, '宇佐見りん', '河出書房新社', '2020-09-10', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO document_catalog VALUES('4309415083', 'JR上野駅公園口', 9, '柳美里', '河出文庫', '2017-02-07', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO document_catalog VALUES('4101006059', '人間失格', 9, '太宰治', '新潮社', '2006-01-01', DEFAULT, DEFAULT, DEFAULT);

-----------------------------------------------------------
-----------------------------------------------------------

-- 3. document_ledger
-- 資料台帳テーブルの作成
CREATE TABLE document_ledger (
    document_id SERIAL PRIMARY KEY,
    isbn_no VARCHAR(15) NOT NULL,
    note VARCHAR(255),
    added_at DATE DEFAULT CURRENT_DATE NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    discarded_at DATE DEFAULT NULL
);

-- テーブルの所有者設定
ALTER TABLE document_ledger OWNER TO libraryuser;

-- 資料台帳テーブルのサンプルデータ
----7つの習慣(document_id:1~3)
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4906638015', '劣化のため廃棄', '1999-4-8', DEFAULT, '2003-8-5');
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4906638015', NULL, '2004-1-7', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4906638015', NULL, '2004-1-7', DEFAULT, DEFAULT);
----よいこの君主論(document_id:4~8)
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4480425993', NULL, '2015-10-18', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4480425993', NULL, '2015-10-18', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4480425993', NULL, '2015-10-18', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4480425993', NULL, '2015-10-18', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4480425993', NULL, '2015-10-18', DEFAULT, DEFAULT);
----推し、燃ゆ(document_id:9~11)
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4309029167', NULL, '2021-3-10', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4309029167', NULL, '2021-3-10', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4309029167', NULL, '2021-3-10', DEFAULT, DEFAULT);
----JR上野駅公園口(document_id:12~14)
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4309415083', NULL, '2020-4-1', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4309415083', NULL, '2020-4-1', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4309415083', NULL, '2020-4-1', DEFAULT, DEFAULT);
----人間失格(document_id:15~19)
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4101006059', NULL, '2020-4-3', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4101006059', NULL, '2020-4-3', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4101006059', NULL, '2020-4-3', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4101006059', NULL, '2020-4-3', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4101006059', NULL, '2020-4-3', DEFAULT, DEFAULT);


-----------------------------------------------------------
-----------------------------------------------------------

--4. lending_ledger
-- 貸出台帳テーブルの作成
CREATE TABLE lending_ledger (
	member_id SERIAL,
    document_id INTEGER,
    return_deadline DATE NOT NULL,
    note VARCHAR(255),
    lent_at DATE DEFAULT CURRENT_DATE NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    returned_at DATE DEFAULT NULL
);

-- テーブルの所有者設定
ALTER TABLE lending_ledger OWNER TO libraryuser;

-- 貸出台帳テーブルのサンプルデータ
----返却済
INSERT INTO lending_ledger VALUES(2, '1', '2021-06-30', NULL, '2021-06-15', DEFAULT, '2021-06-22');
INSERT INTO lending_ledger VALUES(2, '10', '2021-06-30', NULL, '2021-06-15', DEFAULT, '2021-06-22');
INSERT INTO lending_ledger VALUES(3, '2', '2021-06-30', NULL, '2021-06-15', DEFAULT, '2021-06-22');
----貸出中
INSERT INTO lending_ledger VALUES(4, '3', '2021-07-1', NULL, '2021-06-16', DEFAULT, DEFAULT);
INSERT INTO lending_ledger VALUES(4, '5', '2021-07-1', NULL, '2021-06-16', DEFAULT, DEFAULT);
INSERT INTO lending_ledger VALUES(4, '9', '2021-07-1', NULL, '2021-06-16', DEFAULT, DEFAULT);
INSERT INTO lending_ledger VALUES(5, '11', '2021-07-1', NULL, '2021-06-16', DEFAULT, DEFAULT);
------会員ID=9に5冊貸出中（退会不可）
INSERT INTO lending_ledger VALUES(9, '15', '2021-07-1', NULL, '2021-06-16', DEFAULT, DEFAULT);
INSERT INTO lending_ledger VALUES(9, '16', '2021-07-1', NULL, '2021-06-16', DEFAULT, DEFAULT);
INSERT INTO lending_ledger VALUES(9, '17', '2021-07-1', NULL, '2021-06-16', DEFAULT, DEFAULT);
INSERT INTO lending_ledger VALUES(9, '18', '2021-07-1', NULL, '2021-06-16', DEFAULT, DEFAULT);
INSERT INTO lending_ledger VALUES(9, '19', '2021-07-1', NULL, '2021-06-16', DEFAULT, DEFAULT);
----延滞中
INSERT INTO lending_ledger VALUES(6, '12', '2021-06-16', NULL, '2021-06-1', DEFAULT, DEFAULT);
INSERT INTO lending_ledger VALUES(6, '4', '2021-06-16', NULL, '2021-06-1', DEFAULT, DEFAULT);
INSERT INTO lending_ledger VALUES(7, '14', '2021-06-17', NULL, '2021-06-2', DEFAULT, DEFAULT);

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