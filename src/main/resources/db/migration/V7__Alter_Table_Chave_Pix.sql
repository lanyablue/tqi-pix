ALTER TABLE chave_pix ADD CONSTRAINT FK_CHAVEPIX_ON_CONTABANCARIA FOREIGN KEY (conta_bancaria_id) REFERENCES conta (id);

ALTER TABLE chave_pix ADD CONSTRAINT FK_CHAVEPIX_ON_DONO FOREIGN KEY (dono_id) REFERENCES pessoa (id);
