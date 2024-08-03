drop table if exists NOTES;

create table NOTES
(
    N_ID        BIGINT auto_increment
        primary key,
    N_TITLE     CHARACTER(255) not null,
    N_CONTENT   CHARACTER VARYING,
    AUTHOR_ID   INTEGER,
    EDITOR_ID   INTEGER,
    CREATE_TIME DATE,
    MODIFY_TIME INTEGER
);

comment on column NOTES.N_ID is 'note id';

comment on column NOTES.N_TITLE is 'note title';

comment on column NOTES.N_CONTENT is 'note content';

comment on column NOTES.AUTHOR_ID is 'author_id';

comment on column NOTES.EDITOR_ID is 'editor id';

comment on column NOTES.CREATE_TIME is 'create time';

comment on column NOTES.MODIFY_TIME is 'modify time';

drop table if exists NOTES_INFO;

create table NOTES_INFO
(
    ID        BIGINT auto_increment
        primary key,
    N_ID      BIGINT ,
    PARENT_NOTE_ID BIGINT,
    S_ID      INTEGER
);


drop table if exists NOTES_SPACE;

create table NOTES_SPACE
(
    ID     INTEGER auto_increment
        primary key,
    S_ID   INTEGER,
    S_NAME CHARACTER(128)
);