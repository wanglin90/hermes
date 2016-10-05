BEGIN;

DROP TABLE IF EXISTS config_demo;
CREATE TABLE config_demo (
    id int(11) not null auto_increment,
    create_time int(11) not null comment '创建时间',
    update_time int(11) not null comment '更新时间',
    name varchar(256) not null comment '姓名',
    age int(11) not null comment '年龄',
    PRIMARY KEY (id),
    KEY name_idx_0 (name)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

COMMIT;