CREATE TABLE IF NOT EXISTS chave_pix (
  chave VARCHAR(35) NOT NULL,
   id_pessoa BIGINT,
   conta_corrente VARCHAR(10),
   agencia VARCHAR(10),
   banco VARCHAR(35),
   CONSTRAINT pk_chavepix PRIMARY KEY (chave)
);