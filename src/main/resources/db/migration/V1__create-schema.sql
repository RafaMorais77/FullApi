CREATE TABLE IF NOT EXISTS usuarios
(
    id integer NOT NULL,
    nome character varying(255) COLLATE pg_catalog."default",
    login character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    senha character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT usuarios_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS log
(
    id integer NOT NULL,
    "timestamp" timestamp without time zone NOT NULL,
    operation character varying(255) COLLATE pg_catalog."default" NOT NULL,
    details text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT log_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS eventos
(
    id integer NOT NULL,
    titulo character varying(100) COLLATE pg_catalog."default" NOT NULL,
    organizadora character varying(100) COLLATE pg_catalog."default" NOT NULL,
    data date,
    descricao text COLLATE pg_catalog."default",
    CONSTRAINT eventos_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS flyway_schema_history
(
    installed_rank integer NOT NULL,
    version character varying(50) COLLATE pg_catalog."default",
    description character varying(200) COLLATE pg_catalog."default" NOT NULL,
    type character varying(20) COLLATE pg_catalog."default" NOT NULL,
    script character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    checksum integer,
    installed_by character varying(100) COLLATE pg_catalog."default" NOT NULL,
    installed_on timestamp without time zone NOT NULL DEFAULT now(),
    execution_time integer NOT NULL,
    success boolean NOT NULL,
    CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank)
);

CREATE TABLE IF NOT EXISTS inscricao
(
    id integer NOT NULL,
    id_evento integer,
    id_usuario integer,
    CONSTRAINT inscricao_pkey PRIMARY KEY (id),
    CONSTRAINT inscricao_id_evento_fkey FOREIGN KEY (id_evento)
        REFERENCES eventos (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT inscricao_id_usuario_fkey FOREIGN KEY (id_usuario)
        REFERENCES usuarios (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS checkin
(
    id integer NOT NULL,
    id_evento integer,
    id_usuario integer,
    checkin timestamp without time zone,
    CONSTRAINT checkin_pkey PRIMARY KEY (id),
    CONSTRAINT checkin_id_evento_fkey FOREIGN KEY (id_evento)
        REFERENCES eventos (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT checkin_id_usuario_fkey FOREIGN KEY (id_usuario)
        REFERENCES usuarios (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);