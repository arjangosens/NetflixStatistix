# NetflixStatistix
~https://github.com/arjangosens/NetflixStatistix~
## Gemaakt door:
- Arjan Gosens (2115998) (ahpm.gose@student.avans.nl)
- Niek Flipse (2129681) (npa.flipse@student.avans.nl)
- Sam Glerum (2126640) (s.glerum1@student.avans.nl)

## Installatiegids
### Stap 1: Het SQL-bestand uitvoeren
In dezelfde bestandsmap als deze README zit een SQL-bestand genaamd *NetflixStatistix.sql*. Dit bestand dient uitgevoerd te worden in *Microsoft SQL Management Studio 17*

### Stap 2: De DatabaseConnector aanpassen
Nadat u het SQL-bestand uitgevoerd heeft dient u in de *DatabaseConnector* class (deze bevindt zich in de *Database* package) een van de twee volgende regels uit te commenten:
````
//    private final String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetflixStatistics;integratedSecurity=true;";
//    private final String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS2017:1444;databaseName=NetflixStatistics;user=SchoolProject;password=school;";
````

Wanneer u gebruik maakt van de standaard beveiliging van Windows dient u de volgende regel te gebruiken.

````
private final String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetflixStatistics;integratedSecurity=true;";
````

Wanneer u een bepaalde gebruikersnaam en wachtwoord gebruikt dient u de volgende regel te gebruiken. Vergeet daarbij niet om de *user* en *password* variabelen aan te passen.

````
private final String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS2017:1444;databaseName=NetflixStatistics;user=SchoolProject;password=school;";
````

### Stap 3: De applicatie opstarten
Nadat u de *DatabaseConnector* class aangepast heeft kunt de de *main* methode in de *Main* class uitvoeren en zal de User Interface starten.