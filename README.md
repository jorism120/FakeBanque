# Bank Demo - Spring Boot Microservices

## Services

- auth-service : génération JWT
- bank-service : API comptes
- api-gateway : gateway sécurisée

## Ports

- auth-service : 9001
- bank-service : 8082
- api-gateway : 8083

## Démarrage

Lancer chaque service :

```bash
mvn spring-boot:run
```

## Login

```bash
curl -X POST http://localhost:8083/auth/login   -H "Content-Type: application/json"   -d '{"username":"client1","password":"password"}'
```

curl.exe -X POST "http://localhost:9001/auth/login" -H "Content-Type: application/json" --data-binary "@login.json"
curl.exe -X POST "http://localhost:8083/auth/login" -H "Content-Type: application/json" --data-binary "@login.json"

## Accounts

```bash
curl http://localhost:8083/api/bank/accounts   -H "Authorization: Bearer YOUR_TOKEN"
```