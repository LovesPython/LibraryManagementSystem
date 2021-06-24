/*
 * �f�[�^�x�[�X(library)��
 * ���[�U(libraryuser)�̍쐬
 */
DROP DATABASE IF EXISTS library;
DROP USER IF EXISTS libraryuser;
CREATE USER libraryuser WITH PASSWORD 'himitu';
CREATE DATABASE library OWNER libraryuser ENCODING 'UTF8';
\c library

-- 1. member
-- ���[�U�䒠�e�[�u���̍쐬
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

-- �e�[�u���̏��L�Ґݒ�
ALTER TABLE member OWNER TO libraryuser;

-- ���[�U�䒠�e�[�u���̃f�[�^
---- �E��
INSERT INTO member(member_name, member_address, member_tel, member_email, member_password, member_birthday, is_staff, joined_at, updated_at, canceled_at) VALUES('�ؓc�_�u', '�����s���c��L�y��3-9-7', '0893508232', 'kouji798@gyayvcqea.dml', DEFAULT, '1983-07-13', '1', DEFAULT, DEFAULT, DEFAULT);
---- ���p��
INSERT INTO member(member_name, member_address, member_tel, member_email, member_password, member_birthday, is_staff, joined_at, updated_at, canceled_at) VALUES('�}�䐬��', '�_�ސ쌧������S��䒬��3-1-5', '0616085193', 'Narumi_Kasai@zana.zazaf.nd', DEFAULT, '1964-01-21', '0', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO member(member_name, member_address, member_tel, member_email, member_password, member_birthday, is_staff, joined_at, updated_at, canceled_at) VALUES('��[�R�I�]', '�_�ސ쌧���c���s����3-19', '0953395747', 'yukie1281@zdiczyosqw.rw', DEFAULT, '1992-01-31', '0', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO member(member_name, member_address, member_tel, member_email, member_password, member_birthday, is_staff, joined_at, updated_at, canceled_at) VALUES('�암�L�s', '�����s�V�h���\�R��3-7-10', '0279154153', 'hiroyuki5474@rcuwbue.tatz.xmn', DEFAULT, '1988-12-15', '0', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO member(member_name, member_address, member_tel, member_email, member_password, member_birthday, is_staff, joined_at, updated_at, canceled_at) VALUES('�唨����', '�_�ސ쌧���s����ω�4-20-5', '0308631746', 'sara0451@eowlw.he', DEFAULT, '1995-10-20', '0', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO member(member_name, member_address, member_tel, member_email, member_password, member_birthday, is_staff, joined_at, updated_at, canceled_at) VALUES('�{���ǗY', '�����s�V�h�戤�Z��3-13', '0363683792', 'omiyauchi@dmohvkbc.ul', DEFAULT, '1978-02-03', '0', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO member(member_name, member_address, member_tel, member_email, member_password, member_birthday, is_staff, joined_at, updated_at, canceled_at) VALUES('�͍�r�q', '�����s�������S�w�����{�h3-2', '0446542057', 'mtoshiko@ddypiugrkm.umzuu.lu', DEFAULT, '1968-06-27', '0', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO member(member_name, member_address, member_tel, member_email, member_password, member_birthday, is_staff, joined_at, updated_at, canceled_at) VALUES('���R���O', '�����s���c��_�c����2-17-14', '0466138770', 'akihiro68445@ztrviehxl.hem', DEFAULT, '1984-09-12', '0', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO member(member_name, member_address, member_tel, member_email, member_password, member_birthday, is_staff, joined_at, updated_at, canceled_at) VALUES('���i�E��', '�����s�L���搼�r��3-19-1', '0300163171', 'zonfinpjxppuftyuuji6303@xkdt.hz', DEFAULT, '1992-03-14', '0', DEFAULT, DEFAULT, DEFAULT);

-----------------------------------------------------------
-----------------------------------------------------------

-- 2. document_catalog
-- �����ژ^�e�[�u���̍쐬
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

-- �e�[�u���̏��L�Ґݒ�
ALTER TABLE document_catalog OWNER TO libraryuser;

-- �����ژ^�e�[�u���̃f�[�^
INSERT INTO document_catalog VALUES('4906638015', '7�̏K��', 3, '�X�e�B�[�u���ER�E�R�r�[', '�L���O�x�A�[�o��', '1996-12-25', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO document_catalog VALUES('4480425993', '�悢���̌N��_', 1, '�ː_����/�C���ꐢ', '�}�����[', '2009-05-11', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO document_catalog VALUES('4309029167', '�����A�R��', 9, '�F�������', '�͏o���[�V��', '2020-09-10', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO document_catalog VALUES('4309415083', 'JR���w������', 9, '������', '�͏o����', '2017-02-07', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO document_catalog VALUES('4101006059', '�l�Ԏ��i', 9, '���Ɏ�', '�V����', '2006-01-01', DEFAULT, DEFAULT, DEFAULT);

-----------------------------------------------------------
-----------------------------------------------------------

-- 3. document_ledger
-- �����䒠�e�[�u���̍쐬
CREATE TABLE document_ledger (
    document_id SERIAL PRIMARY KEY,
    isbn_no VARCHAR(15) NOT NULL,
    note VARCHAR(255),
    added_at DATE DEFAULT CURRENT_DATE NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    discarded_at DATE DEFAULT NULL
);

-- �e�[�u���̏��L�Ґݒ�
ALTER TABLE document_ledger OWNER TO libraryuser;

-- �����䒠�e�[�u���̃T���v���f�[�^
----7�̏K��(document_id:1~3)
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4906638015', '�򉻂̂��ߔp��', '1999-4-8', DEFAULT, '2003-8-5');
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4906638015', NULL, '2004-1-7', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4906638015', NULL, '2004-1-7', DEFAULT, DEFAULT);
----�悢���̌N��_(document_id:4~8)
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4480425993', NULL, '2015-10-18', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4480425993', NULL, '2015-10-18', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4480425993', NULL, '2015-10-18', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4480425993', NULL, '2015-10-18', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4480425993', NULL, '2015-10-18', DEFAULT, DEFAULT);
----�����A�R��(document_id:9~11)
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4309029167', NULL, '2021-3-10', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4309029167', NULL, '2021-3-10', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4309029167', NULL, '2021-3-10', DEFAULT, DEFAULT);
----JR���w������(document_id:12~14)
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4309415083', NULL, '2020-4-1', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4309415083', NULL, '2020-4-1', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4309415083', NULL, '2020-4-1', DEFAULT, DEFAULT);
----�l�Ԏ��i(document_id:15~19)
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4101006059', NULL, '2020-4-3', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4101006059', NULL, '2020-4-3', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4101006059', NULL, '2020-4-3', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4101006059', NULL, '2020-4-3', DEFAULT, DEFAULT);
INSERT INTO document_ledger(isbn_no, note, added_at, updated_at, discarded_at) VALUES('4101006059', NULL, '2020-4-3', DEFAULT, DEFAULT);


-----------------------------------------------------------
-----------------------------------------------------------

--4. lending_ledger
-- �ݏo�䒠�e�[�u���̍쐬
CREATE TABLE lending_ledger (
	member_id SERIAL,
    document_id INTEGER,
    return_deadline DATE NOT NULL,
    note VARCHAR(255),
    lent_at DATE DEFAULT CURRENT_DATE NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    returned_at DATE DEFAULT NULL
);

-- �e�[�u���̏��L�Ґݒ�
ALTER TABLE lending_ledger OWNER TO libraryuser;

-- �ݏo�䒠�e�[�u���̃T���v���f�[�^
----�ԋp��
INSERT INTO lending_ledger VALUES(2, '1', '2021-06-30', NULL, '2021-06-15', DEFAULT, '2021-06-22');
INSERT INTO lending_ledger VALUES(2, '10', '2021-06-30', NULL, '2021-06-15', DEFAULT, '2021-06-22');
INSERT INTO lending_ledger VALUES(3, '2', '2021-06-30', NULL, '2021-06-15', DEFAULT, '2021-06-22');
----�ݏo��
INSERT INTO lending_ledger VALUES(4, '3', '2021-07-1', NULL, '2021-06-16', DEFAULT, DEFAULT);
INSERT INTO lending_ledger VALUES(4, '5', '2021-07-1', NULL, '2021-06-16', DEFAULT, DEFAULT);
INSERT INTO lending_ledger VALUES(4, '9', '2021-07-1', NULL, '2021-06-16', DEFAULT, DEFAULT);
INSERT INTO lending_ledger VALUES(5, '11', '2021-07-1', NULL, '2021-06-16', DEFAULT, DEFAULT);
------���ID=9��5���ݏo���i�މ�s�j
INSERT INTO lending_ledger VALUES(9, '15', '2021-07-1', NULL, '2021-06-16', DEFAULT, DEFAULT);
INSERT INTO lending_ledger VALUES(9, '16', '2021-07-1', NULL, '2021-06-16', DEFAULT, DEFAULT);
INSERT INTO lending_ledger VALUES(9, '17', '2021-07-1', NULL, '2021-06-16', DEFAULT, DEFAULT);
INSERT INTO lending_ledger VALUES(9, '18', '2021-07-1', NULL, '2021-06-16', DEFAULT, DEFAULT);
INSERT INTO lending_ledger VALUES(9, '19', '2021-07-1', NULL, '2021-06-16', DEFAULT, DEFAULT);
----���ؒ�
INSERT INTO lending_ledger VALUES(6, '12', '2021-06-16', NULL, '2021-06-1', DEFAULT, DEFAULT);
INSERT INTO lending_ledger VALUES(6, '4', '2021-06-16', NULL, '2021-06-1', DEFAULT, DEFAULT);
INSERT INTO lending_ledger VALUES(7, '14', '2021-06-17', NULL, '2021-06-2', DEFAULT, DEFAULT);

-----------------------------------------------------------
-----------------------------------------------------------

-- 5. category_code
-- ���ރR�[�h�e�[�u���̍쐬
CREATE TABLE category_code (
    code INTEGER PRIMARY KEY,
    category_name VARCHAR(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP DEFAULT null
);

-- �e�[�u���̏��L�Ґݒ�
ALTER TABLE category_code OWNER TO libraryuser;

-- ���ރR�[�h�e�[�u���̃f�[�^�i�Œ�l�j
INSERT INTO category_code VALUES(0, '���L', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO category_code VALUES(1, '�N�w', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO category_code VALUES(2, '���j', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO category_code VALUES(3, '�Љ�Ȋw', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO category_code VALUES(4, '���R�Ȋw', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO category_code VALUES(5, '�Z�p', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO category_code VALUES(6, '�Y��', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO category_code VALUES(7, '�|�p', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO category_code VALUES(8, '����', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO category_code VALUES(9, '���w', DEFAULT, DEFAULT, DEFAULT);