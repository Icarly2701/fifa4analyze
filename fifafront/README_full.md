#피파 알려주는 남자



## DB 설정

MariaDB 설치: <https://mariadb.com/downloads/>
DB 암호 : 기본적으로 'system'

### 기본 테이블 생성

`
create database fifa4Analysi;
use fifa4Analysis;

create table matchinfo 
( access_id varchar(500) not null, 
match_id varchar(500) not null, 
match_result varchar(10) not null, 
opp_nickname varchar(50) not null,
match_score varchar(50) not null,
match_date varchar(100) not null,
primary key(match_id) ) 
ENGINE=InnoDB 
DEFAULT CHARSET =utf8; 

create table userinfo 
( nickname varchar(50) not null, 
access_id varchar(500) not null, 
userlevel int(255) not null, 
tier varchar(30) not null,
formation varchar(15) not null,
primary key(access_id) ) 
ENGINE=InnoDB 
DEFAULT CHARSET =utf8; 

`

## Front 실행

npm install

npm run start

