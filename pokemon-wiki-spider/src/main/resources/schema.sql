CREATE TABLE IF NOT EXISTS pw_pokemon (
  id          INTEGER PRIMARY KEY,
  idx         INT         NOT NULL DEFAULT 0,
  name_zh     VARCHAR(32) NOT NULL DEFAULT '',
  name_ja     VARCHAR(32) NOT NULL DEFAULT '',
  name_en     VARCHAR(32) NOT NULL DEFAULT '',
  type1       VARCHAR(16) NOT NULL DEFAULT '',
  type2       VARCHAR(16) NOT NULL DEFAULT '',
  form        VARCHAR(32) NOT NULL DEFAULT '',
  generation  INT         NOT NULL DEFAULT 0,
  create_time DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pw_pokemon_ability (
  id           INTEGER PRIMARY KEY,
  idx          INT         NOT NULL DEFAULT 0,
  name_zh      VARCHAR(32) NOT NULL DEFAULT '',
  type1        VARCHAR(16) NOT NULL DEFAULT '',
  type2        VARCHAR(16) NOT NULL DEFAULT '',
  ability1     VARCHAR(16) NOT NULL DEFAULT '',
  ability2     VARCHAR(16) NOT NULL DEFAULT '',
  ability_hide VARCHAR(16) NOT NULL DEFAULT '',
  form         VARCHAR(32) NOT NULL DEFAULT '',
  generation   INT         NOT NULL DEFAULT 0,
  create_time  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pw_ability (
  id          INTEGER PRIMARY KEY,
  name_zh     VARCHAR(32) NOT NULL DEFAULT '',
  name_ja     VARCHAR(32) NOT NULL DEFAULT '',
  name_en     VARCHAR(32) NOT NULL DEFAULT '',
  effect      VARCHAR(64) NOT NULL DEFAULT '',
  generation  INT         NOT NULL DEFAULT 0,
  create_time DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pw_nature (
  id              INTEGER PRIMARY KEY,
  name_zh         VARCHAR(32) NOT NULL DEFAULT '',
  name_ja         VARCHAR(32) NOT NULL DEFAULT '',
  name_en         VARCHAR(32) NOT NULL DEFAULT '',
  increased_stat  VARCHAR(32) NOT NULL DEFAULT '',
  decreased_stat  VARCHAR(32) NOT NULL DEFAULT '',
  favorite_flavor VARCHAR(32) NOT NULL DEFAULT '',
  disliked_flavor VARCHAR(32) NOT NULL DEFAULT '',
  create_time     DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time     DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pw_move (
  id          INTEGER PRIMARY KEY,
  name_zh     VARCHAR(32) NOT NULL DEFAULT '',
  name_ja     VARCHAR(32) NOT NULL DEFAULT '',
  name_en     VARCHAR(32) NOT NULL DEFAULT '',
  type        VARCHAR(32) NOT NULL DEFAULT '',
  category    VARCHAR(32) NOT NULL DEFAULT '',
  power       VARCHAR(32) NOT NULL DEFAULT '',
  accuracy    VARCHAR(32) NOT NULL DEFAULT '',
  pp          VARCHAR(32) NOT NULL DEFAULT '',
  generation  INT         NOT NULL DEFAULT 0,
  create_time DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pw_item (
  id          INTEGER PRIMARY KEY,
  type        VARCHAR(32)  NOT NULL DEFAULT '',
  img_url     VARCHAR(128) NOT NULL DEFAULT '',
  name_zh     VARCHAR(32)  NOT NULL DEFAULT '',
  name_ja     VARCHAR(32)  NOT NULL DEFAULT '',
  name_en     VARCHAR(32)  NOT NULL DEFAULT '',
  desc        VARCHAR(256) NOT NULL DEFAULT '',
  generation  INT          NOT NULL DEFAULT 0,
  create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pw_pokemon_detail (
  id           INTEGER PRIMARY KEY,
  idx          INT          NOT NULL DEFAULT 0,
  img_url      VARCHAR(128) NOT NULL DEFAULT '',
  type         VARCHAR(16)  NOT NULL DEFAULT '',
  category     VARCHAR(16)  NOT NULL DEFAULT '',
  ability      VARCHAR(32)  NOT NULL DEFAULT '',
  height       VARCHAR(8)   NOT NULL DEFAULT '',
  weight       VARCHAR(8)   NOT NULL DEFAULT '',
  body_style   VARCHAR(128) NOT NULL DEFAULT '',
  catch_rate   VARCHAR(8)   NOT NULL DEFAULT '',
  gender_ratio VARCHAR(32)  NOT NULL DEFAULT '',
  egg_group1   VARCHAR(16)  NOT NULL DEFAULT '',
  egg_group2   VARCHAR(16)  NOT NULL DEFAULT '',
  hatch_time   VARCHAR(8)   NOT NULL DEFAULT '',
  effort_value VARCHAR(32)  NOT NULL DEFAULT '',
  create_time  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pw_pokemon_detail_base_stat (
  id          INTEGER PRIMARY KEY,
  idx         INT      NOT NULL DEFAULT 0,
  hp          INT      NOT NULL DEFAULT 0,
  attack      INT      NOT NULL DEFAULT 0,
  defense     INT      NOT NULL DEFAULT 0,
  sp_attack   INT      NOT NULL DEFAULT 0,
  sp_defense  INT      NOT NULL DEFAULT 0,
  speed       INT      NOT NULL DEFAULT 0,
  total       SMALLINT NOT NULL DEFAULT 0,
  average     FLOAT    NOT NULL DEFAULT 0,
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pw_pokemon_detail_learn_set_by_leveling_up (
  id          INTEGER PRIMARY KEY,
  idx         INT         NOT NULL DEFAULT 0,
  level1      CHAR(4)     NOT NULL DEFAULT '',
  level2      CHAR(4)     NOT NULL DEFAULT '',
  move        VARCHAR(32) NOT NULL DEFAULT '',
  type        VARCHAR(32) NOT NULL DEFAULT '',
  category    VARCHAR(32) NOT NULL DEFAULT '',
  power       VARCHAR(32) NOT NULL DEFAULT '',
  accuracy    VARCHAR(32) NOT NULL DEFAULT '',
  pp          VARCHAR(32) NOT NULL DEFAULT '',
  create_time DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pw_pokemon_detail_learn_set_by_technical_machine (
  id                INTEGER PRIMARY KEY,
  idx               INT          NOT NULL DEFAULT 0,
  img_url           VARCHAR(128) NOT NULL DEFAULT '',
  technical_machine VARCHAR(16)  NOT NULL DEFAULT '',
  move              VARCHAR(32)  NOT NULL DEFAULT '',
  type              VARCHAR(32)  NOT NULL DEFAULT '',
  category          VARCHAR(32)  NOT NULL DEFAULT '',
  power             VARCHAR(32)  NOT NULL DEFAULT '',
  accuracy          VARCHAR(32)  NOT NULL DEFAULT '',
  pp                VARCHAR(32)  NOT NULL DEFAULT '',
  create_time       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pw_pokemon_detail_learn_set_by_breeding (
  id          INTEGER PRIMARY KEY,
  idx         INT           NOT NULL DEFAULT 0,
  parent      VARCHAR(2048) NOT NULL DEFAULT '',
  move        VARCHAR(32)   NOT NULL DEFAULT '',
  type        VARCHAR(32)   NOT NULL DEFAULT '',
  category    VARCHAR(32)   NOT NULL DEFAULT '',
  power       VARCHAR(32)   NOT NULL DEFAULT '',
  accuracy    VARCHAR(32)   NOT NULL DEFAULT '',
  pp          VARCHAR(32)   NOT NULL DEFAULT '',
  create_time DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pw_ability_detail (
  id          INTEGER PRIMARY KEY,
  name_zh     VARCHAR(32)  NOT NULL DEFAULT '',
  desc        VARCHAR(256) NOT NULL DEFAULT '',
  effect      VARCHAR(512) NOT NULL DEFAULT '',
  pokemons    VARCHAR(256) NOT NULL DEFAULT '',
  create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pw_move_detail (
  id          INTEGER PRIMARY KEY,
  name_zh     VARCHAR(32)   NOT NULL DEFAULT '',
  desc        VARCHAR(256)  NOT NULL DEFAULT '',
  img_url     VARCHAR(256)  NOT NULL DEFAULT '',
  notes       VARCHAR(256)  NOT NULL DEFAULT '',
  scope       VARCHAR(256)  NOT NULL DEFAULT '',
  effect      VARCHAR(1024) NOT NULL DEFAULT '',
  create_time DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP
);
