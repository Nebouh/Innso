Exécution du projet :
- Possibilité de modifier le port dans le fichier "src/main/resources/application.properties" en rajoutant la ligne : server.port=8081
- Exécuter la commande "mvn clean install" dans le répertoire du projet
- Exécuter la commande "java -jar target/exercice-0.0.1-SNAPSHOT.jar"

Accéder au projet et le tester avec postman ou autre outil :
- http://localhost:8080/api/all > Renvoie toutes les Client Folder
  

- http://localhost:8080/api/message/new > Permet de créer un nouveau message
  - Ajouter un body Json avec 
  {
                "autorName": "test",
                "message": "test message 2",
                "canal": "MAIL"
 }
 
- http://localhost:8080/api/message/new > Permet de créer un nouveau message avec le paramètre "référence" facultatif
  - Ajouter un paramètre "référence : new-test2022-02-02" / Adapté la valeur en fonction de la référence créée par la création du message
  - Ajouter un body Json avec
  {
    "autorName": "testfrfr",
    "message": "Tout fonctionne",
    "canal": "SMS"
  } 

- http://localhost:8080/api/update > permet de modifier la valeur "reference" d'un Client folder
  - Ajouter un paramètre "référence : KA-186C"
  - Ajout un body Json avec
  {
        "reference": "new-test2022-02-02"
  }
