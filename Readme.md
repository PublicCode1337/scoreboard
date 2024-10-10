# Scoreboard Plugin

Dieses Bukkit-Plugin ermöglicht es dir, ein benutzerdefiniertes Scoreboard für Spieler im Minecraft-Server zu erstellen. Das Scoreboard wird alle 2 Sekunden aktualisiert und unterstützt Placeholders, die durch die PlaceholderAPI ersetzt werden können.

## Funktionen

- **Dynamisches Scoreboard**: Das Scoreboard wird für jeden Spieler individuell erstellt und enthält anpassbare Titel und Punktzahlen.
- **Placeholder-Unterstützung**: Integriere Placeholders aus der PlaceholderAPI, um dynamische Daten wie Spielername, Punkte usw. anzuzeigen.
- **Konfigurierbare Scores**: Scores können über die `config.yml`-Datei definiert werden.

## Installation

1. **Paper/Bukkit-Server vorbereiten**: Stelle sicher, dass du einen Bukkit- oder Paper-Server hast, der die Plugin-API unterstützt.
2. **Plugin herunterladen**: Lade die `Scoreboard.jar`-Datei herunter und lege sie im `plugins`-Ordner deines Servers ab.
3. **PlaceholderAPI installieren** (optional): Wenn du Placeholder-Unterstützung benötigst, lade die PlaceholderAPI herunter und installiere sie ebenfalls im `plugins`-Ordner.
4. **Server neu starten**: Starte deinen Server neu, damit das Plugin geladen wird.

## Konfiguration

Nach der Installation findest du die `config.yml`-Datei im Ordner `plugins/Scoreboard`. Hier kannst du die Einstellungen für dein Scoreboard anpassen.

### Beispiel für die `config.yml`:

```yaml
scoreboard:
  title: "&6Mein Scoreboard" # Titel des Scoreboards, unterstützt Farb-Codes
  scores:
    - "Server, 5" # Punktzahlen im Format "Text, Wert"
    - "Lobby, 3"
    - "Spielername, 1" # Verwende Platzhalter wie %player% für dynamische Werte
