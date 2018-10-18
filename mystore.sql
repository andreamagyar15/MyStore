--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.9
-- Dumped by pg_dump version 9.6.9

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: bags; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bags (
    id integer NOT NULL,
    title character varying(50),
    description character varying(255),
    price double precision,
    amount integer,
    image character varying,
    size integer,
    arrived date
);


ALTER TABLE public.bags OWNER TO postgres;

--
-- Name: bags_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bags_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bags_id_seq OWNER TO postgres;

--
-- Name: bags_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.bags_id_seq OWNED BY public.bags.id;


--
-- Name: hats; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.hats (
    id integer NOT NULL,
    title character varying(50),
    description character varying(255),
    size integer,
    price double precision,
    amount integer,
    image character varying,
    arrived date
);


ALTER TABLE public.hats OWNER TO postgres;

--
-- Name: hats_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hats_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hats_id_seq OWNER TO postgres;

--
-- Name: hats_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.hats_id_seq OWNED BY public.hats.id;


--
-- Name: messages; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.messages (
    id integer NOT NULL,
    name character varying(20),
    message character varying(500),
    email character varying(20),
    number character varying(20)
);


ALTER TABLE public.messages OWNER TO postgres;

--
-- Name: messages_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.messages_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.messages_id_seq OWNER TO postgres;

--
-- Name: messages_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.messages_id_seq OWNED BY public.messages.id;


--
-- Name: orderdetail; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orderdetail (
    id integer NOT NULL,
    orderid integer,
    productid integer,
    quantity integer,
    producttype text
);


ALTER TABLE public.orderdetail OWNER TO postgres;

--
-- Name: orderdetail_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orderdetail_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orderdetail_id_seq OWNER TO postgres;

--
-- Name: orderdetail_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orderdetail_id_seq OWNED BY public.orderdetail.id;


--
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    id integer NOT NULL,
    customerid character varying(50),
    total double precision DEFAULT 0,
    status boolean DEFAULT false
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_id_seq OWNER TO postgres;

--
-- Name: orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;


--
-- Name: shoes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.shoes (
    id integer NOT NULL,
    title character varying(50),
    description character varying(255),
    size integer,
    price double precision,
    amount integer,
    image character varying,
    arrived date
);


ALTER TABLE public.shoes OWNER TO postgres;

--
-- Name: shoes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.shoes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.shoes_id_seq OWNER TO postgres;

--
-- Name: shoes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.shoes_id_seq OWNED BY public.shoes.id;


--
-- Name: tshirts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tshirts (
    id integer NOT NULL,
    title character varying(50),
    description character varying(255),
    price double precision,
    amount integer,
    image character varying,
    size integer,
    arrived date
);


ALTER TABLE public.tshirts OWNER TO postgres;

--
-- Name: tshirts_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tshirts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tshirts_id_seq OWNER TO postgres;

--
-- Name: tshirts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tshirts_id_seq OWNED BY public.tshirts.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    username character varying(50),
    pass character varying(20),
    role character varying(20),
    token character varying(500),
    id integer NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: bags id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bags ALTER COLUMN id SET DEFAULT (1000 + nextval('public.bags_id_seq'::regclass));


--
-- Name: hats id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hats ALTER COLUMN id SET DEFAULT (2000 + nextval('public.hats_id_seq'::regclass));


--
-- Name: messages id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages ALTER COLUMN id SET DEFAULT (500 + nextval('public.messages_id_seq'::regclass));


--
-- Name: orderdetail id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderdetail ALTER COLUMN id SET DEFAULT nextval('public.orderdetail_id_seq'::regclass);


--
-- Name: orders id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);


--
-- Name: shoes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shoes ALTER COLUMN id SET DEFAULT (3000 + nextval('public.shoes_id_seq'::regclass));


--
-- Name: tshirts id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tshirts ALTER COLUMN id SET DEFAULT (3000 + nextval('public.tshirts_id_seq'::regclass));


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Name: bags bags_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bags
    ADD CONSTRAINT bags_pkey PRIMARY KEY (id);


--
-- Name: hats hats_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hats
    ADD CONSTRAINT hats_pkey PRIMARY KEY (id);


--
-- Name: orderdetail orderdetail_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderdetail
    ADD CONSTRAINT orderdetail_pkey PRIMARY KEY (id);


--
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- Name: shoes shoes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shoes
    ADD CONSTRAINT shoes_pkey PRIMARY KEY (id);


--
-- Name: tshirts tshirts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tshirts
    ADD CONSTRAINT tshirts_pkey PRIMARY KEY (id);


--
-- Name: orderdetail orderdetail_orderid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderdetail
    ADD CONSTRAINT orderdetail_orderid_fkey FOREIGN KEY (orderid) REFERENCES public.orders(id);


--
-- PostgreSQL database dump complete
--

