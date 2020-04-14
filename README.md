Application Client-Serveur _Annonce Manager_
============================================

Compiler et exécuter
--------------------

Nous avons ajouté une option `no-encryption` si vous tapez l'option le
serveur/client lance la version qui utilise le chiffrement, sinon il lance la
version qui n'utilise pas le chiffrement, ex. :

### version non sécurisé / sans chiffrement

    make
    java ServerTCP no-encryption
    java ClientTCP no-encryption

### version sécurisé / avec chiffrement

    make
    java ServerTCP 
    java ClientTCP

vous devriez normalement avoir comme résultat ceci :

    Voulez vous vous connecter ? la ligne de commande est : connect <username>:<password>
    Voulez vous créer un compte ? la ligne de commande est : create user <username>:<password>:<phone>:<email>

    
Prérequis
---------

Il faut générer 3 fichiers avant de pouvoir tester ce projet, un fichier `.jsk`
pour le serveur, et un fichier `.jsk` pour le client généré à partir du
certificat donné par le serveur (`.crt`).

Pour générer le serveur.jsk, le serveur.crt et le client.crt:

    keytool -genkey -keystore server.jsk -alias server keyalg RSA 
    keytool -export -keystore server.jsk -alias server -file server.crt 
    keytool -import -alias server -file server.crt -keystore client.jsk

Les mots de passe demandé sont `123456`.

Lancer un Serveur avec la commande `java ServeurTCP`.
Lancer un Client avec la commande `java ClientTCP` (avec notre exemple déjà
implémenté).

J'ai déposé mes fichiers `.jsk` et `.crt` pour tester sans en générer de
nouveaux.

Les Commandes pour communiquer avec le serveur
----------------------------------------------

- Pour créer un compte : `create user <username>:<password>:<phone>:<email>`

      create user hamza:hamza:0615205151:hamza@gmail.com

  La requête est envoyée au serveur, le serveur traite la commande, si elle est
  bonne alors l'utilisateur est ajouté dans la Base de données sinon le serveur
  envoie une erreur au client

- Pour se connecter : `connect <username>:<password>`

    connect hamza:hamza

+ La requête est envoyée au serveur, le serveur traite la commande, si elle est
  bonne l'utilisateur est connecté à l'application et le serveur envoie la
  liste des annonces au client, sinon il envoie le menu d'authentification au
  client.


Pour ajouter une annonce : `annonce <price>:<description>:<domain>`

    annonce 50:IphoneX:Téléphone

+ La requête est envoyée au serveur, le serveur traite la commande, si elle est
bonne alors l'annonce est ajoutée dans la base données ensuite le serveur
renvoie la liste des annonces et le menu au client.

Pour lister vos annonces : `myannonce`

    myannonce

+ La requête est envoyée au serveur, le serveur traite la commande, si elle est
bonne alors la liste des annonces du client connecté s'affiche.

Pour lister toutes les annonces : `annonce`
+ La requête est envoyée au serveur, le serveur traite la commande, si elle est
  bonne alors la liste de toutes les annonces est affichée.

Pour supprimer une de vos annonces : `delete <ID-annonce>`

    delete 1

+ La requête est envoyée au serveur, le serveur traite la commande, si
  l’annonce choisie appartient au client ou existe alors elle va être supprimée
  sinon le serveur va envoyer une erreur.
