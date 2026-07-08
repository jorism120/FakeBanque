TRUNCATE TABLE client;

-- mot de passe en clair : password123
INSERT INTO client (id, email, first_name, last_name, password)
VALUES ('C001', 'mail@mail.fr', 'Jean', 'Bon', '$2b$10$KYnUZKONodNAWp5FDDlDA.v4HXFc32vNLv6qJavxfWtX3ntPyDf8W');
