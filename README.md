# Bibliothèque d'Analyse de Données en Java

![workflow](https://github.com/AEtheve/DevOpsProjet/actions/workflows/maven.yml/badge.svg)
![Badge](https://img.shields.io/endpoint?url=https%3A%2F%2Fgist.githubusercontent.com%2FAEtheve%2F73adb891918b248bf5cd39afb416ee7c%2Fraw%2Ftest.json)

## Auteurs
Alexis Éthève, https://github.com/AEtheve \
Louis Lemay, https://github.com/ar0x18 \
Adrien Chapurlat, https://github.com/LaChappp

## site web associé 
https://aetheve.github.io/DevOpsProjet/

## Description
Ce projet vise à développer une bibliothèque Java pour la manipulation et l'analyse de données. Inspirée par des outils tels que Pandas en Python, cette bibliothèque permettra de créer et de manipuler des structures de données complexes et d'effectuer des analyses statistiques.

## Fonctionnalités
- Création de Dataframes à partir de collections Java ou de fichiers CSV.
- Affichage complet ou partiel des Dataframes.
- Sélection et filtrage de données basés sur des critères spécifiques.
- Calculs statistiques de base sur les colonnes des Dataframes.
- Mécanisme de groupement de données et opération sur ces dernières.

## Usage
build : `mvn -B package --file pom.xml`<br>
test : `mvn test`

## Outils et Technologies
- **Git** pour le contrôle de version.
- **Maven** pour la gestion des dépendances et la construction du projet.
- **JUnit** pour les tests unitaires.
- **JaCoCo** pour l'évaluation de la couverture de code.

## Workflow Git
Nous utilisons le workflow Feature Branch. Chaque nouvelle fonctionnalité (ou bugfix) est développée dans une branche séparée. Les Pull Requests sont ensuite soumis pour examen et validation avant d'être fusionnés dans la branche principale (main).


## Intégration Continue
Un pipeline CI/CD est mis en place avec GitHub Actions pour automatiser les tests et la couverture de code à chaque push et Pull/Merge Request. En complément, pour améliorer le processus de livraison continue, nous avons intégré l'utilisation de Docker. Pour héberger cette image, nous avons choisi d'utiliser le dépôt Docker intégré à GitHub, GitHub Packages.
## Procédure de Validation des Pull Requests
Chaque Pull Request doit être revu par au moins un autre membre de l'équipe avant d'être fusionné dans la branche principale. Nous utilisons des workflows GitHub Actions pour exécuter des tests automatiques et vérifier la couverture de code à chaque Pull Request.