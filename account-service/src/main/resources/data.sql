TRUNCATE TABLE account;

INSERT INTO account (iban, client_id, balance, type, overdraft, taxed)
VALUES ('FR458512369', 'C001', 15520.00, 'CURRENT', -50.00, NULL);

INSERT INTO account (iban, client_id, balance, type, overdraft, taxed)
VALUES ('FR458512368', 'C002', 158.00, 'CURRENT', 0.00, NULL);

/*INSERT INTO account (iban, client_id, balance, type, overdraft, taxed)
VALUES ('FR8964268', '3', 1695.30, 'HOUSING_SAVINGS_PLAN', NULL, TRUE);*/


-- Données issues de getAccounts(clientId)
INSERT INTO account (iban, client_id, balance, type, overdraft, taxed)
VALUES ('12485FB851TID584KJDUH5698598', 'C001', 539.30, 'CURRENT', -150.00, NULL);

/*
INSERT INTO account (iban, client_id, balance, type, overdraft, taxed)
VALUES ('12485FB851TID584KJDUH5698599', '2', 8600.00, 'HOUSING_SAVINGS_PLAN', NULL, FALSE);*/
