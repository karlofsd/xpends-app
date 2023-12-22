--LOADING CURRENCIES
INSERT INTO currencies(name,code,symbol,exchange,deleted) VALUES('Peso argentino', 'ARS','$',1090,0);
INSERT INTO currencies(name,code,symbol,exchange,deleted) VALUES('Peso chileno', 'CLP','$',910,0);
INSERT INTO currencies(name,code,symbol,exchange,deleted) VALUES('Nuevo sol peruano','PEN','S/.',3.85,0);
INSERT INTO currencies(name,code,symbol,exchange,deleted) VALUES('Dólar estadounidense','USD','$',1,0);

--LOADING TRANSACTION_TYPES
INSERT INTO transaction_types(name,description,deleted) VALUES('EXPENSE','Transacciones de gasto',0);
INSERT INTO transaction_types(name,description,deleted) VALUES('DEPOSIT','Transacciones de depósito',0);

--LOADING CATEGORIES
INSERT INTO categories(name,description,deleted) VALUES('Depósito','Nuevo ingreso', 0);
INSERT INTO categories(name,description,deleted) VALUES('Videojuegos','Compra de juegos', 0);
INSERT INTO categories(name,description,deleted) VALUES('Farmacia','Compra de medicamentos', 0);

--LOADING ACCOUNT
INSERT INTO accounts(id,name,description) VALUES(RANDOM_UUID(),'Ahorro pesos','Cuenta de ahorro en pesos');

--LOADING SPOT
INSERT INTO spot(amount,currency_id,account_id) VALUES(500,1,(SELECT MAX(ID) FROM accounts));

--LOADING TRANSACTIONS
INSERT INTO transactions(id,amount,description,date_time,transaction_type_id,spot_id,category_id)
VALUES(RANDOM_UUID(),1000,'Ingreso salario',CURRENT_DATE(),1,1,1);

INSERT INTO transactions(id,amount,description,date_time,transaction_type_id,spot_id,category_id)
VALUES(RANDOM_UUID(),500,'Medicina',CURRENT_DATE(),2,1,3);