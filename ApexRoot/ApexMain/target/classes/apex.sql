---------------------------------------------------------------------
-------------- Drop following tables if they exists -----------------
---------------------------------------------------------------------
 DROP TABLE IF EXISTS apex_refresh_token;
 DROP TABLE IF EXISTS apex_group_role;
 DROP TABLE IF EXISTS apex_role;
 DROP TABLE IF EXISTS apex_user_group;
 DROP TABLE IF EXISTS apex_group;
 DROP TABLE IF EXISTS apex_user;
 DROP TABLE IF EXISTS user_type;

---------------------------------------------------------------------
---- [1] ----------- Create user_type table -------------------------
---------------------------------------------------------------------

CREATE TABLE public.user_type
(
    id serial,
    user_type character varying(100) COLLATE pg_catalog."default" NOT NULL,
    created_by integer,
    created_on timestamp with time zone,
    modified_by integer,
    modified_on timestamp with time zone,
    active_c "char" NOT NULL DEFAULT 'Y'::"char",
    CONSTRAINT user_type_pkey PRIMARY KEY (id)   
)

TABLESPACE pg_default;

ALTER TABLE public.user_type
    OWNER to postgres;


---------------------------------------------------------------------
---- [2] ----------- Create apex_user table --------------------------
---------------------------------------------------------------------

CREATE TABLE public.apex_user
(
    id serial,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    email character varying(100) COLLATE pg_catalog."default" NOT NULL,
    pwd character varying(100) COLLATE pg_catalog."default" NOT NULL,
    user_context character varying(100) COLLATE pg_catalog."default",
    created_on timestamp with time zone,
    created_by integer,
    modified_on timestamp with time zone,
    modified_by integer,
    active_c character(1) COLLATE pg_catalog."default" NOT NULL DEFAULT 'Y'::"char",
    user_type_id integer,
    CONSTRAINT apex_user_pkey PRIMARY KEY (id),
    CONSTRAINT email UNIQUE (email),
    CONSTRAINT created_by FOREIGN KEY (created_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT modified_by FOREIGN KEY (modified_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT user_type_id FOREIGN KEY (user_type_id)
        REFERENCES public.user_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.apex_user
    OWNER to postgres;


---------------------------------------------------------------------
---- [3] -------- Create apex_group table ---------------------------
---------------------------------------------------------------------

CREATE TABLE public.apex_group
(
    id serial,
    group_name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    created_on timestamp with time zone,
    created_by integer,
    modified_on timestamp with time zone,
    modified_by integer,
    active_c character(1) COLLATE pg_catalog."default" NOT NULL DEFAULT 'Y'::"char",
    CONSTRAINT apex_group_pkey PRIMARY KEY (id),
    CONSTRAINT group_name UNIQUE (group_name),
    CONSTRAINT created_by FOREIGN KEY (created_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT modified_by FOREIGN KEY (modified_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.apex_group
    OWNER to postgres;

---------------------------------------------------------------------
---- [4] ------------ Create apex_user_group table ------------------
---------------------------------------------------------------------

CREATE TABLE public.apex_user_group
(
    id serial,
    apex_user_id integer NOT NULL,
    apex_group_id integer NOT NULL,
    created_on date NOT NULL,
    created_by integer,
    modified_on date,
    modified_by integer,
    active_c character(1) COLLATE pg_catalog."default" NOT NULL DEFAULT 'Y'::"char",
    CONSTRAINT apex_user_group_pkey PRIMARY KEY (id),
    CONSTRAINT apex_user_group_apex_group_id_fkey FOREIGN KEY (apex_group_id)
        REFERENCES public.apex_group (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT apex_user_group_apex_user_id_fkey FOREIGN KEY (apex_user_id)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT created_by FOREIGN KEY (created_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT modified_by FOREIGN KEY (modified_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.apex_user_group
    OWNER to postgres;

---------------------------------------------------------------------
---- [5] ----------- Create apex_role table --------------------------
---------------------------------------------------------------------

CREATE TABLE public.apex_role
(
    id serial,
    role_name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    created_on timestamp with time zone DEFAULT CURRENT_DATE,
    created_by integer,
    modified_on timestamp with time zone,
    modified_by integer,
    active_c character(1) COLLATE pg_catalog."default" NOT NULL DEFAULT 'Y'::"char",
    CONSTRAINT apex_role_pkey PRIMARY KEY (id),
    CONSTRAINT role_name UNIQUE (role_name),
    CONSTRAINT created_by FOREIGN KEY (created_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT modified_by FOREIGN KEY (modified_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.apex_role
OWNER to postgres;

---------------------------------------------------------------------
---- [6] ----------- Create apex_group_role table -------------------
---------------------------------------------------------------------

CREATE TABLE public.apex_group_role
(
    id serial,
    apex_group_id integer NOT NULL,
    apex_role_id integer NOT NULL,
    created_by integer,
    created_on timestamp with time zone,
    modified_by integer,
    modified_on timestamp with time zone,    
    active_c character(1) COLLATE pg_catalog."default" NOT NULL DEFAULT 'Y'::"char",
    CONSTRAINT apex_role_group_pkey PRIMARY KEY (id),
    CONSTRAINT apex_group_role_apex_group_id_fkey FOREIGN KEY (apex_group_id)
        REFERENCES public.apex_group (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT apex_role_group_apex_role_id_fkey FOREIGN KEY (apex_role_id)
        REFERENCES public.apex_role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT created_by FOREIGN KEY (created_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT modified_by FOREIGN KEY (modified_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.apex_group_role
    OWNER to postgres;
---------------------------------------------------------------------
---- [7] --------- Create apex_refresh_token table ------------------
---------------------------------------------------------------------

CREATE TABLE public.apex_refresh_token
(
    id serial,
    refresh_token character varying(200) COLLATE pg_catalog."default" NOT NULL,
    expiry_date timestamp with time zone NOT NULL,
    created_on timestamp without time zone,
    user_id integer,
    CONSTRAINT apex_refresh_token_pkey PRIMARY KEY (id),
    CONSTRAINT apex_refresh_token_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.apex_refresh_token
    OWNER to postgres;

COMMENT ON TABLE public.apex_refresh_token
    IS 'This table will be used for keeping refresh token details of a user.';


---------------------------------------------------------------------
---------- [1] Insert-Script for creating super and admin user -----------
---------------------------------------------------------------------
INSERT INTO user_type (user_type,created_by,created_on,modified_by,modified_on,active_c) 
                VALUES('SUPER_USER_TYPE',NULL,current_date,NULL,NULL,'Y');

INSERT INTO apex_user (name,email,pwd,user_type_id,created_by,created_on,modified_by, modified_on,active_c,user_context) 
                VALUES('Super','Super@nus.com','$2a$10$o1.miiRWYmf0QjpAhDoUVOeFE5NJIPNgG3xEM9dxU41FLpnpmK6NC',
                        (select id from user_type where user_type = 'SUPER_USER_TYPE' ),
                        NULL,current_date,NULL,NULL,'Y','ADMIN_CTXT');

INSERT INTO user_type (user_type,created_by,created_on,modified_by,modified_on,active_c) 
                VALUES('ADMIN_USER_TYPE',(select id from apex_user where email = 'Super@nus.com' ),current_date,NULL,NULL,'Y');

INSERT INTO apex_user (name,email,pwd,user_type_id,created_by,created_on,modified_by,modified_on,active_c,user_context) 
                VALUES('Admin','Admin@nus.com','$2a$10$3rpjpOeuwvQittj7qaOkyutghwc48Z.VvOkCo3Dn8xAkXj01ZvG9m',
                        (select id from user_type where user_type = 'ADMIN_USER_TYPE' ),
                        (select id from apex_user where email = 'Super@nus.com'), current_date,NULL,NULL,'Y','ADMIN_CTXT');

---------------------------------------------------------------------
-------------[2] Insert-Script for apex-grouop ----------------------
---------------------------------------------------------------------

INSERT INTO apex_group (group_name,created_by,created_on,modified_by,modified_on,active_c) 
VALUES('GROUP_SUPER',(select id from apex_user where email = 'Super@nus.com'),current_date,NULL,NULL,'Y');

INSERT INTO apex_group (group_name,created_by,created_on,modified_by,modified_on,active_c) 
VALUES('GROUP_ADMIN',(select id from apex_user where email = 'Super@nus.com'),current_date,NULL,NULL,'Y');
  
---------------------------------------------------------------------
---------------[3] Insert-Script for apex-role ----------------------
---------------------------------------------------------------------

INSERT INTO apex_role (role_name,created_by,created_on,modified_by,modified_on,active_c) 
                VALUES('ROLE_SUPER',(select id from apex_user where email = 'Super@nus.com'),current_date,NULL,NULL,'Y');

INSERT INTO apex_role (role_name,created_by,created_on,modified_by,modified_on,active_c) 
                VALUES('ROLE_ADMIN',(select id from apex_user where email = 'Super@nus.com'),current_date,NULL,NULL,'Y');

---------------------------------------------------------------------
------- [4] Associate different Groups with a different roles -------
---------------------------------------------------------------------

INSERT INTO apex_group_role (apex_group_id,apex_role_id,created_by,created_on,modified_by,modified_on,active_c) 
                     VALUES((select id from apex_group  where group_name='GROUP_SUPER'),
                            (select id from apex_role  where role_name='ROLE_SUPER'),
                            (select id from apex_user where email = 'Super@nus.com'),current_date,NULL,NULL,'Y');

INSERT INTO apex_group_role (apex_group_id,apex_role_id,created_by,created_on,modified_by,modified_on,active_c) 
                     VALUES((select id from apex_group  where group_name='GROUP_ADMIN'),
                            (select id from apex_role  where role_name='ROLE_ADMIN'),
                            (select id from apex_user where email = 'Super@nus.com'),current_date,NULL,NULL,'Y');

----------------------------------------------------------------------
-------[5] Associate Admin, Super users with a group by super user ------
----------------------------------------------------------------------

INSERT INTO apex_user_group (apex_user_id,apex_group_id,created_by,created_on,modified_by,modified_on,active_c) 
                     VALUES((select id from apex_user where name='Super'),
                            (select id from apex_group where group_name='GROUP_SUPER'),
                            (select id from apex_user where email = 'Super@nus.com'),   
                            current_date,NULL,NULL,'Y');

INSERT INTO apex_user_group (apex_user_id,apex_group_id,created_by,created_on,modified_by,modified_on,active_c) 
                     VALUES((select id from apex_user where name='Admin'),
                            (select id from apex_group where group_name='GROUP_ADMIN'),
                            (select id from apex_user where email = 'Super@nus.com'),   
                            current_date,NULL,NULL,'Y');
