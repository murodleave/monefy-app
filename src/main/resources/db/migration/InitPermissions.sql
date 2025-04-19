
INSERT INTO permission (code, name, description)
SELECT 'TRANSACTION_READ',
       'Read transactions',
       'Can view transactions' WHERE NOT EXISTS (SELECT 1 FROM permission WHERE code = 'TRANSACTION_READ');
