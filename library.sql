--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: author; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE author (
    id integer NOT NULL,
    author character varying
);


ALTER TABLE author OWNER TO "Guest";

--
-- Name: author_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE author_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE author_id_seq OWNER TO "Guest";

--
-- Name: author_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE author_id_seq OWNED BY author.id;


--
-- Name: catalog; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE catalog (
    id integer NOT NULL,
    copies integer,
    id_title_author integer
);


ALTER TABLE catalog OWNER TO "Guest";

--
-- Name: catalog_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE catalog_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE catalog_id_seq OWNER TO "Guest";

--
-- Name: catalog_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE catalog_id_seq OWNED BY catalog.id;


--
-- Name: copies; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE copies (
    id integer NOT NULL,
    copies integer,
    due_date character varying,
    title_id integer
);


ALTER TABLE copies OWNER TO "Guest";

--
-- Name: copies_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE copies_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE copies_id_seq OWNER TO "Guest";

--
-- Name: copies_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE copies_id_seq OWNED BY copies.id;


--
-- Name: patron; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE patron (
    id integer NOT NULL,
    patron_name character varying
);


ALTER TABLE patron OWNER TO "Guest";

--
-- Name: patron_copy; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE patron_copy (
    id integer NOT NULL,
    id_patron integer,
    id_copy integer
);


ALTER TABLE patron_copy OWNER TO "Guest";

--
-- Name: patron_copy_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE patron_copy_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE patron_copy_id_seq OWNER TO "Guest";

--
-- Name: patron_copy_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE patron_copy_id_seq OWNED BY patron_copy.id;


--
-- Name: patron_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE patron_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE patron_id_seq OWNER TO "Guest";

--
-- Name: patron_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE patron_id_seq OWNED BY patron.id;


--
-- Name: title; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE title (
    id integer NOT NULL,
    title character varying
);


ALTER TABLE title OWNER TO "Guest";

--
-- Name: title_author; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE title_author (
    id integer NOT NULL,
    id_title integer,
    id_author integer
);


ALTER TABLE title_author OWNER TO "Guest";

--
-- Name: title_author_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE title_author_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE title_author_id_seq OWNER TO "Guest";

--
-- Name: title_author_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE title_author_id_seq OWNED BY title_author.id;


--
-- Name: title_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE title_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE title_id_seq OWNER TO "Guest";

--
-- Name: title_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE title_id_seq OWNED BY title.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY author ALTER COLUMN id SET DEFAULT nextval('author_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY catalog ALTER COLUMN id SET DEFAULT nextval('catalog_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY copies ALTER COLUMN id SET DEFAULT nextval('copies_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY patron ALTER COLUMN id SET DEFAULT nextval('patron_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY patron_copy ALTER COLUMN id SET DEFAULT nextval('patron_copy_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY title ALTER COLUMN id SET DEFAULT nextval('title_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY title_author ALTER COLUMN id SET DEFAULT nextval('title_author_id_seq'::regclass);


--
-- Data for Name: author; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY author (id, author) FROM stdin;
4	Michael Crichton
5	Dr. Suess
6	Carl Macek
7	Stephen King
\.


--
-- Name: author_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('author_id_seq', 7, true);


--
-- Data for Name: catalog; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY catalog (id, copies, id_title_author) FROM stdin;
\.


--
-- Name: catalog_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('catalog_id_seq', 1, false);


--
-- Data for Name: copies; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY copies (id, copies, due_date, title_id) FROM stdin;
1	1	01/19/2018	2
\.


--
-- Name: copies_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('copies_id_seq', 1, true);


--
-- Data for Name: patron; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY patron (id, patron_name) FROM stdin;
1	Mattison, Kevin
2	Mattison, Kevin
3	Smith, Bob
\.


--
-- Data for Name: patron_copy; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY patron_copy (id, id_patron, id_copy) FROM stdin;
\.


--
-- Name: patron_copy_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('patron_copy_id_seq', 1, false);


--
-- Name: patron_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('patron_id_seq', 3, true);


--
-- Data for Name: title; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY title (id, title) FROM stdin;
16	Jurassic Park
17	Congo
18	Air Frame
19	Robotech
20	The Sentinels
21	Hop on Pop
22	One FIsh, Two Fish
23	Green Eggs and Ham
25	It
26	It
\.


--
-- Data for Name: title_author; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY title_author (id, id_title, id_author) FROM stdin;
1	1	1
2	13	3
3	14	3
4	15	3
5	19	6
6	20	6
7	18	4
8	17	4
9	16	4
10	23	5
11	21	5
12	22	5
13	26	7
14	25	7
15	18	6
\.


--
-- Name: title_author_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('title_author_id_seq', 15, true);


--
-- Name: title_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('title_id_seq', 26, true);


--
-- Name: author_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY author
    ADD CONSTRAINT author_pkey PRIMARY KEY (id);


--
-- Name: catalog_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY catalog
    ADD CONSTRAINT catalog_pkey PRIMARY KEY (id);


--
-- Name: copies_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY copies
    ADD CONSTRAINT copies_pkey PRIMARY KEY (id);


--
-- Name: patron_copy_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY patron_copy
    ADD CONSTRAINT patron_copy_pkey PRIMARY KEY (id);


--
-- Name: patron_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY patron
    ADD CONSTRAINT patron_pkey PRIMARY KEY (id);


--
-- Name: title_author_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY title_author
    ADD CONSTRAINT title_author_pkey PRIMARY KEY (id);


--
-- Name: title_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY title
    ADD CONSTRAINT title_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

