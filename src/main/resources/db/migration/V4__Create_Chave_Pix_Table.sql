CREATE TABLE IF NOT EXISTS chave_pix (
  chave VARCHAR(30) NOT NULL,
   dono_id BIGINT,
   conta_bancaria_id BIGINT,
   CONSTRAINT pk_chavepix PRIMARY KEY (chave)
);
