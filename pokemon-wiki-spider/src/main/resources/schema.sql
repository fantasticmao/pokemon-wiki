CREATE TABLE IF NOT EXISTS t_pokemon (
  id          INTEGER PRIMARY KEY,
  idx         INTEGER NOT NULL DEFAULT 0,
  name_zh     TEXT    NOT NULL DEFAULT '',
  name_ja     TEXT    NOT NULL DEFAULT '',
  name_en     TEXT    NOT NULL DEFAULT '',
  type1       TEXT    NOT NULL DEFAULT '',
  type2       TEXT    NOT NULL DEFAULT '',
  form        TEXT    NOT NULL DEFAULT '',
  generation  INTEGER NOT NULL DEFAULT 0,
  create_time TEXT    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time TEXT    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS t_pokemon_ability (
  id           INTEGER PRIMARY KEY,
  idx          INTEGER NOT NULL DEFAULT 0,
  name_zh      TEXT    NOT NULL DEFAULT '',
  type1        TEXT    NOT NULL DEFAULT '',
  type2        TEXT    NOT NULL DEFAULT '',
  ability1     TEXT    NOT NULL DEFAULT '',
  ability2     TEXT    NOT NULL DEFAULT '',
  ability_hide TEXT    NOT NULL DEFAULT '',
  form         TEXT    NOT NULL DEFAULT '',
  generation   INTEGER NOT NULL DEFAULT 0,
  create_time  TEXT    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time  TEXT    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS t_ability (
  id          INTEGER PRIMARY KEY,
  name_zh     TEXT    NOT NULL DEFAULT '',
  name_ja     TEXT    NOT NULL DEFAULT '',
  name_en     TEXT    NOT NULL DEFAULT '',
  effect      TEXT    NOT NULL DEFAULT '',
  generation  INTEGER NOT NULL DEFAULT 0,
  create_time TEXT    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time TEXT    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS t_nature (
  id              INTEGER PRIMARY KEY,
  name_zh         TEXT NOT NULL DEFAULT '',
  name_ja         TEXT NOT NULL DEFAULT '',
  name_en         TEXT NOT NULL DEFAULT '',
  increased_stat  TEXT NOT NULL DEFAULT '',
  decreased_stat  TEXT NOT NULL DEFAULT '',
  favorite_flavor TEXT NOT NULL DEFAULT '',
  disliked_flavor TEXT NOT NULL DEFAULT '',
  create_time     TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time     TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS t_move (
  id          INTEGER PRIMARY KEY,
  name_zh     TEXT    NOT NULL DEFAULT '',
  name_ja     TEXT    NOT NULL DEFAULT '',
  name_en     TEXT    NOT NULL DEFAULT '',
  type        TEXT    NOT NULL DEFAULT '',
  category    TEXT    NOT NULL DEFAULT '',
  power       TEXT    NOT NULL DEFAULT '',
  accuracy    TEXT    NOT NULL DEFAULT '',
  pp          TEXT    NOT NULL DEFAULT '',
  generation  INTEGER NOT NULL DEFAULT 0,
  create_time TEXT    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time TEXT    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS t_item (
  id          INTEGER PRIMARY KEY,
  type        TEXT    NOT NULL DEFAULT '',
  img_url     TEXT    NOT NULL DEFAULT '',
  name_zh     TEXT    NOT NULL DEFAULT '',
  name_ja     TEXT    NOT NULL DEFAULT '',
  name_en     TEXT    NOT NULL DEFAULT '',
  desc        TEXT    NOT NULL DEFAULT '',
  generation  INTEGER NOT NULL DEFAULT 0,
  create_time TEXT    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time TEXT    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS t_pokemon_detail (
  id           INTEGER PRIMARY KEY,
  idx          INTEGER NOT NULL DEFAULT 0,
  img_url      TEXT    NOT NULL DEFAULT '',
  type         TEXT    NOT NULL DEFAULT '',
  category     TEXT    NOT NULL DEFAULT '',
  ability      TEXT    NOT NULL DEFAULT '',
  height       TEXT    NOT NULL DEFAULT '',
  weight       TEXT    NOT NULL DEFAULT '',
  body_style   TEXT    NOT NULL DEFAULT '',
  catch_rate   TEXT    NOT NULL DEFAULT '',
  gender_ratio TEXT    NOT NULL DEFAULT '',
  egg_group1   TEXT    NOT NULL DEFAULT '',
  egg_group2   TEXT    NOT NULL DEFAULT '',
  hatch_time   TEXT    NOT NULL DEFAULT '',
  effort_value TEXT    NOT NULL DEFAULT '',
  create_time  TEXT    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time  TEXT    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS t_pokemon_detail_base_stat (
  id          INTEGER PRIMARY KEY,
  idx         INTEGER NOT NULL DEFAULT 0,
  hp          INTEGER NOT NULL DEFAULT 0,
  attack      INTEGER NOT NULL DEFAULT 0,
  defense     INTEGER NOT NULL DEFAULT 0,
  sp_attack   INTEGER NOT NULL DEFAULT 0,
  sp_defense  INTEGER NOT NULL DEFAULT 0,
  speed       INTEGER NOT NULL DEFAULT 0,
  total       INTEGER NOT NULL DEFAULT 0,
  average     REAL    NOT NULL DEFAULT 0,
  create_time TEXT    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time TEXT    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS t_pokemon_detail_learn_set_by_leveling_up (
  id          INTEGER PRIMARY KEY,
  idx         INTEGER NOT NULL DEFAULT 0,
  level       TEXT    NOT NULL DEFAULT '',
  move        TEXT    NOT NULL DEFAULT '',
  type        TEXT    NOT NULL DEFAULT '',
  category    TEXT    NOT NULL DEFAULT '',
  power       TEXT    NOT NULL DEFAULT '',
  accuracy    TEXT    NOT NULL DEFAULT '',
  pp          TEXT    NOT NULL DEFAULT '',
  create_time TEXT    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time TEXT    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS t_pokemon_detail_learn_set_by_technical_machine (
  id                INTEGER PRIMARY KEY,
  idx               INTEGER NOT NULL DEFAULT 0,
  img_url           TEXT    NOT NULL DEFAULT '',
  technical_machine TEXT    NOT NULL DEFAULT '',
  move              TEXT    NOT NULL DEFAULT '',
  type              TEXT    NOT NULL DEFAULT '',
  category          TEXT    NOT NULL DEFAULT '',
  power             TEXT    NOT NULL DEFAULT '',
  accuracy          TEXT    NOT NULL DEFAULT '',
  pp                TEXT    NOT NULL DEFAULT '',
  create_time       TEXT    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time       TEXT    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS t_pokemon_detail_learn_set_by_breeding (
  id          INTEGER PRIMARY KEY,
  idx         INTEGER NOT NULL DEFAULT 0,
  parent      TEXT    NOT NULL DEFAULT '',
  move        TEXT    NOT NULL DEFAULT '',
  type        TEXT    NOT NULL DEFAULT '',
  category    TEXT    NOT NULL DEFAULT '',
  power       TEXT    NOT NULL DEFAULT '',
  accuracy    TEXT    NOT NULL DEFAULT '',
  pp          TEXT    NOT NULL DEFAULT '',
  create_time TEXT    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time TEXT    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS t_ability_detail (
  id          INTEGER PRIMARY KEY,
  name_zh     TEXT NOT NULL DEFAULT '',
  desc        TEXT NOT NULL DEFAULT '',
  effect      TEXT NOT NULL DEFAULT '',
  pokemons    TEXT NOT NULL DEFAULT '',
  create_time TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS t_move_detail (
  id          INTEGER PRIMARY KEY,
  name_zh     TEXT NOT NULL DEFAULT '',
  desc        TEXT NOT NULL DEFAULT '',
  img_url     TEXT NOT NULL DEFAULT '',
  notes       TEXT NOT NULL DEFAULT '',
  scope       TEXT NOT NULL DEFAULT '',
  effect      TEXT NOT NULL DEFAULT '',
  create_time TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
);
