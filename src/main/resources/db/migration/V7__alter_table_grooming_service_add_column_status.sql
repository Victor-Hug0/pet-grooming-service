ALTER TABLE grooming_service ADD COLUMN status VARCHAR(20);

UPDATE grooming_service SET status = 'AGENDADO' WHERE status IS NULL ;