ALTER TABLE tbl_agendamento
ADD COLUMN codigo_reserva VARCHAR(100);

ALTER TABLE tbl_agendamento
ADD CONSTRAINT uk_codigo_reserva UNIQUE (codigo_reserva);