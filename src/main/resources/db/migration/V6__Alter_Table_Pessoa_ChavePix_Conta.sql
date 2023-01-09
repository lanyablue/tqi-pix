ALTER TABLE pessoa_chaves_pix ADD CONSTRAINT uc_pessoa_chaves_pix_chavespix UNIQUE (chaves_pix_id);

ALTER TABLE pessoa_conta ADD CONSTRAINT uc_pessoa_conta_conta UNIQUE (conta_id);

ALTER TABLE pessoa_chaves_pix ADD CONSTRAINT fk_peschapix_on_chave_pix FOREIGN KEY (chaves_pix_id) REFERENCES chave_pix (id);

ALTER TABLE pessoa_chaves_pix ADD CONSTRAINT fk_peschapix_on_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoa (id);

ALTER TABLE pessoa_conta ADD CONSTRAINT fk_pescon_on_conta FOREIGN KEY (conta_id) REFERENCES conta (id);

ALTER TABLE pessoa_conta ADD CONSTRAINT fk_pescon_on_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoa (id);