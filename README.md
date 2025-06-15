# Mon Projet Gestion effectifs
Ce projet utilise une **architecture Domain-Driven Design (DDD)** pour organiser la logique métier, Il est basé sur **Spring Boot** et expose une API REST accessible via **Swagger**.

Le projet suit les principes du **DDD** avec plusieurs modules pour séparer les préoccupations :

- **Domain** : Contient la logique métier (models).
- **Infrastructure** : Gère l'accès aux données, dans notre exemple on n'utilise pas une BDD, il n'est pas utilisé ce module.
- **Application** : Le module Application est responsable de la gestion des cas d'utilisation de l'application.
- **Web / API** : Contient les contrôleurs REST pour interagir avec les utilisateurs ou les systèmes externes.


## Endpoints Swagger

Une fois l'application démarrée, tu peux consulter les différents endpoints via Swagger. Voici les principaux endpoints :

- **GET** `/effectifs/personne` - Récupérer la liste des personnes physiques.
- **POST** `/effectifs/personne` - Créer une nouvelle personne physique.
- **GET** `/effectifs/entreprise` - Récupérer la liste des entreprises.
- **POST** `/effectifs/entreprise` - Créer une nouvelle entreprise.
- **POST** `/effectifs/addBeneficiaire` - Ajouter un bénéficiaire : personne physique / entreprise.
- **GET** `/effectifs` - Récupérer la liste des bénéficiaires.
  
Les endpoints sont disponibles dans l'interface Swagger générée automatiquement à l'adresse suivante après avoir démarré l'application :  
**`http://localhost:8080/swagger-ui.html`**

## Comment exécuter l'application

Pour exécuter cette application Spring Boot, suis ces étapes :

1. **Cloner le dépôt** :
   ```bash
   git clone https://github.com/khalilbmiled/gestion-effectiifs.git
   cd gestion-effectiifs
2. **Construire l'application avec Maven :**
   `mvn clean package`

3. **Lancer l'application** 
Une fois l'application construite, tu peux l'exécuter avec la commande suivante :
`java -jar web/target/web-0.0.1-SNAPSHOT.jar`

Cela démarrera l'application sur le port 8080 par défaut.
