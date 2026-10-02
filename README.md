# Bank Demo - Spring Boot Microservices

## Services

- auth-service : génération JWT
- account-service : API comptes
- api-gateway : gateway sécurisée

## Ports

- auth-service : 9001
- account-service : 8082
- api-gateway : 8083

## Démarrage

Lancer chaque service :

```bash
mvn spring-boot:run
```

## Register

```bash
curl -X POST http://localhost:8083/auth/register   -H "Content-Type: application/json"   -d '{"firstName":"Jean","lastName":"Bon","email":"nouveau@mail.fr","password":"password123"}'
```

## Login

Le login s'appuie sur le client-service : le couple email/mot de passe est vérifié via `client-service`, et un JWT contenant le vrai `clientId` est renvoyé.

```bash
curl -X POST http://localhost:8083/auth/login   -H "Content-Type: application/json"   -d '{"email":"mail@mail.fr","password":"password123"}'
```

curl.exe -X POST "http://localhost:9001/auth/login" -H "Content-Type: application/json" --data-binary "@login.json"
curl.exe -X POST "http://localhost:8083/auth/login" -H "Content-Type: application/json" --data-binary "@login.json"

Client de test préchargé (`client-service/src/main/resources/data.sql`) : email `mail@mail.fr` / mot de passe `password123`.

## Client (compte)

```bash
curl http://localhost:8083/api/clients/me   -H "Authorization: Bearer YOUR_TOKEN"
```

## Accounts

```bash
curl http://localhost:8083/api/accounts   -H "Authorization: Bearer YOUR_TOKEN"
```