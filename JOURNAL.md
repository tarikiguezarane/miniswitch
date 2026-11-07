# Journal de progression

Une entrée par jour, la plus récente en haut.

## 2026-11-07 – Jour 1 : structure Maven multi-modules

**Fait**
- POM parent (`io.github.tarikiguezarane:miniswitch-parent`) et 6 modules : `iso8583`, `hsm`, `terminal`, `acquirer`, `switch`, `issuer`, chacun avec un `package-info.java` qui décrit son rôle.
- Premier code testé : `Iso8583Version`, qui déduit la version de la norme (1987 / 1993 / 2003) du premier chiffre du MTI, avec 11 tests JUnit 5 paramétrés (cas valides et invalides).
- `docs/flow.md` : diagramme de séquence du circuit 0200/0210, rôle des modules, hiérarchie de clés visée. README avec schéma du flux. Licence MIT, `.gitignore`.
- CI GitHub Actions : `mvn verify` sur Java 21 (Temurin) avec cache Maven.

**Choix techniques**
- Lint dès le jour 1 : compilation en `-Xlint:all -Werror` et `maven-enforcer-plugin` (Java ≥ 21, Maven ≥ 3.9, convergence des dépendances).
- Versions centralisées dans le parent (`dependencyManagement`, BOM JUnit) : un seul endroit pour mettre à jour.
- Le module s'appelle `switch` mais son package Java est `switching`, car `switch` est un mot réservé.
- Les modules métier dépendent seulement de `iso8583` ; la dépendance au `hsm` passera par TCP (comme un vrai HSM réseau), pas par un appel Java direct.

**À retenir pour un entretien**
- Le MTI ISO 8583 a 4 chiffres : version, classe (1 = autorisation, 2 = financier, 4 = annulation/reversal, 8 = gestion réseau), fonction (0 = demande, 1 = réponse…), origine. Ex. `0200` = demande financière, version 1987.
- Dans un vrai système, les clés ne sortent jamais du HSM en clair : les applications envoient des commandes et ne voient que des cryptogrammes.

**Limites connues**
- Les modules sont vides, sauf `iso8583` : le codage des messages arrive au jour 2.
- `mvn verify` passe en local (11 tests) ; la CI GitHub reste à observer.

## 2026-11-07 – Jour 0 : lancement

- Création du dépôt, de la feuille de route sur 30 jours (`ROADMAP.md`) et des instructions de travail quotidien (`CLAUDE.md`).
- Prochaine étape : Jour 1.
