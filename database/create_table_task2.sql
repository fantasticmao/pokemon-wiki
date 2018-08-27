DROP TABLE IF EXISTS pw_move_detail;

CREATE TABLE IF NOT EXISTS pw_move_detail (
  id         INT UNSIGNED  NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  nameZh     VARCHAR(32)   NOT NULL DEFAULT ''
  COMMENT '招式名称',
  `desc`     VARCHAR(256)  NOT NULL DEFAULT ''
  COMMENT '招式描述',
  imgUrl     VARCHAR(256)  NOT NULL DEFAULT ''
  COMMENT '图片链接',
  notes      VARCHAR(256)  NOT NULL DEFAULT ''
  COMMENT '注意事项',
  scope      VARCHAR(256)  NOT NULL DEFAULT ''
  COMMENT '作用范围',
  effect     VARCHAR(1024) NOT NULL DEFAULT ''
  COMMENT '附加效果',
  createTime DATETIME      NOT NULL DEFAULT current_timestamp
  COMMENT '创建时间',
  modifyTime DATETIME      NOT NULL DEFAULT current_timestamp
  ON UPDATE current_timestamp
  COMMENT '修改时间',
  PRIMARY KEY (id)
)
  ENGINE InnoDB
  DEFAULT CHARSET 'utf8mb4'
  COMMENT '招式详情';

## ----------------------------------------------------------

DROP TABLE IF EXISTS pw_ability_detail;

CREATE TABLE IF NOT EXISTS pw_ability_detail (
  id         INT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  nameZh     VARCHAR(32)  NOT NULL DEFAULT ''
  COMMENT '特性名称',
  `desc`     VARCHAR(256) NOT NULL DEFAULT ''
  COMMENT '特性描述',
  effect     VARCHAR(512) NOT NULL DEFAULT ''
  COMMENT '特性效果',
  pokemons   VARCHAR(256) NOT NULL DEFAULT ''
  COMMENT '拥有此特性的宝可梦',
  createTime DATETIME     NOT NULL DEFAULT current_timestamp
  COMMENT '创建时间',
  modifyTime DATETIME     NOT NULL DEFAULT current_timestamp
  ON UPDATE current_timestamp
  COMMENT '修改时间',
  PRIMARY KEY (id)
)
  ENGINE InnoDB
  DEFAULT CHARSET 'utf8mb4'
  COMMENT '特性详情';