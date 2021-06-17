/*
 * データベース(webbook)と
 * ユーザ(webbookuser)の作成
 */
DROP DATABASE IF EXISTS webbook;
DROP USER IF EXISTS webbookuser;
CREATE USER webbookuser WITH PASSWORD 'himitu';
CREATE DATABASE webbook OWNER webbookuser ENCODING 'UTF8';
\c webbook

-- 利用者テーブルの作成
CREATE TABLE member (
	user_id SERIAL PRIMARY KEY,
	user_family_name VARCHAR(10) NOT NULL,
	user_name VARCHAR(10) NOT NULL,
	user_postal CHAR(7) NOT NULL,
	user_address VARCHAR(100) NOT NULL,
	user_tel VARCHAR(20) NOT NULL,
	user_email VARCHAR(100) NOT NULL,
	user_birthday DATE NOT NULL,
	user_password VARCHAR(12) DEFAULT 'himitu' NOT NULL,
	user_role CHAR(1) NOT NULL
);

-- テーブルの所有者設定
ALTER TABLE member OWNER TO webbookuser;

-- 利用者テーブルのサンプルデータ
INSERT INTO member VALUES(1, '阿井', '太郎', '1000000', '東京都', '090-1111-1111', 'ai@dd.co.jp', '1984-10-01', DEFAULT, 1);
INSERT INTO member VALUES(2, '伊田', '次郎', '1100000', '千葉県', '090-2222-2222', 'ida@dd.co.jp', '1954-10-2', DEFAULT, 1);
INSERT INTO member VALUES(3, '宇田', '三郎', '1200000', '滋賀県', '090-3333-3333', 'uda@dd.co.jp', '1939-10-3', DEFAULT, 1);
