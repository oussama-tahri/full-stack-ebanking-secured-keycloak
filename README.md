# Ebanking Backend
<hr>

<br>

Dans ce rapport, je vais parler sur la sécurité en utilisant Keycloak.
Voici la commande pour lancer le serveur keycloak :

<img src="captures/keycloakCmdRun.png">

<br>

le serveur keycloak se trouve dans le port **:8080** par défaut, 

<img src="captures/keycloak-interface.png">

<br>

Premierment on doit créer un realm :

<img src="captures/createRealm.png">

<br>

Dans ce realm on doit créer des clients, en passant par les étapes suivantes :

<img src="captures/createClient1.png">

<br>

Dans cette étape en peut créer des clients sans "**Client authentication**" et pour s'authentifier on utilise soit le mot de passe, soit refresh token

<img src="captures/createClient2WithoutAuth.png">

<br>

Si "**Client authentication**" est activé, on peut s'authentifiant a traves le id du client "wallet_client" et le clé secret

<img src="captures/createClient2WithAuth.png">
<img src="captures/loginWithSecretKey.png">

<br>

la derniere étape c'est de définir les routes :

<img src="captures/createClient3.png">

<br>

On a términé la configuration du keycloak, maintenant en passent pour teste l'authentification :

<br>

Authentification par mot de passe :

<img src="captures/testAccesWithpassword.png">

<br>

Authentification par refresh token :

<img src="captures/testAccesWithRefreshToken.png">

<br>

Authentification par clé de secret :

<img src="captures/testAccesWithsecretkey.png">

<br>

Maintenant on va configurer le backend avec keycloak

<br>

Les dependences utilisées :

<img src="captures/dependencies.png">

<br>

La configuration du propriétés

<img src="captures/properties.png">

<br>

Les classes utilisées

<img src="captures/classes.png">

<br>

KeycloakAdapterConfig class

<img src="captures/keycloakAdapterConfigClass.png">

<br>

SecurityConfig class

<img src="captures/SecurityConfigClass.png">

<br>

Pour utiliser keycloakRestTemplate :

<img src="captures/keycloakRestTemplate.png">

<br>

Sécurisation des méthodes :

<img src="captures/secureMethods.png">