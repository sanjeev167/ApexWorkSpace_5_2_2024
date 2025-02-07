---------------------------------------------------------------------
-------------- Drop following tables if they exists -----------------
---------------------------------------------------------------------
 DROP TABLE IF EXISTS access_rights_rbac;

 DROP TABLE IF EXISTS apex_module_mstr;
 DROP TABLE IF EXISTS client_account_mstr;
 DROP TABLE IF EXISTS file_type;
 DROP TABLE IF EXISTS operation_mstr;
 DROP TABLE IF EXISTS page_mstr;
 DROP TABLE IF EXISTS user_type;
 DROP TABLE IF EXISTS project_code_mstr;
 DROP TABLE IF EXISTS project_type_mstr;
 DROP TABLE IF EXISTS project_vertical_mstr;
 DROP TABLE IF EXISTS tree_menu_type_mstr;        

---------------------------------------------------------------------
---- [1] ----------- Create access_rights_rbac table ----------------
---------------------------------------------------------------------

CREATE TABLE public.access_rights_rbac
(
    id serial,
    page_id integer,
    role_id integer,
    created_by integer,
    created_on timestamp with time zone,
    modified_by integer,
    modified_on timestamp with time zone,
    active_c "char" NOT NULL DEFAULT 'Y'::"char",
    CONSTRAINT access_rights_rbac_pkey PRIMARY KEY (id),
    CONSTRAINT access_rights_rbac_created_by_fkey FOREIGN KEY (created_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT access_rights_rbac_modified_by_fkey FOREIGN KEY (modified_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT access_rights_rbac_page_id_fkey FOREIGN KEY (page_id)
        REFERENCES public.page_mstr (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT access_rights_rbac_role_id_fkey FOREIGN KEY (role_id)
        REFERENCES public.apex_role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.access_rights_rbac
    OWNER to postgres;

---------------------------------------------------------------------
---- [2] ----------- Create apex_module_mstr  table ----------------
---------------------------------------------------------------------

CREATE TABLE public.apex_module_mstr
(
    id serial,
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    created_by integer,
    created_on timestamp with time zone,
    modified_by integer,
    modified_on timestamp with time zone,
    active_c "char" NOT NULL DEFAULT 'Y'::"char",
    CONSTRAINT apex_module_pkey PRIMARY KEY (id),
    CONSTRAINT apex_module_mstr_created_by_fkey FOREIGN KEY (created_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT apex_module_mstr_modified_by_fkey FOREIGN KEY (modified_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.apex_module_mstr
    OWNER to postgres;

---------------------------------------------------------------------
---- [3] ----------- Create client_account_mstr table ----------------
---------------------------------------------------------------------

CREATE TABLE public.client_account_mstr
(
    id integer NOT NULL DEFAULT nextval('client_account_mstr_id_seq'::regclass),
    client_account character varying(50) COLLATE pg_catalog."default" NOT NULL,
    created_by integer,
    created_on timestamp with time zone,
    modified_by integer,
    modified_on timestamp with time zone,
    active_c "char" NOT NULL DEFAULT 'Y'::"char",
    CONSTRAINT client_account_mstr_pkey PRIMARY KEY (id),
    CONSTRAINT client_account UNIQUE (client_account),
    CONSTRAINT created_by FOREIGN KEY (created_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT modified_by FOREIGN KEY (modified_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.client_account_mstr
    OWNER to postgres;

COMMENT ON CONSTRAINT client_account ON public.client_account_mstr
    IS 'This project account name will be unique each client';

---------------------------------------------------------------------
---- [4] ----------- Create file_type  table ----------------
---------------------------------------------------------------------

CREATE TABLE public.file_type
(
    id serial,
    file_type character varying(100) COLLATE pg_catalog."default",
    created_by integer,
    created_on timestamp with time zone,
    modified_by integer,
    modified_on timestamp with time zone,
    active_c "char" NOT NULL,
    CONSTRAINT file_type_pkey PRIMARY KEY (id),
    CONSTRAINT file_type_created_by_fkey FOREIGN KEY (created_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT file_type_modified_by_fkey FOREIGN KEY (modified_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.file_type
    OWNER to postgres;

---------------------------------------------------------------------
---- [5] ----------- Create operation_mstr table ----------------
---------------------------------------------------------------------

CREATE TABLE public.operation_mstr
(
    id serial,
    name character varying(50) COLLATE pg_catalog."default",
    page_id integer,
    opurl character varying(50) COLLATE pg_catalog."default",
    created_by integer,
    created_on timestamp with time zone,
    modified_by integer,
    modified_on timestamp with time zone,
    active_c "char" NOT NULL DEFAULT 'Y'::"char",
    CONSTRAINT operation_mstr_pkey PRIMARY KEY (id),
    CONSTRAINT operation_mstr_created_by_fkey FOREIGN KEY (created_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT operation_mstr_modified_by_fkey FOREIGN KEY (modified_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT operation_mstr_page_id_fkey FOREIGN KEY (page_id)
        REFERENCES public.page_mstr (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.operation_mstr
    OWNER to postgres;

---------------------------------------------------------------------
---- [6] ----------- Create page_mstr table ----------------
---------------------------------------------------------------------

CREATE TABLE public.page_mstr
(
    id serial,
    name character varying(50) COLLATE pg_catalog."default",
    module_id integer,
    baseurl character varying(100) COLLATE pg_catalog."default",
    created_by integer,
    created_on timestamp with time zone,
    modified_by integer,
    modified_on timestamp with time zone,
    active_c "char" NOT NULL DEFAULT 'Y'::"char",
    CONSTRAINT page_mstr_pkey PRIMARY KEY (id),
    CONSTRAINT page_mstr_created_by_fkey FOREIGN KEY (created_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT page_mstr_modified_by_fkey FOREIGN KEY (modified_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT page_mstr_module_id_fkey FOREIGN KEY (module_id)
        REFERENCES public.apex_module_mstr (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.page_mstr
    OWNER to postgres;


---------------------------------------------------------------------
---- [7] ----------- Create project_code_mstr table ----------------
---------------------------------------------------------------------

CREATE TABLE public.project_code_mstr
(
    id serial,
    project_code character varying(50) COLLATE pg_catalog."default" NOT NULL,
    project_name character varying(100) COLLATE pg_catalog."default",
    client_account_id integer,
    created_by integer,
    created_on timestamp with time zone,
    modified_by integer,
    modified_on timestamp with time zone,
    active_c "char" NOT NULL DEFAULT 'Y'::"char",
    CONSTRAINT project_code_mstr_pkey PRIMARY KEY (id),
    CONSTRAINT project_code UNIQUE (project_code),
    CONSTRAINT created_by FOREIGN KEY (created_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT modified_by FOREIGN KEY (modified_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.project_code_mstr
    OWNER to postgres;

COMMENT ON CONSTRAINT project_code ON public.project_code_mstr
    IS 'This will be a project code for different project and it will be unique in nature.';

---------------------------------------------------------------------
---- [8] ----------- Create project_type_mstr table ----------------
---------------------------------------------------------------------

CREATE TABLE public.project_type_mstr
(
    id serial,
    project_type character varying(100) COLLATE pg_catalog."default" NOT NULL,
    created_by integer,
    created_on timestamp with time zone,
    modified_by integer,
    modified_on timestamp with time zone,
    active_c "char" NOT NULL DEFAULT 'Y'::"char",
    CONSTRAINT project_type_mstr_pkey PRIMARY KEY (id),
    CONSTRAINT project_type UNIQUE (project_type),
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

ALTER TABLE public.project_type_mstr
    OWNER to postgres;

---------------------------------------------------------------------
---- [9] ----------- Create project_vertical_mstr table -------------
---------------------------------------------------------------------

CREATE TABLE public.project_vertical_mstr
(
    id serial,
    vertical_name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    created_by integer,
    created_on timestamp with time zone,
    modified_by integer,
    modified_on timestamp with time zone,
    active_c "char" NOT NULL DEFAULT 'Y'::"char",
    CONSTRAINT project_vertical_mstr_pkey PRIMARY KEY (id),
    CONSTRAINT vertical_name UNIQUE (vertical_name),
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

ALTER TABLE public.project_vertical_mstr
    OWNER to postgres;

---------------------------------------------------------------------
---- [10] ----------- Create tree_menu_type_mstr table ------------
---------------------------------------------------------------------

CREATE TABLE public.tree_menu_type_mstr
(
    id integer NOT NULL DEFAULT nextval('tree_menu_type_mstr_id_seq'::regclass),
    name character varying(50) COLLATE pg_catalog."default",
    created_by integer,
    created_on timestamp with time zone,
    updated_by integer,
    updated_on timestamp with time zone,
    active_c character(1) COLLATE pg_catalog."default" NOT NULL DEFAULT 'Y'::"char",
    CONSTRAINT tree_menu_type_mstr_pkey PRIMARY KEY (id),
    CONSTRAINT tree_menu_type_mstr_created_by_fkey FOREIGN KEY (created_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT tree_menu_type_mstr_updated_by_fkey FOREIGN KEY (updated_by)
        REFERENCES public.apex_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.tree_menu_type_mstr
    OWNER to postgres;


