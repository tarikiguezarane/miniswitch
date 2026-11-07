# Feuille de route – MiniSwitch (30 jours)

**Profil visé : Monétique.** Simulation du circuit d'autorisation carte complet :
terminal → acquéreur → switch → émetteur, en ISO 8583, avec toutes les opérations cryptographiques
déléguées à un HSM logiciel, comme dans un vrai système.

Stack : Java 21, Maven multi-modules, Netty (TCP), BouncyCastle, PostgreSQL ou H2, JUnit 5, Docker Compose,
OpenTelemetry, GitHub Actions.

Règle : chaque jour, faire la première étape non cochée, puis la cocher. Une étape = un incrément qui marche et qui est testé.
Uniquement des cartes, clés et données de test. Chaque algorithme cryptographique est validé par des vecteurs de test.

## Semaine 1 – ISO 8583 et HSM logiciel
- [x] Jour 1 – Structure Maven multi-modules : `iso8583`, `hsm`, `terminal`, `acquirer`, `switch`, `issuer`, `docs/`, licence MIT, README avec schéma du flux.
- [ ] Jour 2 – Bibliothèque ISO 8583 : MTI, bitmap primaire et secondaire, encodage/décodage + tests.
- [ ] Jour 3 – Définition des champs (fixe, LLVAR, LLLVAR ; n, an, b), packager pour 0100/0110, 0200/0210, 0400/0410, 0800/0810 + tests.
- [ ] Jour 4 – Transport TCP avec en-tête de longueur sur 2 octets (Netty), client et serveur génériques.
- [ ] Jour 5 – Gestion réseau : messages 0800/0810 (sign-on, echo test).
- [ ] Jour 6 – HSM logiciel : clé maître locale simulée, hiérarchie de clés (ZMK, ZPK, TPK, PVK, clé maître EMV), protocole de commandes TCP inspiré des HSM du marché.
- [ ] Jour 7 – HSM : génération de clés, valeurs de contrôle (KCV), import/export sous ZMK.

## Semaine 2 – Cryptographie monétique
- [ ] Jour 8 – PIN block ISO 9564 format 0 + tests sur vecteurs connus.
- [ ] Jour 9 – PIN block ISO 9564 format 4 (AES) + tests.
- [ ] Jour 10 – Traduction de PIN TPK → ZPK (acquéreur) et ZPK → ZPK (switch) par commande HSM.
- [ ] Jour 11 – Vérification du PIN chez l'émetteur (IBM 3624 offset ou PVV) via le HSM.
- [ ] Jour 12 – MAC ISO 9797-1 algorithme 3 (retail MAC) : génération et vérification sur les messages.
- [ ] Jour 13 – Option AES-CMAC pour le MAC.
- [ ] Jour 14 – EMV : dérivation des clés de session, vérification de l'ARQC et génération de l'ARPC avec des clés de test + vecteurs.

## Semaine 3 – Le circuit d'autorisation
- [ ] Jour 15 – Simulateur de terminal : 0200 avec PAN de test, PIN chiffré, données carte (DE55).
- [ ] Jour 16 – Parseur TLV pour le champ 55 (données EMV).
- [ ] Jour 17 – Acquéreur : réception, traduction de PIN, ajout du MAC, routage vers le switch.
- [ ] Jour 18 – Switch : table de routage par BIN, contrôle et régénération du MAC, timeouts, reversal 0400 sur timeout.
- [ ] Jour 19 – Émetteur : base comptes et cartes, plafonds, solde, statut carte, codes réponse (00, 05, 51, 55, 61, 91).
- [ ] Jour 20 – Test de bout en bout du cas nominal sur Docker Compose.
- [ ] Jour 21 – Cas d'erreur : PIN faux, solde insuffisant, plafond dépassé, ARQC invalide, timeout avec reversal.

## Semaine 4 – Robustesse, observabilité et finition
- [ ] Jour 22 – Stand-in processing (STIP) au switch quand l'émetteur est indisponible.
- [ ] Jour 23 – Journalisation avec masquage des PAN, numéros STAN et RRN, bonnes pratiques PCI DSS.
- [ ] Jour 24 – Traçage OpenTelemetry sur les 4 étapes + métriques.
- [ ] Jour 25 – Test de charge (objectif 500 transactions/s) et rapport.
- [ ] Jour 26 – Journal des transactions consultable (CLI ou petite vue web).
- [ ] Jour 27 – GitHub Actions : build, tests, images Docker.
- [ ] Jour 28 – Documentation crypto : formats de PIN, hiérarchie de clés, ARQC/ARPC, expliqués simplement.
- [ ] Jour 29 – Documentation d'architecture (Mermaid) + README final avec badges.
- [ ] Jour 30 – Release v1.0.0 : CHANGELOG, tag Git, bilan dans le journal.
