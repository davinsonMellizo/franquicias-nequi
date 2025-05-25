CREATE TABLE IF NOT EXISTS franchise (
	id INT AUTO_INCREMENT NOT NULL,
    name character varying(100) NOT NULL,
    CONSTRAINT id_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS branch (
	id INT AUTO_INCREMENT NOT NULL,
	id_franchise INT NOT NULL,
    name character varying(100) NOT NULL,
    CONSTRAINT id_pkey PRIMARY KEY (id),
    CONSTRAINT franchise_id_fkey FOREIGN KEY (id_franchise) REFERENCES franchise(id)
);


CREATE TABLE IF NOT EXISTS product (
	id INT AUTO_INCREMENT NOT NULL,
	id_branch INT NOT NULL,
    name character varying(100) NOT NULL,
    stock int4 NOT NULL,
    CONSTRAINT id_pkey PRIMARY KEY (id),
    CONSTRAINT branch_id_fkey FOREIGN KEY (id_branch) REFERENCES branch(id)
);