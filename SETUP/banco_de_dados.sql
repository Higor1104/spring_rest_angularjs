--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.3
-- Dumped by pg_dump version 9.5.3

-- Started on 2016-06-20 07:29:21

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2124 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 181 (class 1259 OID 16748)
-- Name: enderecos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE enderecos (
    id bigint NOT NULL,
    bairro character varying(255),
    cep integer NOT NULL,
    cidade character varying(255),
    complemento character varying(255),
    estado character varying(255),
    logradouro character varying(255),
    numero integer NOT NULL
);


ALTER TABLE enderecos OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 16776)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 16756)
-- Name: pessoa_endereco; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pessoa_endereco (
    id_endereco bigint NOT NULL,
    id_pessoa bigint NOT NULL
);


ALTER TABLE pessoa_endereco OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 16761)
-- Name: pessoas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pessoas (
    id bigint NOT NULL,
    cpf character varying(255),
    sexo boolean,
    nome character varying(255),
    data_nascimento date
);


ALTER TABLE pessoas OWNER TO postgres;

--
-- TOC entry 2113 (class 0 OID 16748)
-- Dependencies: 181
-- Data for Name: enderecos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY enderecos (id, bairro, cep, cidade, complemento, estado, logradouro, numero) FROM stdin;
102	111111	11111111	1111	\N	111	fdsfsd	1111
\.


--
-- TOC entry 2125 (class 0 OID 0)
-- Dependencies: 184
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 104, true);


--
-- TOC entry 2114 (class 0 OID 16756)
-- Dependencies: 182
-- Data for Name: pessoa_endereco; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pessoa_endereco (id_endereco, id_pessoa) FROM stdin;
102	101
\.


--
-- TOC entry 2115 (class 0 OID 16761)
-- Dependencies: 183
-- Data for Name: pessoas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pessoas (id, cpf, sexo, nome, data_nascimento) FROM stdin;
101	07478352685	t	mateus	1111-11-11
\.


--
-- TOC entry 1992 (class 2606 OID 16755)
-- Name: enderecos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY enderecos
    ADD CONSTRAINT enderecos_pkey PRIMARY KEY (id);


--
-- TOC entry 1994 (class 2606 OID 16760)
-- Name: pessoa_endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pessoa_endereco
    ADD CONSTRAINT pessoa_endereco_pkey PRIMARY KEY (id_endereco, id_pessoa);


--
-- TOC entry 1996 (class 2606 OID 16765)
-- Name: pessoas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pessoas
    ADD CONSTRAINT pessoas_pkey PRIMARY KEY (id);


--
-- TOC entry 1997 (class 2606 OID 16766)
-- Name: fk_kkr6vig0g73lolt5hpw2h3yvu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pessoa_endereco
    ADD CONSTRAINT fk_kkr6vig0g73lolt5hpw2h3yvu FOREIGN KEY (id_endereco) REFERENCES enderecos(id);


--
-- TOC entry 1998 (class 2606 OID 16771)
-- Name: fk_oul842lxg2k4nj42uqtxfddeo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pessoa_endereco
    ADD CONSTRAINT fk_oul842lxg2k4nj42uqtxfddeo FOREIGN KEY (id_pessoa) REFERENCES pessoas(id);


--
-- TOC entry 2123 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-06-20 07:29:22

--
-- PostgreSQL database dump complete
--

